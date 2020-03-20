package com.emsoft.riskwaring.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Auther: xhf
 * @Date: 2020-03-17 15:12
 * @Description: 模型任务参数
 */
@Entity
@Accessors(chain = true)
@Table(name = "TASKPARM")
//@UuidGenerator(name = "idGenerator")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TaskParmPo {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    @Column(name = "MODELCODE", nullable = false, length = 36)
    private String modelCode;

    @Column(name = "PARAMEYERNAME", nullable = false, length = 36)
    private String parameterName;

    @Column(name = "CHINESEEXPLAIN", nullable = false, length = 36)
    private String chineseExplain;

    @Column(name = "TYPE", nullable = false, length = 36)
    private String type;

    @Column(name = "REQUIRED", nullable = false, length = 1)
    private boolean required;
}
