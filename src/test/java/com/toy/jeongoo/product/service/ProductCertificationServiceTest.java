package com.toy.jeongoo.product.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductCertificationServiceTest {

    @Autowired
    private ProductFindService productFindService;

    @Test
    @DisplayName("제품 인증 상태를 COMPLETED로 바꾼다.")
    public void certifyTest() throws Exception {
        //given


        //when

        //then
    }

    @Test
    @DisplayName("제품 인증 상태를 FAILED로 바꾼다.")
    public void failedTest() throws Exception {
        //given


        //when

        //then
    }

}