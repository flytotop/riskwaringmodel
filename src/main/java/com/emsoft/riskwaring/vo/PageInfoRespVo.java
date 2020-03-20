package com.emsoft.riskwaring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * PageInfoVo
 *
 * @author TangWeijie
 * @date 2019/1/24 11:08
 */

@Data
@ApiModel("翻页接口返回")
@Accessors(chain = true)
public class PageInfoRespVo implements Serializable {
    @ApiModelProperty(value = "页面大小", example = "20")
    private Integer pageSize = 20;
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageIndex = 1;
    @ApiModelProperty(value = "总页数", example = "2")
    private Integer totalPage;
    @ApiModelProperty(value = "存在下页", example = "true", required = true)
    private Boolean more;
    @ApiModelProperty(value = "总数量", example = "1")
    private Long totalElement;

    public PageInfoRespVo() {
    }

    public PageInfoRespVo(Page<?> page) {
        this.more = page.hasNext();
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageIndex = page.getNumber()+1;
    }
}