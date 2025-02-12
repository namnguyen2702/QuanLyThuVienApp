/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public interface ICustomer {
    public abstract void addBookToCart(String id_book);
    public abstract void buyBook(int price);
    public abstract void updateBalance();
    public abstract void updateInventory(String type);
    public abstract ArrayList<Model_Sach> getInventory(String type);
    public abstract void loadInventory(ResultSet rs, String type);
    public abstract void submitRate(String book_id, String txtRate, int starRate);
}
