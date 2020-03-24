package com.emsoft.riskwaring.config;

import io.searchbox.annotations.JestId;
import lombok.Data;

/**
 * @Auther: xhf
 * @Date: 2020-03-23 14:28
 * @Description:
 */
@Data
public class EsBlog {
    @JestId  // 主键
    private String id;
    private Long blogId;
    private String title;
    private String summary;
    private String content;
}
