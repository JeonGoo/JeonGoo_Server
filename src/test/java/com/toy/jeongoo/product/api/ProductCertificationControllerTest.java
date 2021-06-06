package com.toy.jeongoo.product.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.toy.jeongoo.utils.DefaultResponse;
import com.toy.jeongoo.utils.StatusCode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductCertificationControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품 인증 성공")
    @Transactional
    public void certifyTest() throws Exception {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);
        ProductGradeUpdateRequest certificationRequest = new ProductGradeUpdateRequest(ProductGrade.HIGH);
        final String certificationRequestString = objectMapper.writeValueAsString(certificationRequest);

        //then
        mockMvc.perform(put("/api/v1/products/" + product.getId() + "/certification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(certificationRequestString))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 인증시 이미 인증됐거나 실패된 상품이면 오류 발생")
    @Transactional
    public void certifyTest_error() throws Exception {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);
        product.certify();

        ProductGradeUpdateRequest certificationRequest = new ProductGradeUpdateRequest(ProductGrade.NONE);
        final String certificationRequestString = objectMapper.writeValueAsString(certificationRequest);

        //when
        final MvcResult result = mockMvc.perform(put("/api/v1/products/" + product.getId() + "/certification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(certificationRequestString))
                .andReturn();

        final DefaultResponse<Long> resultValue = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DefaultResponse<Long>>() {
        });

        //then
        assertThat(resultValue.getStatusCode()).isEqualTo(StatusCode.BAD_REQUEST);
    }

    @Test
    @DisplayName("상품 인증상태를 FAILED로 바꾼다.")
    @Transactional
    public void certifyFailedTest() throws Exception {
        //given
        final User user = saveUser();
        final Product product = saveProduct(user);
        ProductCertificationFailedRequest certificationFailedRequest = new ProductCertificationFailedRequest("고유한 시리얼 넘버가 아닙니다.");
        final String certificationFailedRequestString = objectMapper.writeValueAsString(certificationFailedRequest);

        //when
        final MvcResult result = mockMvc.perform(put("/api/v1/products/" + product.getId() + "/certification/failure")
                .contentType(MediaType.APPLICATION_JSON)
                .content(certificationFailedRequestString))
                .andExpect(status().isOk())
                .andReturn();

        final DefaultResponse<Long> resultValue = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DefaultResponse<Long>>() {
        });
        final Product actualProduct = productRepository.findById(resultValue.getData()).get();

        //then
        assertThat(actualProduct.getCertificationStatus()).isEqualTo(CertificationStatus.FAILED);
        assertThat(actualProduct.getCertificationFailedReason()).isEqualTo(certificationFailedRequest.getCertificationFailedReason());
    }

    private User saveUser() {
        User user = User.builder()
                .name("user")
                .password("1234")
                .address(new Address("중구", "102호"))
                .email("testUser@test.com")
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