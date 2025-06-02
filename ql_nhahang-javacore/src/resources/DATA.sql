-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ql_nhahang
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `SoBan` int NOT NULL,
  `TinhTrangBan` enum('Trống','Đã đặt','Đang phục vụ') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GhiChu` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SoBan`),
  CONSTRAINT `chkTinhTrangBan` CHECK ((`TinhTrangBan` in (_utf8mb4'Trống',_utf8mb4'Đã đặt',_utf8mb4'Đang phục vụ')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES (1,'Trống','Bàn gần cửa sổ'),(2,'Đang phục vụ','Bàn gần cửa ra vào'),(3,'Đã đặt','Bàn gần nhà bếp'),(4,'Trống','Bàn gần quầy bar'),(5,'Đang phục vụ','Bàn gần nhà vệ sinh');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chebienmon`
--

DROP TABLE IF EXISTS `chebienmon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chebienmon` (
  `MaCheBien` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaMon` varchar(100) DEFAULT NULL,
  `MaHoaDonBanHang` varchar(100) DEFAULT NULL,
  `TrangThai` enum('Chưa bắt đầu','Đang nấu','Hoàn thành','Lỗi') DEFAULT NULL,
  PRIMARY KEY (`MaCheBien`),
  KEY `MaMon` (`MaMon`),
  KEY `MaHoaDonBanHang` (`MaHoaDonBanHang`),
  CONSTRAINT `chebienmon_ibfk_1` FOREIGN KEY (`MaMon`) REFERENCES `menu` (`MaMon`),
  CONSTRAINT `chebienmon_ibfk_2` FOREIGN KEY (`MaHoaDonBanHang`) REFERENCES `hoadonbanhang` (`MaHoaDonBanHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chebienmon`
--

LOCK TABLES `chebienmon` WRITE;
/*!40000 ALTER TABLE `chebienmon` DISABLE KEYS */;
INSERT INTO `chebienmon` VALUES ('CB001','M001','HD001','Đang nấu'),('CB002','M002','HD002','Hoàn thành'),('CB003','M003','HD003','Đang nấu'),('CB004','M004','HD004','Hoàn thành'),('CB005','M005','HD005','Đang nấu');
/*!40000 ALTER TABLE `chebienmon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `congthucmonan`
--

DROP TABLE IF EXISTS `congthucmonan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `congthucmonan` (
  `MaMon` varchar(100) NOT NULL,
  `MaNguyenLieu` varchar(50) NOT NULL,
  `SoLuong` float DEFAULT NULL,
  PRIMARY KEY (`MaMon`,`MaNguyenLieu`),
  KEY `MaNguyenLieu` (`MaNguyenLieu`),
  CONSTRAINT `congthucmonan_ibfk_1` FOREIGN KEY (`MaMon`) REFERENCES `menu` (`MaMon`),
  CONSTRAINT `congthucmonan_ibfk_2` FOREIGN KEY (`MaNguyenLieu`) REFERENCES `khonguyenlieu` (`MaNguyenLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `congthucmonan`
--

LOCK TABLES `congthucmonan` WRITE;
/*!40000 ALTER TABLE `congthucmonan` DISABLE KEYS */;
INSERT INTO `congthucmonan` VALUES ('M001','NL001',0.5),('M001','NL002',1),('M002','NL003',0.3),('M003','NL004',0.25),('M003','NL005',0.15);
/*!40000 ALTER TABLE `congthucmonan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadonbanhang`
--

DROP TABLE IF EXISTS `hoadonbanhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadonbanhang` (
  `MaHoaDonBanHang` varchar(100) NOT NULL,
  `SoBan` int DEFAULT NULL,
  `GhiChu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MaHoaDonBanHang`),
  KEY `fkSoBan` (`SoBan`),
  CONSTRAINT `fkSoBan` FOREIGN KEY (`SoBan`) REFERENCES `ban` (`SoBan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hoadonbanhang_ibfk_2` FOREIGN KEY (`SoBan`) REFERENCES `ban` (`SoBan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadonbanhang`
--

LOCK TABLES `hoadonbanhang` WRITE;
/*!40000 ALTER TABLE `hoadonbanhang` DISABLE KEYS */;
INSERT INTO `hoadonbanhang` VALUES ('HD001',1,'Đã thanh toán'),('HD002',2,'Chưa thanh toán'),('HD003',3,'Đã thanh toán'),('HD004',4,'Chưa thanh toán'),('HD005',5,'Đã thanh toán');
/*!40000 ALTER TABLE `hoadonbanhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadonbanhangchitiet`
--

DROP TABLE IF EXISTS `hoadonbanhangchitiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadonbanhangchitiet` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `MaMon` varchar(100) DEFAULT NULL,
  `MaHoaDonBanHang` varchar(100) DEFAULT NULL,
  `SoBan` int DEFAULT NULL,
  `SoLuongDat` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_MaMon` (`MaMon`),
  KEY `fk_MaHoaDonBanHang` (`MaHoaDonBanHang`),
  KEY `fk_SoBan` (`SoBan`),
  CONSTRAINT `fk_MaHoaDonBanHang` FOREIGN KEY (`MaHoaDonBanHang`) REFERENCES `hoadonbanhang` (`MaHoaDonBanHang`),
  CONSTRAINT `fk_MaMon` FOREIGN KEY (`MaMon`) REFERENCES `menu` (`MaMon`),
  CONSTRAINT `fk_SoBan` FOREIGN KEY (`SoBan`) REFERENCES `ban` (`SoBan`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadonbanhangchitiet`
--

LOCK TABLES `hoadonbanhangchitiet` WRITE;
/*!40000 ALTER TABLE `hoadonbanhangchitiet` DISABLE KEYS */;
INSERT INTO `hoadonbanhangchitiet` VALUES (1,'M001','HD001',1,2);
/*!40000 ALTER TABLE `hoadonbanhangchitiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khonguyenlieu`
--

DROP TABLE IF EXISTS `khonguyenlieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khonguyenlieu` (
  `MaNguyenLieu` varchar(50) NOT NULL,
  `TenNguyenLieu` varchar(100) DEFAULT NULL,
  `MaNhaCungCap` varchar(50) DEFAULT NULL,
  `GiaTien` decimal(10,2) DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  PRIMARY KEY (`MaNguyenLieu`),
  KEY `fk_khoanguyenlieu_nhacungcap` (`MaNhaCungCap`),
  CONSTRAINT `fk_khoanguyenlieu_nhacungcap` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE CASCADE,
  CONSTRAINT `fk_khonguyenlieu_nhacungcap` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khonguyenlieu`
--

LOCK TABLES `khonguyenlieu` WRITE;
/*!40000 ALTER TABLE `khonguyenlieu` DISABLE KEYS */;
INSERT INTO `khonguyenlieu` VALUES ('NL001','Thịt bò','NCC001',120000.00,15),('NL002','Bún','NCC002',20000.00,50),('NL003','Gạo','NCC003',15000.00,100),('NL004','Rau cải','NCC004',25000.00,20),('NL005','Hương liệu','NCC005',30000.00,10);
/*!40000 ALTER TABLE `khonguyenlieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `MaMon` varchar(100) NOT NULL,
  `TenMon` varchar(100) DEFAULT NULL,
  `GiaTien` decimal(10,2) DEFAULT NULL,
  `TinhTrangMon` enum('Còn','Hết') DEFAULT NULL,
  `SoLuong` int DEFAULT NULL,
  PRIMARY KEY (`MaMon`),
  CONSTRAINT `chkTinhTrangMon` CHECK ((`TinhTrangMon` in (_utf8mb4'Hết',_utf8mb4'còn')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('M001','Phở',50000.00,'Còn',100),('M002','Bún bò Huế',60000.00,'Còn',80),('M003','Cơm tấm',45000.00,'Còn',120),('M004','Bánh mì',30000.00,'Còn',150),('M005','Gỏi cuốn',35000.00,'Còn',90);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `MaNhaCungCap` varchar(50) NOT NULL,
  `TenNhaCungCap` varchar(100) DEFAULT NULL,
  `LienHe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MaNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('NCC001','Công ty Thịt Hà Nội','0123456789'),('NCC002','Công ty Bún Huế','0987654321'),('NCC003','Công ty Gạo Việt','0345678910'),('NCC004','Công ty Rau Sạch','0567891234'),('NCC005','Công ty Hương Liệu','0789123456');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` varchar(100) NOT NULL,
  `TenNhanVien` varchar(100) DEFAULT NULL,
  `ChucVu` enum('Đầu bếp','Phục vụ','Thu ngân','Quản lý','Bảo vệ') DEFAULT NULL,
  `Luong1Gio` decimal(10,2) DEFAULT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `SoDienThoai` varchar(10) NOT NULL,
  `Tuoi` int DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  CONSTRAINT `chk_chuc_vu` CHECK ((`ChucVu` in (_utf8mb4'Đầu bếp',_utf8mb4'Phục vụ',_utf8mb4'Thu ngân',_utf8mb4'Quản lý',_utf8mb4'Bảo vệ')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('NV001','Nguyễn Văn A','Đầu bếp',80000.00,'Nam','0123456789',23),('NV002','Trần Văn B','Đầu bếp',80000.00,'Nam','0237651892',25),('NV003','Nguyễn Tuyết C','Thu ngân',30000.00,'Nữ','0362916481',24),('NV004','Trần Minh D','Phục vụ',200000.00,'Nam','0372615819',20),('NV005','Nguyễn Huy E','Quản lý',100000.00,'Nam','0372615834',24),('NV006','Trần Hào F','Bảo vệ',150000.00,'Nam','0372615839',30);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phancongbep`
--

DROP TABLE IF EXISTS `phancongbep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phancongbep` (
  `MaCheBien` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `MaNhanVien` varchar(100) NOT NULL,
  `VaiTro` enum('Chính','Phụ') DEFAULT NULL,
  PRIMARY KEY (`MaCheBien`,`MaNhanVien`),
  KEY `MaNhanVien` (`MaNhanVien`),
  CONSTRAINT `phancongbep_ibfk_1` FOREIGN KEY (`MaCheBien`) REFERENCES `chebienmon` (`MaCheBien`),
  CONSTRAINT `phancongbep_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phancongbep`
--

LOCK TABLES `phancongbep` WRITE;
/*!40000 ALTER TABLE `phancongbep` DISABLE KEYS */;
INSERT INTO `phancongbep` VALUES ('CB001','NV001','Chính'),('CB002','NV002','Phụ');
/*!40000 ALTER TABLE `phancongbep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienluong`
--

DROP TABLE IF EXISTS `tienluong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tienluong` (
  `MaLuong` varchar(100) NOT NULL,
  `MaNhanVien` varchar(100) DEFAULT NULL,
  `TenNhanVien` varchar(100) DEFAULT NULL,
  `TinhTrangLuong` enum('Đã trả','Chưa trả') DEFAULT NULL,
  `GioLamViec` int DEFAULT NULL,
  `GhiChu` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MaLuong`),
  KEY `fk_nhanvien` (`MaNhanVien`),
  CONSTRAINT `fk_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tienluong_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienluong`
--

LOCK TABLES `tienluong` WRITE;
/*!40000 ALTER TABLE `tienluong` DISABLE KEYS */;
INSERT INTO `tienluong` VALUES ('TL001','NV001','Nguyễn Văn A','Đã trả',160,'Hoàn thành tháng 4'),('TL002','NV002','Trần Văn B','Đã trả',160,'Hoàn thành tháng 4'),('TL003','NV003','Nguyễn Tuyết C','Đã trả',160,'Hoàn thành tháng 4'),('TL004','NV004','Trần Minh D','Đã trả',160,'Hoàn thành tháng 4'),('TL005','NV005','Nguyễn Huy E','Đã trả',160,'Hoàn thành tháng 4'),('TL006','NV006','Trần Hào F','Đã trả',160,'Hoàn thành tháng 4');
/*!40000 ALTER TABLE `tienluong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tonkho`
--

DROP TABLE IF EXISTS `tonkho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tonkho` (
  `MaNguyenLieu` varchar(50) NOT NULL,
  `SoLuongTon` int DEFAULT NULL,
  `NgayCapNhat` date DEFAULT NULL,
  PRIMARY KEY (`MaNguyenLieu`),
  CONSTRAINT `tonkho_ibfk_1` FOREIGN KEY (`MaNguyenLieu`) REFERENCES `khonguyenlieu` (`MaNguyenLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tonkho`
--

LOCK TABLES `tonkho` WRITE;
/*!40000 ALTER TABLE `tonkho` DISABLE KEYS */;
INSERT INTO `tonkho` VALUES ('NL001',15,'2025-04-18'),('NL002',50,'2025-04-18'),('NL003',100,'2025-04-18'),('NL004',20,'2025-04-18'),('NL005',10,'2025-04-18');
/*!40000 ALTER TABLE `tonkho` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-19  0:39:05
