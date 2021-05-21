package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.product.model.status.UseStatus;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ProductBasicInfoRequest {

    @ApiParam(name = "상품 이름")
    private String name;

    @ApiParam(name = "상품 가격")
    private Long price;

    @ApiParam(name = "상품 고유 번호")
    private String serialNumber;

    @ApiParam(name = "상품 설명")
    private String description;

    @ApiParam(name = "상품 상태(USED: 중고, DISUSED: 새상품)")
    private UseStatus useStatus;

    public ProductBasicInfoRequest(String name, Long price, String serialNumber, String description, UseStatus useStatus) {
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber;
        this.description = description;
        this.useStatus = useStatus;
    }
}
