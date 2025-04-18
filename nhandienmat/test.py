import cv2
import pytesseract
import numpy as np

# (Nếu cần chỉ rõ đường dẫn Tesseract, thường không cần trên RPi)
# pytesseract.pytesseract.tesseract_cmd = r'/usr/bin/tesseract'

def detect_plate_text(frame):
    # Chuyển sang ảnh xám
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Làm mượt và tăng cường cạnh
    blur = cv2.bilateralFilter(gray, 11, 17, 17)
    edged = cv2.Canny(blur, 30, 200)

    # Tìm contours
    contours, _ = cv2.findContours(edged.copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    contours = sorted(contours, key=cv2.contourArea, reverse=True)[:10]

    plate_img = None
    for c in contours:
        approx = cv2.approxPolyDP(c, 10, True)
        if len(approx) == 4:  # Tìm vùng có hình chữ nhật (biển số thường là hình hộp)
            x, y, w, h = cv2.boundingRect(approx)
            plate_img = frame[y:y+h, x:x+w]
            cv2.rectangle(frame, (x, y), (x+w, y+h), (0,255,0), 2)
            break

    text = ""
    if plate_img is not None:
        plate_gray = cv2.cvtColor(plate_img, cv2.COLOR_BGR2GRAY)
        text = pytesseract.image_to_string(plate_gray, config='--psm 8')
        print("Biển số nhận dạng:", text.strip())

    return frame, text

# Mở camera
cap = cv2.VideoCapture(0)

print("Nhấn Q để thoát.")

while True:
    ret, frame = cap.read()
    if not ret:
        break

    frame, plate_text = detect_plate_text(frame)

    # Hiển thị kết quả
    cv2.imshow("Camera", frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
