package com.example.iphone_web_be.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Đã xảy ra lỗi không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Khóa không hợp lệ", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Tên đăng nhập phải có ít nhất {min} ký tự", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Mật khẩu phải có ít nhất {min} ký tự", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Bạn chưa đăng nhập hoặc phiên đăng nhập đã hết hạn", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Bạn không có quyền thực hiện thao tác này", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Tuổi của bạn phải từ {min} trở lên", HttpStatus.BAD_REQUEST),
    PASSWORD_EXISTED(1010, "Mật khẩu mới không được trùng với mật khẩu hiện tại", HttpStatus.BAD_REQUEST),
    USERNAME_REQUIRED(1011, "Họ và tên không được để trống", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(1012, "Email không được để trống", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1013, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    PHONE_REQUIRED(1014, "Số điện thoại không được để trống", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(1015, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),
    CITIZEN_ID_REQUIRED(1016, "Số CCCD không được để trống", HttpStatus.BAD_REQUEST),
    CITIZEN_ID_INVALID(1017, "Số CCCD phải gồm 9 hoặc 12 chữ số", HttpStatus.BAD_REQUEST),
    PASSWORD_REQUIRED(1018, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1019, "Role khong ton tai", HttpStatus.BAD_REQUEST),
    USER_INFORMATION_INCOMPLETE(1020, "Thông tin khách hàng chưa đầy đủ", HttpStatus.BAD_REQUEST),
    ACCOUNT_EXISTED(1021, "Account khong ton tai!", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE(1022, "Số dư không đủ để thực hiện giao dịch", HttpStatus.BAD_REQUEST),
    OTP_INVALID(1023, "OTP không hợp lệ hoặc đã hết hạn", HttpStatus.BAD_REQUEST),
    TRANSFER_NOT_FOUND(1024, "Giao dịch chưa được khởi tạo hoặc đã hết hạn", HttpStatus.NOT_FOUND),
    TRANSFER_INVALID(1025, "Thông tin giao dịch không hợp lệ", HttpStatus.BAD_REQUEST),
    TRANSFER_EXPIRED(1026, "Transfer request has expired", HttpStatus.BAD_REQUEST),
    TRANSFER_ALREADY_COMPLETED(1027, "Transfer has already been completed", HttpStatus.BAD_REQUEST),
    TRANSFER_CANCELLED(1028, "Transfer has been cancelled", HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(1029, "OTP hết hạn", HttpStatus.BAD_REQUEST),
    INVALID_OTP(1030, "Người dùng nhập sai OTP", HttpStatus.BAD_REQUEST),
    OTP_MAX_ATTEMPT(1030, "Nhập sai quá số lần cho phép", HttpStatus.BAD_REQUEST),
    NEWS_NOT_FOUND(1031, "Tin tức không tồn tại", HttpStatus.NOT_FOUND),
    INVALID_DATE_RANGE(1032, "fromDate phải nhỏ hơn hoặc bằng toDate", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_EXISTED(1033, "Tài khoản không tồn tại", HttpStatus.NOT_FOUND),
    OTP_SEND_FAILED(1034, "Gửi mã OTP thất bại, vui lòng thử lại", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_NOT_FOUND(1035, "Danh mục không tồn tại", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1036, "Sản phẩm không tồn tại", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED(1037, "Sản phẩm hoặc mã slug đã tồn tại", HttpStatus.BAD_REQUEST),
    COLOR_NOT_FOUND(1038, "Màu sắc không tồn tại", HttpStatus.NOT_FOUND),
    STORAGE_NOT_FOUND(1039, "Dung lượng bộ nhớ không tồn tại", HttpStatus.NOT_FOUND),
    SKU_EXISTED(1040, "Mã SKU biến thể đã tồn tại", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED(1041, "Tên Category đã tồn tại!", HttpStatus.BAD_REQUEST),
    SLUG_EXISTED(1042, "Slug Category đã tồn tại!", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1043, "Category không tồn tại!", HttpStatus.NOT_FOUND),
    COLOR_EXISTED(1044, "Color đã tồn tại!", HttpStatus.BAD_REQUEST),
    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;
}
