package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.product.model.status.UseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductBasicInfoRequest {

    private String name;
    private Long price;
    private String serialNumber;
    private String description;
    private UseStatus useStatus;

    public ProductBasicInfoRequest(String name, Long price, String serialNumber, String description, UseStatus useStatus) {
        this.name = name;
        this.price = price;
        this.serialNumber = serialNumber;
        this.description = description;
        this.useStatus = useStatus;
    }
}
