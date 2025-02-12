/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Controller.services;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public interface IUser {
    public abstract boolean login(String username, String password);
    public abstract void logout();
    public abstract void changepassword(String pwd_old, String pwd_new);
    public abstract void updateInfo(String txtboxhoTen,String txtboxGioitinh,String txtboxNgaySinh, String txtboxDiachi,String txtboxSdt );
    public abstract ArrayList<Model_Log> viewLogs();
}
