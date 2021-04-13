package com.toy.jeongoo.product.model;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static com.toy.jeongoo.product.model.status.CertificationStatus.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "description")
    private String description;

    @Embedded
    @Column(nullable = false)
    private Money price;

    @Embedded
    private MediaInfo mediaInfo;

    @Enumerated(value = STRING)
    private UseStatus useStatus;

    @Enumerated(value = STRING)
    private CertificationStatus certificationStatus;

    private String certificationFailedReason;

    @Enumerated(value = STRING)
    private ProductGrade grade;

    public Product(String name, Long price, String serialNumber, String description, UseStatus useStatus,
                   List<String> imagePaths, String videoPath, User user) {
        this.name = name;
        this.price = Money.of(price);
        this.serialNumber = serialNumber;
        this.description = description;
        this.useStatus = useStatus;
        this.mediaInfo = new MediaInfo(imagePaths.get(0), videoPath);
        this.certificationStatus = REQUEST;
        this.grade = ProductGrade.NONE;
        this.user = user;
    }

    public void certify(ProductGrade grade) {
        checkCanCertification();
        this.certificationStatus = COMPLETED;
        this.grade = grade;
    }

    public void certifyFailed(String certificationFailedReason) {
        checkAlreadyCertifyFailed();
        this.certificationStatus = FAILED;
        this.certificationFailedReason = certificationFailedReason;
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