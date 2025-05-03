import cv2
import os

# Kiểm tra nếu thư mục "test" không tồn tại thì tạo
if not os.path.exists("test"):
    os.makedirs("test")

# Mở webcam
cap = cv2.VideoCapture(0)

# Đọc một frame từ webcam
ret, frame = cap.read()

# Nếu có thể đọc được frame, lưu ảnh vào thư mục "test"
if ret:
    cv2.imwrite("test/OIP.jpg", frame)

# Giải phóng webcam
cap.release()
