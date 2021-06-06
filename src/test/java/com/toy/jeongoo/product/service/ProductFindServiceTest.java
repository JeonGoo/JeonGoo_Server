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
import org.springframework.data.domain.PageRequest;

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
    void findProductTest() {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);

        //when
        final Product findProduct = productFindService.findProduct(product.getId());

        //then
        assertThat(findProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    @DisplayName("상품 아이디를 통해 해당 상품을 상세 조회한다. 이때 조회 횟수가 늘어난다.")
    void showProductDetailTest() throws Exception {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);

        //when
        final Product findProduct = productFindService.showProductDetail(product.getId());

        //then
        assertThat(findProduct.getHitCount()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("모든 상품을 조회한다.")
    @CsvSource("2,3")
    void findAllProductPageTest(int size) {
        //given
        final User user = saveUser();
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct(user);
        }

        //when
        final List<Product> allProduct = productFindService.findAllProductPage(PageRequest.of(0, size));

        //then
        assertThat(allProduct.size()).isEqualTo(size);
    }

    @ParameterizedTest
    @DisplayName("특정 회원과 관련된 상품을 조회한다.")
    @CsvSource("2,3")
    void findAllProductByUserTest(int size) {
        //given
        final User user = saveUser();
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct(user);
        }

        //when
        final List<Product> allProduct = productFindService.findAllProductByUser(user);

        //then
        assertThat(allProduct.size()).isEqualTo(size);
    }

    @Test
    @DisplayName("판매중인 상품을 조회한다.")
    public void showAllSaleProductsPageTest() throws Exception {
        //given
        final User user = saveUser();
        saveProduct(user);

        //when
        final List<Product> productList = productFindService.showAllSaleProductsPage(PageRequest.of(0, 5));

        //then
        assertThat(productList.size()).isEqualTo(1);
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