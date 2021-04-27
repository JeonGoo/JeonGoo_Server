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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductDeleteServiceTest {

    @Autowired
    private ProductDeleteService productDeleteService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("특정 상품을 삭제한다.")
    public void deleteTest() throws Exception {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);

        //when
        final Long deleteId = productDeleteService.delete(product.getId());
        final Optional<Product> deletedProduct = productRepository.findById(deleteId);

        //then
        assertThat(deletedProduct.isPresent()).isFalse();
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