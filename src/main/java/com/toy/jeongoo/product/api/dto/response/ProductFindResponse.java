package com.toy.jeongoo.product.api.dto.response;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.file.dto.FileDetailDto;
import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.ProductGrade;
import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductFindResponse {

    private Long id;
    private String name;
    private String description;
    private Money price;
    private UseStatus useStatus;
    private CertificationStatus certificationStatus;
    private String certificationFailedReason;
    private ProductGrade productGrade;
    private List<FileDetailDto> fileList;

    public ProductFindResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.useStatus = product.getUseStatus();
        this.certificationStatus = product.getCertificationStatus();
        this.certificationFailedReason = product.getCertificationFailedReason();
        this.productGrade = product.getGrade();
        this.fileList = createdFileList(product.getFileList());
    }

    private List<FileDetailDto> createdFileList(List<File> fileList) {
        return fileList.stream()
                .map(FileDetailDto::new)
                .collect(Collectors.toList());
    }
}
