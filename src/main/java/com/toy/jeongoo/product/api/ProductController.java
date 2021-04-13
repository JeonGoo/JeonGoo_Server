package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.MediaInfoDto;
import com.toy.jeongoo.product.api.dto.request.MediaInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.api.dto.request.ProductCertificationRequest;
import com.toy.jeongoo.product.api.dto.request.ProductRegistrationRequest;
import com.toy.jeongoo.product.service.ProductCertificationService;
import com.toy.jeongoo.product.service.ProductRegistrationService;
import com.toy.jeongoo.upload.FileUploadService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRegistrationService registrationService;
    private final ProductCertificationService certificationService;
    private final FileUploadService fileUploadService;

    @PostMapping("/users/{userId}")
    public DefaultResponse<Long> register(@RequestBody ProductRegistrationRequest productRequest,
                                          @PathVariable Long userId) {
        try {
            final MediaInfoDto mediaInfoDto = createMediaInfoDto(productRequest.getMediaInfoRequest());
            final Long registrationId = registrationService.register(productRequest.getProductBasicInfoRequest(), mediaInfoDto, userId);
            return DefaultResponse.res(CREATED, REGISTER_PRODUCT, registrationId);
        } catch (Exception productRegistrationException) {
            log.error(productRegistrationException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, REGISTER_PRODUCT_FAIL);
        }
    }

    @PutMapping("/{productId}/certification")
    public DefaultResponse<Long> certify(@PathVariable Long productId,
                                         @RequestBody ProductCertificationRequest certificationRequest) {
        try {
            final Long certificationProduct = certificationService.certify(productId, certificationRequest);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT, certificationProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_ERROR);
        }
    }

    @PutMapping("/{productId}/certification/failure")
    public DefaultResponse<Long> certifyFailed(@PathVariable Long productId,
                                               @RequestBody ProductCertificationFailedRequest certificationFailedRequest) {
        try {
            Long certificationFailedProduct = certificationService.failed(productId, certificationFailedRequest);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT_FAIL, certificationFailedProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_FAIL_ERROR);
        }
    }

    private MediaInfoDto createMediaInfoDto(MediaInfoRequest mediaInfoRequest) {
        List<String> imageFilePaths = mediaInfoRequest.getImageFiles().stream()
                .map(fileUploadService::upload)
                .collect(Collectors.toList());

        String videoFilePath = fileUploadService.upload(mediaInfoRequest.getVideoFile());
        return new MediaInfoDto(imageFilePaths, videoFilePath);
    }
}
