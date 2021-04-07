package com.toy.jeongoo.product;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.product.status.CertificationStatus;
import com.toy.jeongoo.product.status.UseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
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

    private String name;
    private String serialNumber;
    private String description;

    @Embedded
    private Money price;

    @Embedded
    private MediaInfo mediaInfo;

    @Enumerated(value = STRING)
    private UseStatus useStatus;

    @Enumerated(value = STRING)
    private CertificationStatus certificationStatus;

    @Enumerated(value = STRING)
    private ProductGrade grade;
}
