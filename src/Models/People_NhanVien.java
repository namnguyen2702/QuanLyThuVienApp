package Models;

import Controller.Message;
import Controller.services;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class People_NhanVien extends People_User implements IStaff{
    private int luong;
    public People_NhanVien(People_User usr)
    {
        super(usr);
    }
    @Override
    public ArrayList<Model_Log> viewLogs()
    {
        String sqlGetLogs = "SELECT * FROM `logs` WHERE `type`='buy'";
        ArrayList<Model_Log> logs = new ArrayList<Model_Log>();
        try{
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlGetLogs);
            while(rs.next())
            {
                Model_Log tmp = new Model_Log();
                tmp.time = rs.getTimestamp("time");
                tmp.user = rs.getString("user");
                tmp.action = rs.getString("action");
                logs.add(tmp);
                services.msg("add log "+tmp.user+" "+tmp.action);
            } 
        }
        catch(Exception e)
        {
            services.msg(e);
            services.msgbox("Error", "Có lỗi trong quá trình lấy logs");
        }
        return logs;
    }
    @Override
    public void addBook(String last_id, String txtboxTen,String txtboxTacGia, String txtboxTheLoai,String txtboxGia, String txtboxSoLuong, String txtboxLinkAnh)
    {
        try{
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs;
            String sqlGetLastID = "SELECT * FROM `khosach` ORDER BY `book_id` DESC LIMIT 1";
            rs = stmt.executeQuery(sqlGetLastID);
            while(rs.next())
            {
                int id= (Integer.parseInt(rs.getString("book_id"))+1);
                if(id<10) last_id = "00"+id;
                else if(id<100) last_id = "0"+id;
                else last_id = id+"";
                break;
            }
            String id_TacGia = services.cnn.getRowInDB("user","ten",txtboxTacGia).getString("user_id");
            String sqlInsert = "INSERT INTO `khosach`(`book_id`, `book_name`, `tacgia`, `theloai`, `gia`, `soluong`, `image`) "
                    + "VALUES ('"+last_id+"','"+txtboxTen+"','"+id_TacGia+"','"+services.cnn.getRowInDB("danhmuc", "ten", txtboxTheLoai).getString("id")+"','"+txtboxGia+"','"+txtboxSoLuong+"','"+txtboxLinkAnh+"')";
            stmt.execute(sqlInsert);
            services.msgbox("Success", "Thêm sách '"+txtboxTen+"' thành công!");
            services.sendLog(services.user.getID(),services.user.getTen(),"addbook", Message.addBook+txtboxTen+", ID : "+last_id);
        }
        catch(Exception e)
        {
            services.msg(e);
            services.msgbox("Error", "Có lỗi trong quá trình thêm sách");
        }
    }
}
