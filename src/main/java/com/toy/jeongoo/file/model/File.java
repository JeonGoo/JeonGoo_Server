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

    @Column(name = "media_type")
    private MediaType mediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("product_id")
    private Product product;

    public File(String path, MediaType mediaType, Product product) {
        this.path = path;
        this.mediaType = mediaType;
        this.product = product;
    }
}
