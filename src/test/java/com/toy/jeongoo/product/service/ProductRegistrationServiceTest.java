package com.toy.jeongoo.product.service;

import com.toy.jeongoo.file.dto.request.FileInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRegistrationServiceTest {

    @Autowired
    private ProductRegistrationService productRegistrationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void init() {
        saveUser();
    }

    @Test
    @DisplayName("상품을 등록한다.")
    public void registerTest() throws Exception {
        //given
        ProductBasicInfoRequest basicInfoRequest = new ProductBasicInfoRequest("product", 10000L, "12345", "좋은 상품", UseStatus.USED);
        MultipartFile multipartFile = new MockMultipartFile("/file", "test.txt", null, new FileInputStream("/test"));
        FileInfoRequest fileInfoRequest = new FileInfoRequest(Arrays.asList(multipartFile), multipartFile);
        Long userId = user.getId();

        //when
        final Long registerProductId = productRegistrationService.register(basicInfoRequest, null, null, userId);
        final Product product = productRepository.findById(registerProductId).get();

        //then
        assertThat(product.getName()).isEqualTo(basicInfoRequest.getName());
        assertThat(product.getSerialNumber()).isEqualTo(basicInfoRequest.getSerialNumber());
        assertThat(product.getDescription()).isEqualTo(basicInfoRequest.getDescription());
    }

    private void saveUser() {
        user = User.builder()
                .email("test@test.com")
                .password("1234")
                .name("test user")
                .build();

        userRepository.save(user);
    }
}