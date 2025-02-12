/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Controller.Message;
import Controller.services;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class People_QuanLy extends People_User implements IManager, IStaff{
    public People_QuanLy()
    {
        super();
    }
    public People_QuanLy(People_User usr)
    {
        super(usr);
    }
    @Override
    public ArrayList<Model_Log> viewLogs()
    {
        String sqlGetLogs = "SELECT * FROM `logs`";
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
    @Override
    public void loadUser(DefaultTableModel model)
    {
        //id  - tk - mk - admin - ban
        try{
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `login` ORDER BY `user_id`");
            while(rs.next())
            {
                //model.addRow(new Object[]{rs.getString(3),rs.getString(1),rs.getString(2),rs.getInt(6)==1?true:false,rs.getInt(4)==1?true:false});
                model.addRow(new Object[]{rs.getString(3),rs.getString(1),"******",rs.getInt(6)==1?true:false,rs.getInt(4)==1?true:false});
            }
        }
        catch(Exception e)
        {
            services.msg(e);
            return;
        }
        services.msg("Load table success");
    }
    public String getData(String table)
    {
        String data = "";
        try{
            ResultSet rs = services.cnn.con.createStatement().executeQuery("SELECT COUNT(*) FROM "+table+" AS tong WHERE 1");
            if(rs.next())
            {
                data = rs.getString(1);
            }
        }
        catch(Exception e)
        {
            services.msg(e);
        }
        return data;
    }
    @Override
    public String[] loadAnalytics()
    {
        // so nguoi dung - so sach - so danh muc - so rate - so phien ban
        String[] data = new String[5];
        try{
            data[0] = this.getData("login");
            data[1] = this.getData("khosach");
            data[2] = this.getData("danhmuc");
            data[3] = this.getData("rate");
            data[4] = this.getData("version");
        }
        catch(Exception e)
        {
            services.msg(e);
        }
        return data;
    }
}
