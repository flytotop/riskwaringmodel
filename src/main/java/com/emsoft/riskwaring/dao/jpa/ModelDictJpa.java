package com.emsoft.riskwaring.dao.jpa;

import com.emsoft.riskwaring.dao.po.ModelDictPo;
import com.emsoft.riskwaring.dao.po.ModelPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 22:23
 * @Description:
 */
public interface ModelDictJpa   extends JpaRepository<ModelDictPo,String>, JpaSpecificationExecutor<ModelDictPo> {
    List<ModelDictPo> queryByModelCode(String modelCode);
}
