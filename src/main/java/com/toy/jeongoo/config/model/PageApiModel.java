package com.toy.jeongoo.config.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel
@Getter
@NoArgsConstructor
public class PageApiModel {

    @ApiModelProperty(value = "페이지 번호")
    private Integer page;

    @ApiModelProperty(value = "페이지 크기")
    private Integer size;

    @ApiModelProperty(value = "정렬(사용법: 컬럼명, ASC|DESC)")
    private List<String> sort;
}
