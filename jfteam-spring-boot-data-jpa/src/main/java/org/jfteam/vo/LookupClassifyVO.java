package org.jfteam.vo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午10:54
 */
@Entity
@Table(name = "sys_lookup_classify")
public class LookupClassifyVO extends BaseVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classifyId;
    private String classifyCode;
    private String classifyParentCode;
    private String name;
    private Integer status;
    private String classifyDesc;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyParentCode() {
        return classifyParentCode;
    }

    public void setClassifyParentCode(String classifyParentCode) {
        this.classifyParentCode = classifyParentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClassifyDesc() {
        return classifyDesc;
    }

    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc;
    }
}
