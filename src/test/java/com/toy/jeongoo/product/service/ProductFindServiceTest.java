package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductFindServiceTest {

    @Autowired
    private ProductFindService productFindService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("상품 아이디를 통해 해당 상품을 조회한다.")
    void findProduct() {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);

        //when
        final Product findProduct = productFindService.findProduct(product.getId());

        //then
        assertThat(findProduct.getId()).isEqualTo(product.getId());
    }

    @ParameterizedTest
    @DisplayName("모든 상품을 조회한다.")
    @CsvSource("2,3")
    void findAllProduct(int size) {
        //given
        final User user = saveUser();
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct(user);
        }

        //when
        final List<Product> allProduct = productFindService.findAllProduct();

        //then
        assertThat(allProduct.size()).isEqualTo(size);
    }

    @ParameterizedTest
    @DisplayName("특정 회원과 관련된 상품을 조회한다.")
    @CsvSource("2,3")
    void findAllProductByUser(int size) {
        //given
        final User user = saveUser();
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct(user);
        }

        //when
        final List<Product> allProduct = productFindService.findAllProductByUser(user.getId());

        //then
        assertThat(allProduct.size()).isEqualTo(size);
    }

    private User saveUser() {
        User user = User.builder()
                .name("user")
                .password("1234")
                .address(new Address("중구", "102호"))
                .email("test@test.com")
                .gender(Gender.MALE)
                .phoneNumber("010-1234-5678")
                .build();
        userRepository.save(user);

        return user;
    }

    private Product saveProduct(User user) {
        Product product = new Product("상품", 10000L, "12345",
                "좋은 상품", UseStatus.USED, user, new ArrayList<>());
        productRepository.save(product);

        return product;
    }
}