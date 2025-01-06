# Công cụ chuyển đổi cơ bản Quốc ngữ sang Hán Nôm
Dự án đầu tay bằng Java của Liu2k5, với đam mê về hệ thống chữ viết của ông cha.

# Tóm tắt
Chương trình có chức năng chuyển văn bản Quốc ngữ sang văn bản Hán Nôm một cách cơ bản.
Dữ liệu đầu vào là trường nhập văn bản Quốc ngữ, sau khi nhấn nút "Chuyển" ta nhận lại văn bản Hán Nôm tương ứng bên phải.

# Kết quả
![ảnh](https://github.com/user-attachments/assets/a0bd55c5-4998-4e91-991f-4b80b3abdaa2)


# Mã nguồn
- Word.java:  Xác định các thành phần cơ bản để xác định một "chữ", có thể là từ đơn.
- WordPair.java:  Đơn vị cặp chữ tạo nên từ ghép.
- WordGroup.java:  Là tập hợp của nhiều hơn hai "chữ", là từ ghép hoặc cụm từ.
- NumbersAndPunctuations:  Chuyển các dấu câu trong văn bản Quốc ngữ sang các dấu câu trong văn bản Hán Nôm tương ứng.
- TextProcessing:  Có chức năng xử lí văn bản gốc trở nên đơn giản cho việc xử lí của chương trình.
- Dictionary:  Đảm nhậm các công việc xử lí chính như nhập dữ liệu, phân tích, và dựa vào đó để chuyển đổi văn bản.
- View:  Giao diện đồ họa người dùng cho chương trình.
- Model:  Listenner cho lớp View.

# Dữ liệu đi kèm
- qngu.txt: Tệp chứa các văn bản Quốc ngữ đã được tách sẵn.
- hnom.txt: Tệp chứa các văn bản Hán Nôm tương ứng với mỗi đoạn văn bản Quốc ngữ.
Dữ liệu được tớ sưu tầm từ nhiều nguồn, và các văn bản được tớ xây dựng dựa trên việc chọn lọc chữ Hán/ Nôm sao cho có hệ thống, nhưng chưa thể gọi là "chuẩn hóa".
* Các nguồn tham khảo:
+ https://nomfoundation.org/?uiLang=vn: từ điển Hán Nôm
+ https://vi.wikisource.org/wiki/T%E1%BA%AFt_%C4%91%C3%A8n: tác phẩm Tắt đèn
+ https://www.youtube.com/watch?v=waQUvyJt3KM: bản dịch lời bài hát Last night, good night (kz - livetune)
+ https://www.youtube.com/watch?v=KjWUow-fIhg: bản dịch lời bài hát Leave (赤髪 / Akagami)
+ https://www.youtube.com/watch?v=N_jXg7UIc1I: song ngữ Nhật - Trung lời bài hát I wanna be your world (kz - livetune)
+ https://www.youtube.com/watch?v=u2Ix73ePWDs: lời bài hát Giấc mơ trưa (Thùy Chi)
+ https://www.youtube.com/watch?v=aY3BBUdoTDg: lời bài hát Tình ca mùa xuân (Anh Thơ)
+ https://www.youtube.com/watch?v=PYVmFEJQDKU: bản dịch lời bài hát Starduster (JimmyThumb-P)
+ https://vi.wikipedia.org/wiki/Th%E1%BB%9Di_ti%E1%BA%BFt: nội dung trang Wikipedia về Thời tiết
+ https://vi.wikipedia.org/wiki/Trung_t%C3%A2m_Th%C6%B0%C6%A1ng_m%E1%BA%A1i_Th%E1%BA%BF_gi%E1%BB%9Bi_(1973%E2%80%932001): nội dung trang Wikipedia về Trung tâm Thương mại Thế giới (1973–2001)
+ https://www.asuswebstorage.com/navigate/a/#/s/4188FA6CCDF04CA2B46AEDBE06CA9381Y: dữ liệu các từ trong tiếng Việt (Ủy ban phục sinh Hán Nôm Việt Nam)
+ https://github.com/ryanphung/chinese-hanviet-cognates/tree/master: dữ liệu các từ Hán Việt (ryanphung)

# "Lời sau cùng"
- Vì sự hạn chế về kĩ năng lập trình cũng như về thời gian của tớ, nên chương trình chưa có lượng thông tin đủ lớn, chưa đủ "thông minh" để xử lí chính xác toàn bộ văn bản. Tuy nhiên, tớ vẫn sẽ tiếp tục cố gắng để tiếp tục phát triển chương trình, cũng như xây dựng thêm những đoạn văn bản mẫu.
- Dù bạn là ai cũng có thể góp ý với tớ, về lỗi sai, hay thậm chí văn bản mẫu đều được hoan nghênh. Và cuối cùng cảm ơn các bạn đã sử dụng chương trình!
