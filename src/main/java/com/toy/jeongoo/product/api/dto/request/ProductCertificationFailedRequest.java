package com.toy.jeongoo.product.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class ProductCertificationFailedRequest {

    @NotEmpty(message = "상품 인증 실패 사유를 알려줘야 합니다.")
    private String certificationFailedReason;

    public ProductCertificationFailedRequest(@NotEmpty String certificationFailedReason) {
        this.certificationFailedReason = certificationFailedReason;
    }
}
