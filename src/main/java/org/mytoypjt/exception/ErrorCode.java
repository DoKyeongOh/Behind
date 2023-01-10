package org.mytoypjt.exception;

public enum ErrorCode {

    // 로그인 관련
    ACCOUNT_IS_NOT_EXIST,

    // 프로필 관련
    PROFILE_IS_NOT_EXIST,

    // 인증 관련
    EMAIL_IS_NOT_EXIST,
    CERT_VALUE_IS_NOT_CORRECT,
    CERT_VALUE_IS_NULL,
    CERT_INPUT_IS_NULL,
    CERT_FAILURE,

    // 비밀번호 변경 관련
    INPUT_PW_IS_NULL,
    INPUT_PW_CHECK_IS_NULL,

    // 로그인 세션 관련
    ACCOUNT_IS_ALREADY_LOGIN

}
