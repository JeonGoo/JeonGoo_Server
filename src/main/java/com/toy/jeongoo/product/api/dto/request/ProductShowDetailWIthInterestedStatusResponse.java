package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.product.api.dto.response.ProductDetailDto;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.api.dto.response.UserShowResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductShowDetailWIthInterestedStatusResponse {

    private UserShowResponse userShowResponse;
    private ProductDetailDto productDetailDto;
    private int interestCount;
    private boolean isInterested;

    public ProductShowDetailWIthInterestedStatusResponse(Product product, boolean isInterested) {
        this.userShowResponse = new UserShowResponse(product.getUser());
        this.productDetailDto = new ProductDetailDto(product);
        this.interestCount = product.getInterestCount();
        this.isInterested = isInterested;
    }
}
