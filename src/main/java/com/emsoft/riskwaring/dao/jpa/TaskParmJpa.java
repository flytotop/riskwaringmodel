package com.emsoft.riskwaring.dao.jpa;

import com.emsoft.riskwaring.dao.po.ModelPo;
import com.emsoft.riskwaring.dao.po.TaskParmPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 15:20
 * @Description: 模型参数
 */
public interface TaskParmJpa  extends JpaRepository<TaskParmPo,String>, JpaSpecificationExecutor<TaskParmPo> {
    void deleteByModelCode(String modelCode);
    List<TaskParmPo> queryByModelCode(String modelCode);
}
