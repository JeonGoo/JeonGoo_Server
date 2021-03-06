package com.toy.jeongoo.product.api.dto.response;

import com.toy.jeongoo.file.dto.FileDetailDto;
import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.ProductGrade;
import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.SalesStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductDetailDto {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private long hitCount;
    private UseStatus useStatus;
    private CertificationStatus certificationStatus;
    private String certificationFailedReason;
    private ProductGrade productGrade;
    private SalesStatus salesStatus;
    private List<FileDetailDto> fileList;

    public ProductDetailDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice().getValue();
        this.hitCount = product.getHitCount();
        this.useStatus = product.getUseStatus();
        this.certificationStatus = product.getCertificationStatus();
        this.certificationFailedReason = product.getCertificationFailedReason();
        this.productGrade = product.getGrade();
        this.salesStatus = product.getSalesStatus();
        this.fileList = createdFileList(product.getFileList());
    }

    private List<FileDetailDto> createdFileList(List<File> fileList) {
        return fileList.stream()
                .map(FileDetailDto::new)
                .collect(Collectors.toList());
    }
}
