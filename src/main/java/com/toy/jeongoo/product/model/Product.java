package com.toy.jeongoo.product.model;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.SalesStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.toy.jeongoo.product.model.status.CertificationStatus.*;
import static com.toy.jeongoo.product.model.status.SalesStatus.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product", fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterestProduct> interestProductList = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "hit_count", nullable = false)
    private long hitCount;

    @Embedded
    @Column(nullable = false)
    private Money price;

    @Enumerated(value = STRING)
    private UseStatus useStatus;

    @Enumerated(value = STRING)
    private CertificationStatus certificationStatus;

    @Enumerated(value = STRING)
    private SalesStatus salesStatus;

    private String certificationFailedReason;

    @Enumerated(value = STRING)
    private ProductGrade grade;

    public Product(String name, Long price, String serialNumber, String description, UseStatus useStatus,
                   User user, List<File> fileList) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.description = description;
        this.price = Money.of(price);
        this.hitCount = 0L;
        this.useStatus = useStatus;
        this.certificationStatus = REQUEST;
        this.grade = ProductGrade.NONE;
        this.user = user;
        this.salesStatus = SALE;
        changeFileList(fileList);
    }

    public void certify() {
        checkCanCertification();
        this.certificationStatus = COMPLETED;
    }

    public void certifyFailed(String certificationFailedReason) {
        checkAlreadyCertifyFailed();
        this.certificationStatus = FAILED;
        this.certificationFailedReason = certificationFailedReason;
    }

    public void update(String name, Long price, String serialNumber, String description, UseStatus useStatus, List<File> fileList) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.description = description;
        this.price = Money.of(price);
        this.useStatus = useStatus;
        this.certificationStatus = REQUEST;
        this.grade = ProductGrade.NONE;
        changeFileList(fileList);
    }

    public void hit() {
        this.hitCount++;
    }

    public boolean isSoldOut() {
        return this.salesStatus.equals(SOLD_OUT);
    }

    public void soldOut() {
        this.salesStatus = SOLD_OUT;
    }

    public void addInterestProduct(InterestProduct interestProduct) {
        this.interestProductList.add(interestProduct);
    }

    public int getInterestCount() {
        return interestProductList.size();
    }

    public void changeGrade(ProductGrade productGrade) {
        checkGradeIsNotNone(productGrade);
        this.grade = productGrade;
    }

    private void checkGradeIsNotNone(ProductGrade grade) {
        if (grade.equals(ProductGrade.NONE)) {
            throw new IllegalArgumentException("product grade should not be none!");
        }
    }

    private void changeFileList(List<File> fileList) {
        if (!this.fileList.isEmpty()) {
            this.fileList.clear();
        }
        this.fileList.addAll(fileList);
        for (File file : fileList) {
            file.setProduct(this);
        }
    }

    private void checkCanCertification() {
        if (this.certificationStatus == COMPLETED || this.certificationStatus == FAILED) {
            throw new IllegalArgumentException("the product has already been certify or failed to certification.");
        }
    }

    private void checkAlreadyCertifyFailed() {
        if (this.certificationStatus == FAILED) {
            throw new IllegalArgumentException("the product has already been failed to certification.");
        }
    }
}
