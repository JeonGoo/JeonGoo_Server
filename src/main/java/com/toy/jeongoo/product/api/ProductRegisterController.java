package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.service.ProductRegistrationService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRegisterController {

    private final ProductRegistrationService registrationService;

    @PostMapping("/users/{userId}")
    public DefaultResponse<Long> register(@PathVariable Long userId,
                                          ProductBasicInfoRequest productBasicInfoRequest,
                                          @RequestPart(name = "imageFile", required = false) List<MultipartFile> imageFiles,
                                          @RequestPart(name = "videoFile", required = false) MultipartFile videoFile) {
        try {
            final Long registrationId = registrationService.register(productBasicInfoRequest, imageFiles, videoFile, userId);
            return DefaultResponse.res(CREATED, REGISTER_PRODUCT, registrationId);
        } catch (Exception productRegistrationException) {
            log.error(productRegistrationException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, REGISTER_PRODUCT_FAIL);
        }
    }
}
