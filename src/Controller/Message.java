/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ADMIN
 */

public class Message {
    //Account 
    public static String addAccount = " add account ";
    public static String resetPass = "reset password account ID : ";
    public static String removeUser = "remove account ID : ";
    public static String wrongInfo = "Thông tin tài khoản hoặc mật khẩu không chính xác!";
    public static String lockAccount = "Tài khoản đã bị khóa";
    public static String notFoundUser = "Không tìm thấy user trong hệ thống";
    public static String loginSuccess = "Login thành công!";
    public static String notEnoughPermission = "Bạn không đủ thẩm quyền!";
    public static String registerSuccess = "Đăng kí tài khoản thành công!";
    public static String accountExist = "Tài khoản đã tồn tại!";
    public static String differentPass = "Mật khẩu không giống nhau!";
    public static String containSpace = "Tài khoản mật khẩu không chứa dấu cách !";
    public static String errorRegister = "Có lỗi trong quá trình thêm tài khoản!";
    
    //Book
    
    public static String addBook = "add book ";    
    public static String updateBook = "update book ID : ";
    public static String deleteBook = "delete book ID : ";
    public static String buyBook = "buy book ID : ";
    public static String rateSuccess = "Đánh giá sách thành công";
    public static String errorGetBook = "Có lỗi trong quá trình lấy dữ liệu sách!";
    public static String addToCart = "Thêm sách vào giỏ hàng thành công!";
    public static String notFoundResultBook = "Không tìm thấy sách theo yêu cầu";
    
    //Other
    public static String errorGetImage ="Không thể lấy thông tin ảnh đại diện!";
    public static String errorWhenConnect = "Có lỗi trong quá trình kết nối !";
    public static String incorrectCaptcha = "Mã xác nhận không đúng!";
    public static String tooManyTryAgain = "Quá nhiều lần thử không thành công!";
    public static String missingInput = "Vui lòng nhập đủ dữ liệu!";
    public static String chooseFilter = "Vui lòng chọn phân loại tìm kiếm!";
    
}
