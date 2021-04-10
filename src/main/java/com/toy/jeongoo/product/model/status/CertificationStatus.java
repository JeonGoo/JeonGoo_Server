package com.toy.jeongoo.product.model.status;

public enum CertificationStatus {
    REQUEST("인증 요청"),
    COMPLETED("인증 완료"),
    FAILED("인증 실패"),
    RE_REQUEST("인증 재요청");

    private String message;

    CertificationStatus(String message) {
        this.message = message;
    }
}
