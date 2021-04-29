package com.toy.jeongoo.product.api.dto.response;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.api.dto.response.UserShowResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductShowResponse {

    private UserShowResponse userShowResponse;
    private ProductDetailDto productDetailDto;

    public ProductShowResponse(Product product) {
        this.userShowResponse = new UserShowResponse(product.getUser());
        this.productDetailDto = new ProductDetailDto(product);
    }
}
