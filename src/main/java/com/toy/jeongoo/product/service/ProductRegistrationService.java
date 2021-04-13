package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.api.dto.MediaInfoDto;
import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductRegistrationService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long register(ProductBasicInfoRequest productBasicInfo, MediaInfoDto mediaInfoDto, Long userId) {
        final User user = findUserById(userId);
        final Product product = new Product(productBasicInfo.getName(), productBasicInfo.getPrice(), productBasicInfo.getSerialNumber(), productBasicInfo.getDescription(),
                productBasicInfo.getUseStatus(), mediaInfoDto.getImagePaths(), mediaInfoDto.getVideoPath(), user);
        productRepository.save(product);

        return product.getId();
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found user. input id: %d", id)));
    }
}
