package com.toy.jeongoo.product.api.dto.request;

import com.toy.jeongoo.file.dto.request.FileInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    private ProductBasicInfoRequest productBasicInfoRequest;
    private FileInfoRequest fileInfoRequest;
}
