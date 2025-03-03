-- SELECT * FROM ql_nhahang.hoadonnhap;
USE ql_nhahang;
-- ALTER TABLE hoadonnhap DROP FOREIGN KEY MaNguyenLieu;
-- ALTER TABLE hoadonnhap DROP FOREIGN KEY MaNhaCungCap;
-- ALTER TABLE hoadonnhap
-- ADD CONSTRAINT fk_hoadonnhap_nhacungcap
-- FOREIGN KEY (MaNhaCungCap) 
-- REFERENCES nhacungcap(MaNhaCungCap);
ALTER TABLE hoadonnhap 
ADD CONSTRAINT fk_hoadonnhap_khonguyenlieu
FOREIGN KEY (MaNguyenLieu) 
REFERENCES khonguyenlieu(MaNguyenLieu);







