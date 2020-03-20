package com.emsoft.riskwaring.dao.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Auther: xhf
 * @Date: 2020-03-16 19:49
 * @Description:
 */
@Entity
@Accessors(chain = true)
@Table(name = "MODELDICT")
//@UuidGenerator(name = "idGenerator")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ModelDictPo {
    /**
     * 主键uuid
     */
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
