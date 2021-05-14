package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.ProductGrade;
import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductCertificationServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCertificationService productCertificationService;

    private User user;
    private Product product;

    @BeforeEach
    public void setUp() {
        init();
    }

    @Test
    @DisplayName("제품 인증 상태를 COMPLETED로 바꾼다.")
    public void certifyTest() throws Exception {
        //given
        ProductGrade productGrade = ProductGrade.INTERMEDIATE;
        ProductGradeUpdateRequest certificationRequest = new ProductGradeUpdateRequest(productGrade);
        Long productId = product.getId();

        //when
        final Long certifyProductId = productCertificationService.certify(productId, certificationRequest);
        final Product product = productRepository.findById(certifyProductId).get();

        //then
        assertThat(product.getCertificationStatus()).isEqualTo(CertificationStatus.COMPLETED);
        assertThat(product.getGrade()).isEqualTo(productGrade);
    }

    @Test
    @DisplayName("제품 인증 상태를 FAILED로 바꾼다.")
    public void failedTest() throws Exception {
        //given
        String failedReason = "올바른 상품이 아닙니다.";
        ProductCertificationFailedRequest certificationFailedRequest = new ProductCertificationFailedRequest(failedReason);
        Long productId = product.getId();

        //when
        final Long failedProductId = productCertificationService.failed(productId, certificationFailedRequest);
        final Product product = productRepository.findById(failedProductId).get();

        //then
        assertThat(product.getCertificationStatus()).isEqualTo(CertificationStatus.FAILED);
        assertThat(product.getCertificationFailedReason()).isEqualTo(failedReason);

    }

    private void init() {
        saveUser();
        saveProduct();
    }

    private void saveUser() {
        user = User.builder()
                .name("user")
                .password("1234")
                .address(new Address("중구", "102호"))
                .email("test@test.com")
                .gender(Gender.MALE)
                .phoneNumber("010-1234-5678")
                .build();
        userRepository.save(user);
    }

    private void saveProduct() {
        product = new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());
        productRepository.save(product);
    }
}