package com.toy.jeongoo.product.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.jeongoo.product.api.dto.response.ProductDetailDto;
import com.toy.jeongoo.product.api.dto.response.ProductShowResponse;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import com.toy.jeongoo.utils.DefaultResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductFindControllerTest {

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
        saveUser();
    }

    @AfterEach
    public void teardown() {
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 아이디로 해당 상품을 조회한다.")
    public void findProductTest() throws Exception {
        //given
        saveProduct();
        long productId = product.getId();

        //when
        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/" + productId))
                .andExpect(status().isOk())
                .andReturn();
        final DefaultResponse<ProductShowResponse> resultValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<DefaultResponse<ProductShowResponse>>() {
        });

        //then
        assertThat(resultValue.getData().getProductDetailDto().getId()).isEqualTo(productId);

    }

    @ParameterizedTest
    @DisplayName("모든 상품을 조회한다.")
    @CsvSource("2,3")
    public void findAllProductsTest(int size) throws Exception {
        //given
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct();
        }

        //when
        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andReturn();
        final DefaultResponse<List<ProductShowResponse>> resultValues = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<DefaultResponse<List<ProductShowResponse>>>() {
                });

        //then
        assertThat(resultValues.getData().size()).isEqualTo(size);
    }

    @ParameterizedTest
    @DisplayName("특정 회원과 관련된 상품을 모두 조회한다.")
    @CsvSource("2,3")
    public void findAllProductsByUser(int size) throws Exception {
        //given
        for (int cnt = 0; cnt < size; cnt++) {
            saveProduct();
        }

        //when
        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/products/users/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn();
        final DefaultResponse<List<ProductShowResponse>> resultValues = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<DefaultResponse<List<ProductShowResponse>>>() {
                });

        //then
        assertThat(resultValues.getData().size()).isEqualTo(size);
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