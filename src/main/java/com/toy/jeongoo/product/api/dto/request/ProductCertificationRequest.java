package com.toy.jeongoo.product.api.dto.request;

import com.sun.istack.NotNull;
import com.toy.jeongoo.product.model.ProductGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCertificationRequest {

    @NotNull
    private ProductGrade productGrade;

    public ProductCertificationRequest(ProductGrade productGrade) {
        this.productGrade = productGrade;
    }
}
