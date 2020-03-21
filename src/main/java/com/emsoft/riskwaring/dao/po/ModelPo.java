package com.emsoft.riskwaring.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 19:23
 * @Description: 模型字典
 */
@Entity
@Accessors(chain = true)
@Table(name = "MODEL_MSG")
//@UuidGenerator(name = "idGenerator")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ModelPo implements Serializable {
    /**
     * 主键uuid
     */
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    //模型编码
    @Column(name = "MODEL_CODE", nullable = true ,length = 36)
    private String modelCode;

    @Column(name = "MODEL_NAME", nullable = true ,length = 36)
    private String modelName;

    //任务参数
    @Column(name = "TASK_PARM", nullable = true ,length = 255)
    private String taskParm;

    //结果字典
    @Column(name = "RESULT_DICT", nullable = true ,length = 255)
    private String resultDict;
}
