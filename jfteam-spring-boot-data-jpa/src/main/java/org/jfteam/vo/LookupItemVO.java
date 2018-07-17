package org.jfteam.vo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-17
 * Time: 下午11:08
 */
@Entity
@Table(name = "sys_lookup_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LookupItemVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemCode;
    private String itemName;
    private String itemDesc;
    private Integer status;
    private Integer itemIndex;
    private String classifyCode;
    private String parentCode;
    private String classifyParentCode;
    private String classifyParentName;

    private String itemAttr1;
    private String itemAttr2;
    private String itemAttr3;
    private String itemAttr4;
    private String itemAttr5;
    private String itemAttr6;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getClassifyParentCode() {
        return classifyParentCode;
    }

    public void setClassifyParentCode(String classifyParentCode) {
        this.classifyParentCode = classifyParentCode;
    }

    public String getClassifyParentName() {
        return classifyParentName;
    }

    public void setClassifyParentName(String classifyParentName) {
        this.classifyParentName = classifyParentName;
    }

    public String getItemAttr1() {
        return itemAttr1;
    }

    public void setItemAttr1(String itemAttr1) {
        this.itemAttr1 = itemAttr1;
    }

    public String getItemAttr2() {
        return itemAttr2;
    }

    public void setItemAttr2(String itemAttr2) {
        this.itemAttr2 = itemAttr2;
    }

    public String getItemAttr3() {
        return itemAttr3;
    }

    public void setItemAttr3(String itemAttr3) {
        this.itemAttr3 = itemAttr3;
    }

    public String getItemAttr4() {
        return itemAttr4;
    }

    public void setItemAttr4(String itemAttr4) {
        this.itemAttr4 = itemAttr4;
    }

    public String getItemAttr5() {
        return itemAttr5;
    }

    public void setItemAttr5(String itemAttr5) {
        this.itemAttr5 = itemAttr5;
    }

    public String getItemAttr6() {
        return itemAttr6;
    }

    public void setItemAttr6(String itemAttr6) {
        this.itemAttr6 = itemAttr6;
    }
}
