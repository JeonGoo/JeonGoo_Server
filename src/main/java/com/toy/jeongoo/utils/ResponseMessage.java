package com.toy.jeongoo.utils;

public class ResponseMessage {
    public static String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static String VALUE_VALIDATION_ERROR = "값 검증 에러";

    // user
    public static String CREATE_USER = "회원 가입 성공";
    public static String CREATE_USER_FAIL = "회원 가입 실패";

    public static String LOGIN_USER = "로그인 성공";
    public static String LOGIN_USER_FAIL = "로그인 실패";

    public static final String FIND_USER = "회원 조회 성공";
    public static final String FIND_USER_FAIL = "회원 조회 실패";

    public static final String UPDATE_USER = "회원 수정 성공";
    public static final String UPDATE_USER_FAIL = "회원 수정 실패";

    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String DELETE_USER_FAIL = "회원 탈퇴 실패";

    // product
    public static String REGISTER_PRODUCT = "상품 등록 성공";
    public static String REGISTER_PRODUCT_FAIL = "상품 등록 실패";

    public static String CERTIFICATE_PRODUCT = "정품 인증 성공";
    public static String CERTIFICATE_PRODUCT_ERROR = "정품 인증 오류";

    public static String CERTIFICATE_PRODUCT_FAIL = "정품 인증 실패 알림 성공";
    public static String CERTIFICATE_PRODUCT_FAIL_ERROR = "정품 인증 실패 알림 오류";

    public static String SHOW_PRODUCT = "상품 조회 성공";
    public static String SHOW_PRODUCT_FAIL = "상품 조회 실패";

    public static String UPDATE_PRODUCT = "상품 수정 성공";
    public static String UPDATE_PRODUCT_FAIL = "상품 수정 실패";

    public static final String DELETE_PRODUCT = "상품 삭제 성공";
    public static final String DELETE_PRODUCT_FAIL = "상품 삭제 실패";

    // purchase
    public static final String PRODUCT_PURCHASE = "상품 구입 성공";
    public static final String PRODUCT_PURCHASE_FAIL = "상품 구입 실패";

    // interset product
    public static final String REGISTER_INTEREST_PRODUCT = "관심 상품 등록 성공";
    public static final String REGISTER_INTEREST_PRODUCT_FAIL = "관심 상품 등록 실패";

    public static final String CANCEL_INTEREST_PRODUCT = "관심 상품 취소 성공";
    public static final String CANCEL_INTEREST_PRODUCT_FAIL = "관심 상품 취소 실패";
}
