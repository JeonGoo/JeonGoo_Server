package com.toy.jeongoo.file.model;

import com.toy.jeongoo.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String path;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public File(String path, MediaType mediaType) {
        this.path = path;
        this.mediaType = mediaType;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
