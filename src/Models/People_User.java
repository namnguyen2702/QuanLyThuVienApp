/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.util.Date;
import java.sql.ResultSet;
import Controller.*;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class People_User implements IUser{
    protected String id, ten, gioiTinh, diaChi, dienThoai, cmnd, avatar;
    protected Date ngaySinh;
    protected Model_ChucVu role = new Model_ChucVu();
    protected int money ;
    public People_User()
    {
        
    }
    public People_User(ResultSet rs)
    {
        try
        {
            this.id = rs.getString("user_id");
            this.ten = rs.getString("Ten");
            this.gioiTinh = rs.getString("Gioi tinh");
            this.ngaySinh = rs.getDate("Ngay sinh");
            this.diaChi = rs.getString("Dia chi");
            this.dienThoai = rs.getString("Sdt");
            this.cmnd = rs.getString("CMND");
            this.avatar = rs.getString("avatar");
            this.role.setChucVu(rs.getString("chucvu"));
            this.money = rs.getInt("so_du");
            //services.msg("Load user : "+this.ten+" thanh cong");
        }
        catch(Exception e)
        {
            services.msg("DB fail load user");
            services.msg(e);
        }
    }
    public People_User(People_User usr)
    {
        this.id = usr.id;
        this.ten = usr.ten;
        this.gioiTinh = usr.gioiTinh;  
        this.ngaySinh = usr.ngaySinh;  
        this.diaChi = usr.diaChi;      
        this.dienThoai = usr.dienThoai;
        this.cmnd = usr.cmnd;
        this.avatar = usr.avatar;
        this.role = usr.role;
        this.money = usr.money;
    }
    public void getInfo()
    {
        services.msg("Get info :");
        services.msg("Tên : "+this.ten);
        services.msg("User ID : "+this.id);
        services.msg("Giới tính : "+this.gioiTinh);
        services.msg("Ngày Sinh : "+this.ngaySinh);
        services.msg("Địa chỉ : "+this.diaChi);
        services.msg("Điện thoại : "+this.dienThoai);
        services.msg("CMND : "+this.cmnd);
        services.msg("Role : "+this.role.getChucVu());
    }
    public String getID()
    {
        return this.id;
    }
    public String getTen()
    {
        return this.ten;
    }
    public String getGioitinh()
    {
        return this.gioiTinh;
    }
    public Date getNgaysinh()
    {
        return this.ngaySinh;
    }
    public String getDiachi()
    {
        return this.diaChi;
    }
    public String getSdt()
    {
        return this.dienThoai;
    }
    public String getCMND()
    {
        return this.cmnd;
    }
    public String getAvatar()
    {
        return this.avatar;
    }
    public String getChucvu()
    {
        return this.role.getChucVu();
    }
    public int getSoDu()
    {
        return this.money;
    }
    @Override
    public boolean login(String username, String password)
    {
        try{
            String sqlCheckInfo = "SELECT * FROM `login` WHERE username='"+username+"' AND password = '"+password+"' LIMIT 1";
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCheckInfo);
            if(!rs.next())
            {
                services.msgbox("Error",Message.wrongInfo);
                return false;
            }
            else{
                if(rs.getInt("banned")==1)
                {
                    services.msgbox("Thông báo",Message.lockAccount);
                    return false;
                }
                services.account = new Model_Account(rs);
                rs = stmt.executeQuery("SELECT * FROM `user` WHERE user_id = '"+services.account.user_id+"'");
                if(!rs.next())
                {
                    services.msgbox("Thông báo",Message.notFoundUser);
                    return false;
                }
                else {
                    services.user = new People_User(rs);
                    services.msgbox("Thông báo", Message.loginSuccess);
                }         
                return true;
            }
            
        }
        catch(Exception e)
        {
            services.msg(e);
            services.msgbox("Error", Message.errorWhenConnect);
            services.cnn.connect();
            return false;
        }
    }
    
    
    @Override
    public void logout()
    {
        services.user = new People_User();
        services.account = new Model_Account();
    }
    @Override
    public void changepassword(String pwd_old, String pwd_new)
    {
        String sql = "";
        try{
            Statement stmt = services.cnn.con.createStatement();
            sql = "UPDATE `login` SET `password`='"+pwd_new+"' WHERE `username`='"+services.account.getUsername()+"'";
            stmt.execute(sql);
            services.account.setPassword(pwd_new);
            services.msgbox("Thay đổi mật khẩu", "Thay đổi mật khẩu thành công!");
        }
        catch(Exception e)
        {
            services.msgbox("Thất bại", "Có lỗi trong quá trình thay đổi mật khẩu");
            services.msg(e);
        }
    }
    @Override
    public void updateInfo(String txtboxhoTen,String txtboxGioitinh,String txtboxNgaySinh, String txtboxDiachi,String txtboxSdt )
    {
         try{
            Statement stmt = services.cnn.con.createStatement();
            stmt.execute("UPDATE `user` SET `Ten`='"+txtboxhoTen+"',`Gioi tinh`='"+txtboxGioitinh+"',`Ngay sinh`='"+txtboxNgaySinh+"',`Dia chi`='"+txtboxDiachi+"',`Sdt`='"+txtboxSdt+"' WHERE `user_id`='"+services.account.getUserID()+"'");
            services.msgbox("Cập nhật thông tin", "Cập nhật thông tin thành công !");
            services.cnn.loadUser(services.user);
        }
        catch(Exception e)
        {
            services.msgbox("Cập nhật thông tin", "Cập nhật thông tin thành công !");
            services.msg(e);
        }
    }
    @Override
    public ArrayList<Model_Log> viewLogs(){
        return null;
    }
}
