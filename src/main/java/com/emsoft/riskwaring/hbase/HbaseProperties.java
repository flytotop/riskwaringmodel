package com.emsoft.riskwaring.hbase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
//@Component
//@ConfigurationProperties(prefix = "hbase")
public class HbaseProperties {
    private Map<String, String> config;
}
