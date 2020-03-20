package com.emsoft.riskwaring.dao.jpa;

import com.emsoft.riskwaring.dao.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:23
 * @Description:
 */
public interface  ModelJpa extends JpaRepository<ModelPo,String>, JpaSpecificationExecutor<ModelPo> {
    void deleteByModelCode(String modelCode);

}
