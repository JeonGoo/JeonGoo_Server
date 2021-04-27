package com.toy.jeongoo.product.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.jeongoo.file.dto.request.FileInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductRegistrationRequest;
import com.toy.jeongoo.product.model.status.UseStatus;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import com.toy.jeongoo.utils.DefaultResponse;
import com.toy.jeongoo.utils.ResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @DisplayName("상품을 등록한다.")
    public void registerTest() throws Exception {
        //given
        User user = User.builder()
                .name("user")
                .email("test@test.com")
                .password("1234")
                .build();
        userRepository.save(user);
        System.out.println(user.getId());

        final ProductBasicInfoRequest basicInfoRequest = new ProductBasicInfoRequest("product", 1000L, "2134", "good product", UseStatus.USED);
        final ProductRegistrationRequest registrationRequest = new ProductRegistrationRequest(basicInfoRequest, new FileInfoRequest());
        final String registrationRequestString = objectMapper.writeValueAsString(registrationRequest);

        //when
        final MvcResult mvcResult = mockMvc.perform(post("/api/v1/products/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(registrationRequestString))
                .andExpect(status().isOk())
                .andReturn();
        final DefaultResponse<Long> resultValue = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<DefaultResponse<Long>>() {
        });

        //then
        assertThat(resultValue.getMessage()).isEqualTo(ResponseMessage.REGISTER_PRODUCT);
    }

}