package com.toy.jeongoo.product.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRegistrationRequest {

    private ProductBasicInfoRequest productBasicInfoRequest;
    private MediaInfoRequest mediaInfoRequest;
}
