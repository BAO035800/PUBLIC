Quy trình hoạt động của code và ứng dụng (chạy code ở view/main):

Khách đến thu ngân sẽ nhập vào hóa đơn bán hàng để tạo hóa đơn bán hàng ở trạng thái chưa thanh toán

Sau đó vào hóa đơn chi tiết để nhập số bàn và mã món ăn cùng với số lượng trên menu :
+ Bàn sẽ từ trạng thái trống sang đang phục vụ
+ Số lượng món ăn ở menu sẽ bị trừ đi và khi về 0 nó là trạng thái hết
+ Tiền sẽ tự động tính ở hóa đơn chi tiết và sẽ tự động cộng vào tổng tiền ở hóa đơn bán hàng
+ Trường hợp đặt 1 bàn nhưng bàn đấy ở hóa đơn khác đang phục vụ và chưa thanh toán sẽ báo lỗi

 Sau khi cập nhật ở hóa đơn chi tiết sẽ đưa vào bếp ở bảng chế biến món ăn:
+ Ấn refresh sẽ cập nhật các món ăn ,hóa đơn và số bàn ở những hóa đơn chưa thanh toán
+ Bao giờ bếp xong sẽ chỉnh trạng thái thành đã hoàn thành

 Các trạng thái tính tiền tổng được tự động ở mỗi bảng
