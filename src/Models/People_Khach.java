/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Controller.Message;
import java.util.Date;
import java.util.ArrayList; 
import java.sql.ResultSet;
import Controller.services;
import java.sql.Statement;
/**
 *
 * @author ADMIN
 */
public class People_Khach extends People_User implements ICustomer{
    protected Date ngayThamGia ; 
    private ArrayList<Model_Sach> inventory = new ArrayList<Model_Sach>();
    private ArrayList<Model_Sach> cart = new ArrayList<Model_Sach>();
    public People_Khach(People_User usr)
    {
        super(usr);
    }
    @Override
    public void loadInventory(ResultSet rs,String type)
    {
        switch(type)
        {
            case "cart":{
                this.cart.clear();
                break;
            }
            case "inventory":{
                this.inventory.clear();
                break;
            }
        }
        try{
            //services.msg("Loading "+type);
            String[] list_id_book = rs.getString(type).split(",");
            for(String info_book : list_id_book)
            {
                try{
                //services.msg(type +" ID : "+id_book);
                Model_Sach tmp = services.getBookByID(info_book.split(":")[0].trim());
                tmp.setSoLuong(Integer.parseInt(info_book.split(":")[1].trim()));
                //services.msg("Load "+type+" tên : "+tmp.getTen());
                if(type.compareTo("inventory")==0) this.inventory.add(tmp);
                else this.cart.add(tmp);
                }
                catch(Exception e1)
                {
                    services.msg(e1);
                }
            }
        }
        catch(Exception e)
        {
            services.msg(e);
        }
    }

    @Override
    public ArrayList<Model_Sach> getInventory(String type)
    {
        if(type.compareTo("cart")==0)
        {
            return this.cart;
        }
        else 
            return this.inventory;
    }
    @Override
    public void updateInventory(String type)
    {
        String sql = "";
        try{
            String value = "";
            for(Model_Sach sach:((People_Khach)services.user).getInventory(type))
            {
                value+=sach.getID()+":"+sach.getSoLuong()+",";
            }
            if(value.compareTo("")==0)
            {
                value = "";
            }
            else 
                value = value.substring(0, value.length()-1);
            sql = "UPDATE `inventory` SET `"+type+"` ='"+value+"' WHERE user_id='"+services.user.getID()+"'";
            Statement stmt = services.cnn.con.createStatement();
            stmt.execute(sql);
            services.msg("Update invent");
        }
        catch(Exception e)
        {
            services.msg("Cập nhật invent thất bại");
            services.msg(sql);
            services.msg(e);
        }
    }
    
    @Override
    public void addBookToCart(String id)
    {
        Model_Sach sach = services.getBookByID(id);
        if(sach==null)
        {
            services.msg("Sach == null");
            return;
        }
        for(Model_Sach item:this.cart)
        {
            if(sach.getID().compareTo(item.getID())==0)
            {
                item.setSoLuong(item.getSoLuong()+1);
                services.msgbox("Success", "Thêm số lượng 1 vào giỏ hàng thành công!");
                return;
            }
        }
        sach.setSoLuong(1);
        this.cart.add(sach);
        services.msgbox("Success", Message.addToCart);
        return;
    }
    @Override
    public void buyBook(int price)
    {
        this.money -= price;
        this.updateBalance();
    }
    @Override
    public void updateBalance()
    {
        String sqlUpdate = "";
        try{
            sqlUpdate = "UPDATE `user` SET `so_du`='"+this.money+"' WHERE `user_id`='"+this.getID()+"'";
            services.cnn.con.createStatement().execute(sqlUpdate);
        }
        catch(Exception e)
        {
            services.msg(sqlUpdate);
            services.msg(e);
        }
    }
    @Override
    public void submitRate(String book_id, String txtRate, int starRate)
    {
        int last_id = 999;
        String idInsert = "";
        try{
            String sqlGetLastID = "SELECT `id_cmt` FROM `rate` ORDER BY `id_cmt` DESC LIMIT 1";
            ResultSet rs = services.cnn.con.createStatement().executeQuery(sqlGetLastID);
            if(rs.next())
            {
                last_id = Integer.parseInt(rs.getString("id_cmt"))+1;
            }
            else{
                last_id = 1;
            }
            if(last_id<10) idInsert = "00"+last_id;
            else if(last_id<100) idInsert = "0"+last_id;
            else idInsert = last_id +"";
            
            String sqlInsert = "INSERT INTO `rate`(`id_cmt`, `book_id`, `user`, `comment`, `star`) "
                    + "VALUES ('"+idInsert+"','"+book_id+"','"+services.user.getID()+"','"+txtRate+"','"+starRate+"')";
            services.cnn.con.createStatement().execute(sqlInsert);
            services.msgbox("Success", Message.rateSuccess);
        }
        catch(Exception e)
        {
            
        }
    }
    @Override
    public ArrayList<Model_Log> viewLogs()
    {
        String sqlGetLogs = "SELECT * FROM `logs` WHERE `type`= 'buy' and `user_id`='"+services.user.getID().trim()+"'";
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
}
