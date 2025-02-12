/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBconnect;
import Models.Model_Account;
import java.sql.*;
import Controller.services;
import Models.*;
/**
 *
 * @author ADMIN
 */
public class Connect {
    public Connection con;
    public boolean isLogin = false;
    public boolean isConnect = false;
    public String id;
    public Connect()
    {
          
    }
    public void connect()
    {
        try{
            this.isConnect = true;
            Class.forName("com.mysql.jdbc.Driver");  
            this.con=DriverManager.getConnection("jdbc:mysql://"+services.server+":3306/library?characterEncoding=utf8","root","");  
            services.msg("DBConnect Success");
        }
        catch(Exception e)
        {
            
        }
    }
    public void loadUser(People_User user)
    {
        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user where user_id="+this.id);
            services.user = new People_User(rs);
        }
        catch(Exception e)
        {
            services.msg(e);
        }
        
    }
    public void loadBook()
    {
        services.books.clear();
        try{
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `khosach`");
            while(rs.next())
            {
                Model_Sach tmp = new Model_Sach(rs);
                services.books.add(tmp);
            }
        }
        catch(Exception e)
        {
            services.msg(e);
        }
    }
    public void resetInfo()
    {
        services.user = new People_User();
        services.cnn.isLogin = false;
        services.account = new Model_Account();
    }
    public ResultSet getRowInDB(String table, String columnLabel, String value)
    {
        try{
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `"+table+"` WHERE `"+columnLabel+"`='"+value+"' LIMIT 1");
            rs.next();
            return rs;
        }
        catch(Exception e)
        {
            services.msg(e);
        }
        return null;
    }
}
