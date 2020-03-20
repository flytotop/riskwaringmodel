package com.emsoft.riskwaring.util.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * PageInfoVo
 *
 * @author TangWeijie
 * @date 2019/1/24 11:08
 */

@Data
@ApiModel(description = "翻页接口")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Accessors(chain = true)
public class PageInfoVo implements Serializable {
    @ApiModelProperty(value = "页面大小", example = "20")
    private Integer pageSize = 20;
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageIndex = 1;

    public Pageable pageableEntity() {
        return PageRequest.of(pageIndex > 1 ? pageIndex - 1 : 0, pageSize);
    }

    public Pageable pageableEntity(String properties) {
        Sort sort = Sort.by(Sort.Direction.DESC, properties);
        return PageRequest.of(pageIndex > 1 ? pageIndex - 1 : 0, pageSize, sort);
    }

}