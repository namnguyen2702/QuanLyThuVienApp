/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Scanner;
import DBconnect.Connect;
import javax.swing.JOptionPane;
import Models.*;
import Models.Model_Account;
import View.FrameMsg;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

/**
 *
 * @author ADMIN
 */
public class services {

    public static boolean fix = true;
    public static String server = "127.0.0.1";
    public static String imageServer = "127.0.0.1:8080";
    public static boolean isConfirm = false;

    public static String path_avatar = "/content/image/avatar/";
    public static String path_book = "/content/image/books/";
    public static String path_comp = "/content/image/comp/";
    public static Connect cnn = new Connect();
    public static Scanner sc = new Scanner(System.in);

    public static ArrayList<Model_Sach> books = new ArrayList<Model_Sach>();

    public static Model_Account account = new Model_Account();
    public static People_User user = new People_User();

    public static <T> void msg(T msg) {
        if (services.fix) {
            System.out.println(msg);
        }
    }

    public static <T> void msgbox(String title, T msg) {

//        JOptionPane.showMessageDialog(null, 
//                              msg, 
//                              title, 
//                              JOptionPane.INFORMATION_MESSAGE);
        FrameMsg msgbox = new FrameMsg();
        msgbox.setMessage(title, String.valueOf(msg));
        msgbox.show();
    }

    public static boolean setImgLabel(JLabel avatar, String url, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new URL(url));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(width, height, width));
            avatar.setIcon(icon);
            return true;
        } catch (Exception e) {
            try {
                avatar.setIcon(new ImageIcon(ImageIO.read(new URL(services.getUrlImageBook("error.jpg"))).getScaledInstance(width, height, width)));
            } catch (Exception e1) {
            }
            services.msgbox("Error", Message.errorGetImage);
            services.msg(e);
            return false;
            //System.exit(1);
        }
    }

    public static void setImgButton(JButton btn, String url) {
        try {
            BufferedImage img = ImageIO.read(new URL(url));
            ImageIcon icon = new ImageIcon(img);
            btn.setIcon(icon);
        } catch (Exception e) {
            services.msg("Khong the set anh");
            services.msg(e);
            //System.exit(1);
        }
    }

    public static void disposeParent(JPanel panel, JPanel new_panel) {
        if (new_panel == null) {
            JFrame parent = (JFrame) panel.getTopLevelAncestor();
            parent.dispose();
        } else {
            JFrame parent = (JFrame) panel.getTopLevelAncestor();
            parent.setContentPane(new_panel);
            parent.validate();
            parent.invalidate();
        }
    }

    public static String getUrlImageBook(String filename) {
        if (filename.contains("http")) {
            return filename;
        } else {
            return "http://" + services.imageServer + services.path_book + filename;
        }
    }

    public static String getUrlImageAvatar(String filename) {
        if (filename.contains("http")) {
            return filename;
        } else {
            return "http://" + services.imageServer + services.path_avatar + filename;
        }
    }

    public static String getUrlImageComp(String filename) {
        if (filename.contains("http")) {
            return filename;
        } else {
            return "http://" + services.imageServer + services.path_comp + filename;
        }
    }

    public static void sendLog(String user_id, String user, String type, String logs) {
        try {
            int last_id;
            String id;
            Statement stmt = services.cnn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `logs` GROUP BY `id` DESC LIMIT 1");
            if (rs.next()) {
                last_id = Integer.parseInt(rs.getString("id")) + 1;
            } else {
                return;
            }
            if (last_id < 10) {
                id = "0000" + last_id;
            } else if (last_id < 100) {
                id = "000" + last_id;
            } else if (last_id < 1000) {
                id = "00" + last_id;
            } else if (last_id < 10000) {
                id = "0" + last_id;
            } else {
                id = last_id + "";
            }
            String sqlInsertLog = "INSERT INTO `logs`(`id`, `user`, `action`, `user_id`,`type`) VALUES ('" + id.trim() + "','" + user.trim() + "','" + logs.trim() + "','" + user_id + "','" + type + "')";
            stmt.execute(sqlInsertLog);
        } catch (Exception e) {
            services.msg(e);
        }
    }

    public static Model_Sach getBookByID(String book_id) {
        Model_Sach tmp = new Model_Sach();
        try {
            ResultSet rs = services.cnn.con.createStatement().executeQuery("SELECT * FROM `khosach` WHERE book_id='" + book_id + "'");
            if (rs.next()) {
                tmp = new Model_Sach(rs);
            }
        } catch (Exception e) {
            tmp = null;
            services.msg(e);
        }
        return tmp;
    }

    public static void buyBook(String id, int SL) {
        try {
            int sl = 0;
            String sqlGet = "SELECT * FROM `khosach` WHERE `book_id` = '" + id + "' LIMIT 1";
            ResultSet rs = services.cnn.con.createStatement().executeQuery(sqlGet);
            if (rs.next()) {
                sl = rs.getInt("soluong");
            } else {
                return;
            }
            String sql = "UPDATE `khosach` SET soluong='" + (sl - SL) + "' WHERE `book_id`='" + id + "'";
            services.cnn.con.createStatement().execute(sql);
        } catch (Exception e) {
            services.msg(e);
        }
    }

    public static String getRandomCaptcha(boolean number) {
        Random rand = new Random();
        int val_int = rand.nextInt(999999);
        String rd = "";
        while (val_int > 0 && number == false) {
            switch (val_int % 10) {
                case 0: {
                    rd += "j";
                    break;
                }
                case 1: {
                    rd += "b";
                    break;
                }
                case 2: {
                    rd += "q";
                    break;
                }
                case 3: {
                    rd += "z";
                    break;
                }
                case 4: {
                    rd += "a";
                    break;
                }
                case 5: {
                    rd += "m";
                    break;
                }
                case 6: {
                    rd += "k";
                    break;
                }
                case 7: {
                    rd += "t";
                    break;
                }
                case 8: {
                    rd += "x";
                    break;
                }
                case 9: {
                    rd += "p";
                    break;
                }
            }
            val_int /= 10;
        }
        if (number) {
            rd = String.valueOf(val_int);
        }
        try {
            Process p = Runtime.getRuntime().exec(
                    "cmd /c python src/captcha/captcha-main.py " + rd);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            services.msg(in.readLine()); // Show captcha
        } catch (Exception e) {
            services.msg(e);
        }
        if (number) {
            return String.valueOf(val_int);
        }
        return rd;
    }

    public static void changePanel(JPanel panelold, JPanel panelnew, int width, int height) {
        panelold.removeAll();
        panelold.setLayout(new CardLayout());
        panelold.add(panelnew);
        panelold.setPreferredSize(new Dimension(width, height));
        panelold.validate();
        panelold.invalidate();
    }
}
