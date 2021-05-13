package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.product.model.ProductGrade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
public class ProductGradeUpdateRequest {

    @NotNull(message = "상품 등급을 입력해줘야 합니다.")
    private ProductGrade productGrade;

    public ProductGradeUpdateRequest(@NotNull ProductGrade productGrade) {
        this.productGrade = productGrade;
    }
}
