/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Controller.services;
import java.sql.ResultSet;
import Controller.Message;
/**
 *
 * @author ADMIN
 */
public class Model_Sach {
    private String id, ten, tacGia, theLoai;
    private int gia , soLuong, luotmua, luotxem;
    private String image;
    public Model_Sach()
    {
        
    }
    public String getTen()
    {
        return this.ten;
    }
    public int getGia()
    {
        return this.gia;
    }
    public int getSoLuong()
    {
        return this.soLuong;
    }
    public void setSoLuong(int SL)
    {
        this.soLuong = SL;
    }
    public String getID()
    {
        return this.id;
    }
    public String getIdTacGia()
    {
        return this.tacGia;
    }
    public String getTheLoai()
    {
        return this.theLoai;
    }
    public String getImage()
    {
        return this.image;
    }
    public int getLuotMua()
    {
        return this.luotmua;
    }
    public int getLuotXem()
    {
        return this.luotxem;
    }
    public Model_Sach(ResultSet rs)
    {
        try{
            this.id = rs.getString("book_id");
            this.ten = rs.getString("book_name");
            this.tacGia = rs.getString("tacgia");
            this.theLoai = rs.getString("theloai");
            this.gia = rs.getInt("gia");
            this.soLuong = rs.getInt("soluong");
            this.image = rs.getString("image");
            this.luotmua = rs.getInt("luotmua");
            this.luotxem = rs.getInt("luotxem");
            //services.msg("Load du lieu sach "+this.ten+" thanh cong");
        }
        catch(Exception e)
        {
            services.msgbox("Error",Message.errorGetBook);
            services.msg("Constructor Model_Sach : "+e);
        }
    }
    public void getInfo()
    {
        try{
            services.msg("ID : "+this.id);
            services.msg("Ten : "+this.ten);
            services.msg("Tac gia : "+services.cnn.getRowInDB("user","user_id",this.tacGia).getString("Ten"));
            services.msg("The loai : "+this.theLoai);
            services.msg("Gia : "+this.gia);
            services.msg("So luong : "+this.soLuong);
            services.msg("Image : "+this.image);
        }
        catch(Exception e)
        {
            services.msg(e);
        }      
    }
    public Object[] getObject()
    {
        try{
            return new Object[]{this.id,this.ten,services.cnn.getRowInDB("user","user_id",this.tacGia).getString("Ten"),services.cnn.getRowInDB("danhmuc", "id", this.getTheLoai().trim()).getString("ten"),this.gia};
        }
        catch(Exception e)
        {
            return new Object[]{};
        }   
    }
}
