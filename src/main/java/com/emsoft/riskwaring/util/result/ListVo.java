package com.emsoft.riskwaring.util.result;

import com.emsoft.riskwaring.util.CopyBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ListVo
 *
 * @author TangWeijie
 * @date 2019/1/24 11:33
 */
@ApiModel("通用翻页")
@Data
public class ListVo<RESULTS_TYPE> implements Serializable {

    @ApiModelProperty(value = "查询结果",required = true)
    private Collection<RESULTS_TYPE> results;
    @ApiModelProperty(value = "翻页信息",required = true)
    private PageInfoRespVo pageInfo;
    public ListVo(Collection<RESULTS_TYPE> results, PageInfoVo pageInfo) {
        this.results = results;

        this.pageInfo = CopyBean.simpleCopy(pageInfo,PageInfoRespVo.class);

    }
    public ListVo(Collection<RESULTS_TYPE> results, PageInfoRespVo pageInfo) {
        this.results = results;
        this.pageInfo = pageInfo;
    }

    public static  <T> ListVo<T> pageToListVo(Page<T> page) {
        PageInfoRespVo pageInfoVo = new PageInfoRespVo(page);


        return new ListVo<>(page.getContent(),pageInfoVo);
    }

    public <U>  ListVo<U> transType(Class<U> targetClass) {
        List<U> result = this.getResults().stream().map(x-> CopyBean.simpleCopy(x,targetClass)).collect(Collectors.toList());
        return new ListVo<>(result,CopyBean.simpleCopy(this.pageInfo,PageInfoRespVo.class));
    }
}
