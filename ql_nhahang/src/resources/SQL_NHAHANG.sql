-- SELECT * FROM ql_nhahang.hoadonnhap;
USE ql_nhahang;
SELECT `ban`.`SoBan`,
    `ban`.`TinhTrangBan`,
    `ban`.`GhiChu`
FROM `ql_nhahang`.`ban`;
SELECT `hoadonbanhang`.`MaHoaDonBanHang`,
    `hoadonbanhang`.`TenMon`,
    `hoadonbanhang`.`GiaTien`,
    `hoadonbanhang`.`SoBan`,
    `hoadonbanhang`.`TongTienHoaDon`,
    `hoadonbanhang`.`MaMon`,
    `hoadonbanhang`.`GhiChu`
FROM `ql_nhahang`.`hoadonbanhang`;
SELECT `khonguyenlieu`.`MaNguyenLieu`,
    `khonguyenlieu`.`TenNguyenLieu`,
    `khonguyenlieu`.`MaNhaCungCap`,
    `khonguyenlieu`.`SoLuong`,
    `khonguyenlieu`.`GiaNhap`
FROM `ql_nhahang`.`khonguyenlieu`;
SELECT `menu`.`MaMon`,
    `menu`.`TenMon`,
    `menu`.`GiaTien`,
    `menu`.`TinhTrangMon`,
    `menu`.`SoLuong`
FROM `ql_nhahang`.`menu`;
SELECT `nhacungcap`.`MaNhaCungCap`,
    `nhacungcap`.`TenNhaCungCap`,
    `nhacungcap`.`LienHe`
FROM `ql_nhahang`.`nhacungcap`;
SELECT `nhanvien`.`MaNhanVien`,
    `nhanvien`.`TenNhanVien`,
    `nhanvien`.`ChucVu`,
    `nhanvien`.`NgaySinh`,
    `nhanvien`.`Luong1Gio`,
    `nhanvien`.`GioiTinh`,
    `nhanvien`.`SoDienThoai`
FROM `ql_nhahang`.`nhanvien`;
SELECT `tienluong`.`TenNhanVien`,
    `tienluong`.`TongTienLuong`,
    `tienluong`.`TinhTrangLuong`,
    `tienluong`.`NgayCong`,
    `tienluong`.`SoTienThanhToan`,
    `tienluong`.`MaNhanVien`,
    `tienluong`.`MaLuong`
FROM `ql_nhahang`.`tienluong`;








