package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.product.model.ProductGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class ProductCertificationRequest {

    @NotNull(message = "상품을 인증할 때 상품 등급을 입력해줘야 합니다.")
    private ProductGrade productGrade;

    public ProductCertificationRequest(@NotNull ProductGrade productGrade) {
        this.productGrade = productGrade;
    }
}
