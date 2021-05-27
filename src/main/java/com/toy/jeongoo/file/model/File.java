package com.toy.jeongoo.file.model;

import com.toy.jeongoo.common.entity.BaseTimeEntity;
import com.toy.jeongoo.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String path;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public File(String path, FileType fileType) {
        this.path = path;
        this.fileType = fileType;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
