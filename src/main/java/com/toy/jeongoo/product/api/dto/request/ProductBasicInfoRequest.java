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
}
