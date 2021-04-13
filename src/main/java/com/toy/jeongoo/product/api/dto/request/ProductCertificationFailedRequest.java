package com.toy.jeongoo.product.api.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCertificationFailedRequest {

    @NotNull
    private String certificationFailedReason;
}
