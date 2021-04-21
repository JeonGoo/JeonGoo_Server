package com.toy.jeongoo.product.model;

import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    @DisplayName("상품 인증이 완료되면 인증 상태가 COMPLETED로 바뀐다.")
    public void certifyTest() throws Exception {
        //given
        Product product = createProduct();

        //when
        product.certify(ProductGrade.HIGH);

        //then
        assertThat(product.getCertificationStatus()).isEqualTo(CertificationStatus.COMPLETED);
    }

    @Test
    @DisplayName("상품 인증시 이미 인증된 상품이면 오류가 발생한다.")
    public void certifyCanCertificationExceptionTest() throws Exception {
        //given
        Product product = createProduct();
        product.certify(ProductGrade.HIGH);

        //then
        assertThatThrownBy(() -> product.certify(ProductGrade.HIGH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the product has already been certify or failed to certification.");
    }

    @Test
    @DisplayName("상품 인증시 상품 등급이 NONE이면 오류 발생")
    public void certifyGradeIsNotNoneExceptionTest() throws Exception {
        //given
        Product product = createProduct();

        //then
        assertThatThrownBy(() -> product.certify(ProductGrade.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("product grade should not be none!");
    }

    @Test
    @DisplayName("상품 인증 상태를 FAILED로 바꾼다.")
    public void certifyFailedTest() throws Exception {
        //given
        Product product = createProduct();
        String certificationFailedReason = "정품 인증이 불가한 상품입니다.";

        //when
        product.certifyFailed(certificationFailedReason);

        //then
        assertThat(product.getCertificationStatus()).isEqualTo(CertificationStatus.FAILED);
        assertThat(product.getCertificationFailedReason()).isEqualTo(certificationFailedReason);
    }

    @Test
    @DisplayName("이미 인증이 실패한 상품일 경우 오류 발생")
    public void certifyFailedExceptionTest() throws Exception {
        //given
        Product product = createProduct();
        String certificationFailedReason = "정품 인증이 불가한 상품입니다.";

        //when
        product.certifyFailed(certificationFailedReason);

        //then
        assertThatThrownBy(() -> product.certifyFailed(certificationFailedReason))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the product has already been failed to certification.");
    }

    private Product createProduct() {
        User user = User.builder()
                .name("bang")
                .build();

        return new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());
    }
}