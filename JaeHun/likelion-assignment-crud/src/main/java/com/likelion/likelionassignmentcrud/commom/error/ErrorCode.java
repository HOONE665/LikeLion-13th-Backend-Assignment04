package com.likelion.likelionassignmentcrud.commom.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    //404 NOT_FOUND
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다.", "NOT_FOUND_404"),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다.", "NOT_FOUND_404"),
    MENU_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 메뉴가 없습니다.", "NOT_FOUND_404"),
    RESTAURANT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 식당이 없습니다.", "NOT_FOUND_404"),

    //400 BAD_REQUEST
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패했습니다.", "BAD_REQUEST_400"),

    //500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR_500"),;


    private final HttpStatus httpStatus;
    private final String message;
    private final String code;



    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}