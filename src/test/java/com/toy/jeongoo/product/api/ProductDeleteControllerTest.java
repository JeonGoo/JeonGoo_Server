package com.toy.jeongoo.product.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import com.toy.jeongoo.utils.DefaultResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductDeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User user;
    private Product product;

    @BeforeEach
    public void setUp() {
        init();
    }

    @Test
    @DisplayName("상품 삭제")
    public void deleteTest() throws Exception {
        //when
        final MvcResult result = mockMvc.perform(delete("/api/v1/products/" + product.getId()))
                .andExpect(status().isOk())
                .andReturn();
        final DefaultResponse<Long> resultValue = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DefaultResponse<Long>>() {
        });
        final Optional<Product> deletedProduct = productRepository.findById(resultValue.getData());

        //then
        assertThat(deletedProduct.isPresent()).isFalse();
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