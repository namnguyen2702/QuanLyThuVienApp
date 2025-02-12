-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Feb 12, 2025 at 03:28 PM
-- Server version: 11.4.2-MariaDB-log
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `Ten` varchar(50) NOT NULL,
  `link_fb` varchar(99) NOT NULL,
  `vaitro` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`Ten`, `link_fb`, `vaitro`) VALUES
('Nguyễn Văn Nam', 'https://facebook.com/nvn2702', 'Trưởng nhóm');

-- --------------------------------------------------------

--
-- Table structure for table `danhmuc`
--

CREATE TABLE `danhmuc` (
  `id` varchar(30) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `mota` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `danhmuc`
--

INSERT INTO `danhmuc` (`id`, `ten`, `mota`) VALUES
('001', 'Luyện Thi THPTQG', NULL),
('002', 'Sách Tham Khảo Cấp 1, 2, 3', NULL),
('003', 'Sách Tâm Lý', NULL),
('004', 'Sách Thiếu Nhi', NULL),
('005', 'Sách Tham Khảo Lớp 9', NULL),
('006', 'Sách Tiếng Anh', NULL),
('007', 'Ngoại Ngữ', NULL),
('008', 'Sách Mới', NULL),
('009', 'Sách Văn Học', NULL),
('010', 'Kỹ năng sống', NULL),
('011', 'Quản lý', NULL),
('012', 'Phật Giáo', NULL),
('013', 'Sách Y Học - Chăm Sóc Sức Khỏe', NULL),
('014', 'Sách Thường Thức Đời Sống', NULL),
('015', 'Truyện Tranh Thiếu Nhi', NULL),
('016', 'Kĩ năng-trí tuệ', NULL),
('017', 'Kiến thức', NULL),
('018', 'Kinh tế', NULL),
('019', 'Cha mẹ', NULL),
('020', 'In Lịch Tết 2023', NULL),
('021', 'Sách Về Danh Nhân', NULL),
('022', 'Lịch Sử', NULL),
('023', 'Vỡ Tập Tô - Tập Viết - Tập Vẽ', NULL),
('024', 'Sách Văn Học Việt Nam', NULL),
('025', 'Kỹ năng', NULL),
('026', 'Sách Văn Hoá - Du Lịch', NULL),
('027', 'Sách Triết Học - Tôn Giáo -Tâm Linh', NULL),
('028', 'Phong Thủy - Tử Vi', NULL),
('029', 'Từ Điển', NULL),
('030', 'Nữ Công Gia Chánh', NULL),
('031', 'Sức khỏe', NULL),
('032', 'Sách Y Học & Thể Dục Thể Thao', NULL),
('033', 'Tư Duy - Sống Đẹp', NULL),
('034', 'Khám Phá', NULL),
('035', 'Tư duy và học tập', NULL),
('036', 'Sách Tham Khảo Lớp 10', NULL),
('037', 'Truyện Cổ Tích', NULL),
('038', 'Khoa Học', NULL),
('039', 'Giáo trình', NULL),
('040', 'Luyện Thi English', NULL),
('041', 'Nuôi Dạy', NULL),
('042', 'KHXH', NULL),
('043', 'KHTN', NULL),
('044', 'Ngoại Văn', NULL),
('045', 'Bonsai', NULL),
('046', 'Chính trị', NULL),
('047', 'Sách Triết Học', NULL),
('048', 'Sách Toán Mẫu Giáo - Thiếu Nhi', NULL),
('049', 'Sách Văn Học Nước Ngoài', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `user_id` varchar(10) NOT NULL,
  `inventory` varchar(2000) DEFAULT NULL,
  `cart` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`user_id`, `inventory`, `cart`) VALUES
('001', NULL, NULL),
('002', NULL, NULL),
('003', '481:1,867:1', ''),
('004', NULL, '001:4'),
('005', NULL, '002:3,001:1'),
('007', '004:1', '002:1,001:1,003:1');

-- --------------------------------------------------------

--
-- Table structure for table `khosach`
--

CREATE TABLE `khosach` (
  `book_id` varchar(10) NOT NULL,
  `book_name` varchar(40) NOT NULL,
  `tacgia` varchar(40) NOT NULL,
  `theloai` varchar(30) NOT NULL,
  `gia` int(14) NOT NULL,
  `soluong` int(5) NOT NULL,
  `luotxem` int(10) NOT NULL DEFAULT 0,
  `luotmua` int(3) NOT NULL DEFAULT 0,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khosach`
--

INSERT INTO `khosach` (`book_id`, `book_name`, `tacgia`, `theloai`, `gia`, `soluong`, `luotxem`, `luotmua`, `image`) VALUES
('001', 'Java Sách', '006', '001', 123456, 1, 1, 1, '1.jpg'),
('002', 'Anh Văn', '003', '017', 111111, 222, 0, 0, 'Small14014.png'),
('003', 'Địa Lý', '003', '017', 111111, 222, 0, 0, 'Small14000.png'),
('004', 'Toán', '003', '004', 100000, 3, 0, 0, 'Small14034.png'),
('006', 'Lịch Sử', '001', '001', 100000, 2, 0, 0, 'Small14014.png');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `banned` int(2) NOT NULL DEFAULT 0,
  `joined_date` date NOT NULL DEFAULT current_timestamp(),
  `isAdmin` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `user_id`, `banned`, `joined_date`, `isAdmin`) VALUES
('admin', '1', '001', 0, '2025-02-07', 1),
('guest', '1', '003', 0, '2022-09-29', 0),
('khachhang', '1', '007', 0, '2025-02-08', 0),
('namnguyen', '1', '005', 0, '2024-12-30', 0),
('staff', '1', '002', 0, '2022-10-02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `id` varchar(5) NOT NULL,
  `time` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `user_id` varchar(5) DEFAULT NULL,
  `user` varchar(50) DEFAULT NULL,
  `type` varchar(20) NOT NULL DEFAULT '',
  `action` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `id_cmt` varchar(15) NOT NULL,
  `book_id` varchar(15) NOT NULL,
  `time` date NOT NULL DEFAULT current_timestamp(),
  `user` varchar(50) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `star` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`id_cmt`, `book_id`, `time`, `user`, `comment`, `star`) VALUES
('001', '001', '2022-10-10', '001', 'Sách này khá hay, nên đọc', 5),
('002', '001', '2022-10-10', '001', 'Sách này tốt, recommend nên mua', 4),
('003', '001', '2022-10-10', '001', 'Bìa cứng, đẹp lắm á', 4),
('004', '001', '2022-10-10', '001', 'Shipper hơi tệ, nhưng shop hỗ trợ tốt', 3),
('005', '001', '2022-10-10', '001', 'Shop giao sản phẩm lỗi', 1),
('006', '001', '2022-10-10', '001', 'Giao nhầm hàng nhưng không đổi trả', 2),
('007', '001', '2022-10-11', '003', 'Sách này cũng được', 4),
('008', '002', '2022-10-14', '003', 'Sách này nói chung cũng hay', 5),
('009', '002', '2022-10-14', '003', 'Sach nay hay', 4),
('010', '002', '2022-10-16', '003', 'Sach kha hay', 5),
('011', '002', '2022-10-16', '003', 'Sach hay nhieu nhieu', 4),
('012', '002', '2022-10-19', '003', 'sach hay', 5),
('013', '002', '2022-10-26', '003', 'sach ok', 5);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `ten` varchar(30) NOT NULL,
  `mota` varchar(200) NOT NULL,
  `hesoluong` float NOT NULL,
  `luongcoban` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ten`, `mota`, `hesoluong`, `luongcoban`) VALUES
('Author', 'Tác giả', 1, 10000000),
('Customer', 'Khách', 0, 0),
('Manager', 'Quản lý', 3, 10000000),
('Staff', 'Nhân Viên', 2, 5000000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(10) NOT NULL,
  `Ten` varchar(40) DEFAULT NULL,
  `Gioi tinh` varchar(5) DEFAULT NULL,
  `Ngay sinh` date DEFAULT NULL,
  `Dia chi` varchar(50) DEFAULT NULL,
  `Sdt` varchar(13) DEFAULT NULL,
  `CMND` varchar(20) DEFAULT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  `chucvu` varchar(30) DEFAULT NULL,
  `so_du` int(20) NOT NULL DEFAULT 100000
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `Ten`, `Gioi tinh`, `Ngay sinh`, `Dia chi`, `Sdt`, `CMND`, `avatar`, `chucvu`, `so_du`) VALUES
('001', 'Admin', 'Nam', '2003-01-01', '', '', '', 'avatar.png', 'Manager', 0),
('002', 'Nhân viên', 'Nam', '2003-11-01', 'Ha Dong', '0795155630', '123456', 'girl.jpg', 'Staff', 29999),
('003', 'Khách', 'Nu', '2003-12-05', 'Ca Mau', '01292388', '2xxxxxxxxxxxx', 'profile.jpg', 'Customer', 493660860),
('005', 'nam nguyen', 'Nam', '2003-01-01', '', '', '', 'avatar.png', 'Customer', 100000),
('006', 'Nam Nguyễn Văn', 'Nam', '2003-01-01', 'Viet Nam', '0123456789', '0xxxxxxx', 'avatar.png', 'Customer', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `version`
--

CREATE TABLE `version` (
  `version` varchar(100) NOT NULL,
  `detail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `version`
--

INSERT INTO `version` (`version`, `detail`) VALUES
('Version 1.0.0 (21/09/2024)', 'Triển khai Project và các base'),
('Version 1.0.1 (25/09/2024)', 'Thêm tính năng tự generate ID user'),
('Version 1.0.2 (28/09/2024)', 'Nâng cấp cho phép sửa thông tin sâu'),
('Version 1.0.2.a (01/10/2024)', 'Fix bug cũ'),
('Version 1.0.3 (04/10/2024)', 'Thêm tính năng cập nhật sách'),
('Version 1.0.4 (05/10/2024)', 'Thêm tính năng Preview ảnh khi upload'),
('Version 1.0.5 (07/10/2024)', 'Cập nhật tính năng nhật ký hệ thống'),
('Version 1.0.5 (09/10/2024)', 'Bổ sung 4 tính chất OOP'),
('Version 1.0.6 (10/10/2024)', 'Cập nhật tính năng rương đồ và giỏ hàng User'),
('Version 1.0.7 (10/10/2024)', 'Release tính năng mua sách trong hệ thống'),
('Version 1.0.8 (10/10/2024)', 'Release tính năng đánh giá sách trong hệ thống'),
('Version 1.0.9 (16/10/2024)', 'Release tính năng captcha xác thực người dùng'),
('Version 1.1.0 (18/10/2024)', 'Xây dựng panel thư viện');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`Ten`);

--
-- Indexes for table `danhmuc`
--
ALTER TABLE `danhmuc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `khosach`
--
ALTER TABLE `khosach`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`id_cmt`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ten`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `version`
--
ALTER TABLE `version`
  ADD PRIMARY KEY (`version`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
