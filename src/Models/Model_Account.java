/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.sql.ResultSet;
import java.util.Date;
import Controller.services;
/**
 *
 * @author ADMIN
 */
public class Model_Account {
    protected String usr, pwd, user_id;
    protected boolean ban = false;
    protected Date joined_time;
    protected boolean isAdmin = false;
    public Model_Account()
    {
        
    }
    public Model_Account(ResultSet rs)
    {
        try
        {
            this.usr = rs.getString(1);
            this.pwd = rs.getString(2);
            this.user_id = rs.getString(3);
            this.ban = rs.getInt(4)==1?true:false;
            this.joined_time = rs.getDate(5);
            this.isAdmin = rs.getInt(6)==1?true:false;
        }
        catch(Exception e)
        {
            
        }      
    }
    public void getInfo()
    {
        services.msg(this.usr+" "+this.pwd+" "+this.user_id+" "+this.ban+" "+this.joined_time);
    }
    public String getUsername()
    {
        return this.usr;
    }
    public String getPassword()
    {
        return this.pwd;
    }
    public void setPassword(String pwd)
    {
        this.pwd = pwd;
    }
    public String getUserID()
    {
        return this.user_id;
    }
    public boolean isAdmin()
    {
        return this.isAdmin;
    }
    public boolean isBan()
    {
        return this.ban;
    }
}
