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
INSERT INTO `ban` VALUES (1,'Trống','');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
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
INSERT INTO `hoadonbanhang` VALUES ('1',1,'1'),('2',1,''),('3',1,'1'),('4',1,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadonbanhangchitiet`
--

LOCK TABLES `hoadonbanhangchitiet` WRITE;
/*!40000 ALTER TABLE `hoadonbanhangchitiet` DISABLE KEYS */;
INSERT INTO `hoadonbanhangchitiet` VALUES (1,'1','1',1,23),(3,'1','2',1,2),(4,'1','3',1,2),(5,'1','4',1,2);
/*!40000 ALTER TABLE `hoadonbanhangchitiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadonnhap`
--

DROP TABLE IF EXISTS `hoadonnhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadonnhap` (
  `MaHoaDonKho` varchar(50) NOT NULL,
  `MaNguyenLieu` varchar(50) DEFAULT NULL,
  `MaNhaCungCap` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MaHoaDonKho`),
  KEY `fk_hoadonnhap_nhacungcap` (`MaNhaCungCap`),
  KEY `fk_hoadonnhap_khonguyenlieu` (`MaNguyenLieu`),
  CONSTRAINT `fk_hoadonnhap_khonguyenlieu` FOREIGN KEY (`MaNguyenLieu`) REFERENCES `khonguyenlieu` (`MaNguyenLieu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_hoadonnhap_nhacungcap` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_khonguyenlieu` FOREIGN KEY (`MaNguyenLieu`) REFERENCES `khonguyenlieu` (`MaNguyenLieu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_nhacungcap` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadonnhap`
--

LOCK TABLES `hoadonnhap` WRITE;
/*!40000 ALTER TABLE `hoadonnhap` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadonnhap` ENABLE KEYS */;
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
INSERT INTO `khonguyenlieu` VALUES ('1','1','11',1.00,1);
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
INSERT INTO `menu` VALUES ('1','1',1.00,'Còn',1);
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
INSERT INTO `nhacungcap` VALUES ('1','1','1'),('11','111','11');
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
  `chucVu` enum('Quản lý','Phục vụ','Thu ngân','Bảo vệ') DEFAULT NULL,
  `Luong1Gio` decimal(10,2) DEFAULT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `SoDienThoai` varchar(10) NOT NULL,
  `Tuoi` int DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `tienluong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-02 11:52:48
