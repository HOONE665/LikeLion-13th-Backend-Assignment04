package com.likelion.likelionassignmentcrud.commom.template;

import com.likelion.likelionassignmentcrud.commom.error.ErrorCode;
import com.likelion.likelionassignmentcrud.commom.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResTemplate<T> {

    private final int code; //응답코드
    private final String message; //응답매세지
    private T data; // 응답데이터

    //데이터없는성공
    public static ApiResTemplate successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(),successCode.getMessage());
    }
    //데이터있는성공
    public static <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data)  {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }
    //ERROR
    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage);
    }
}