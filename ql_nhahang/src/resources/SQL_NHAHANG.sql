USE ql_nhahang;
-- CREATE TABLE Ban (
--     SoBan INT PRIMARY KEY,
--     TinhTrangBan VARCHAR(50)
--     CONSTRAINT chkTinhTrangBan CHECK (TinhTrangBan IN ('Hết','Còn', 'Đã đặt'))
-- );
CREATE TABLE Menu (
    MaMon INT PRIMARY KEY,
    TenMon VARCHAR(100),
    GiaTien DECIMAL(10, 2),
    TinhTrangMon VARCHAR(50),
    SoLuong INT,
    CONSTRAINT chkTinhTrangMon CHECK(TinhTrangMon IN('Hết','còn'))
);
CREATE TABLE HoaDonBanHang (
    MaHoaDonBanHang INT PRIMARY KEY,
    MaMon INT,
    TenMon VARCHAR(100),
    GiaTien DECIMAL(10, 2),
    SoBan INT,
    TongTienHoaDon DECIMAL(10,2),
    FOREIGN KEY (MaMon) REFERENCES Menu(MaMon),
    FOREIGN KEY (SoBan) REFERENCES Ban(SoBan)
);
CREATE TABLE NhanVien (
    MaNhanVien INT PRIMARY KEY,
    TenNhanVien VARCHAR(100),
    ChucVu VARCHAR(50),
    NgaySinh DATE,
    Luong1Gio DECIMAL(10, 2)
);
CREATE TABLE TienLuong (
    MaNhanVien INT,
    TenNhanVien VARCHAR(100),
    TongTienLuong DECIMAL(10, 2),
    TinhTrangLuong VARCHAR(50),
    NgayCong INT,
    SoTienThanhToan DECIMAL(10, 2),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    CONSTRAINT chkTinhTrangLuong CHECK(TinhTrangLuong IN('Đã trả','Chưa trả'))
);
CREATE TABLE NhaCungCap (
    MaNhaCungCap INT PRIMARY KEY,
    TenNhaCungCap VARCHAR(100),
    LienHe VARCHAR(100)
);
CREATE TABLE KhoNguyenLieu (
    MaNguyenLieu INT PRIMARY KEY,
    TenNguyenLieu VARCHAR(100),
    MaNhaCungCap INT,
    SoLuong INT,
    GiaNhap DECIMAL(10, 2),
    FOREIGN KEY (MaNhaCungCap) REFERENCES NhaCungCap(MaNhaCungCap)
);
CREATE TABLE HoaDonNhap (
    MaHoaDonKho INT PRIMARY KEY,
    MaNguyenLieu INT,
    MaNhaCungCap INT,
    SoLuong INT,
    NgayNhap DATE,
    TongTienNhap DECIMAL(10, 2),
    FOREIGN KEY (MaNguyenLieu) REFERENCES KhoNguyenLieu(MaNguyenLieu),
    FOREIGN KEY (MaNhaCungCap) REFERENCES NhaCungCap(MaNhaCungCap)
);





