package com.toy.jeongoo.product.model;

import com.toy.jeongoo.product.model.status.CertificationStatus;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    @DisplayName("상품 인증이 완료되면 인증 상태가 COMPLETED로 바뀐다.")
    public void certifyTest() throws Exception {
        //given
        User user = User.builder()
                .name("bang")
                .build();
        Product product = new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());

        //when
        product.certify(ProductGrade.HIGH);

        //then
        assertThat(product.getCertificationStatus()).isEqualTo(CertificationStatus.COMPLETED);
    }

    @ParameterizedTest
    @DisplayName("상품 인증시 이미 인증된 상품이거나 인증이 실패된 상품이면 오류가 발생한다.")
    @CsvSource("COMPLETED, FAILED")
    public void certifyCanCertificationException(CertificationStatus certificationStatus) throws Exception {
        //given
        User user = User.builder()
                .name("bang")
                .build();
        Product product = new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());
        product.certify(ProductGrade.HIGH);

        //then
        assertThatThrownBy(() -> product.certify(ProductGrade.HIGH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("the product has already been certify or failed to certification.");
    }

    @Test
    @DisplayName("상품 인증시 상품 등급이 NONE이면 오류 발생")
    public void certifyGradeIsNotNoneException() throws Exception {
        //given
        User user = User.builder()
                .name("bang")
                .build();
        Product product = new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());

        //then
        assertThatThrownBy(() -> product.certify(ProductGrade.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("product grade should not be none!");
    }
}