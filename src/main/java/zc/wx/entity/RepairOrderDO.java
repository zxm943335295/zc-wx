package zc.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zm
 * @date 2021/4/23 14:25
 */
@Data
@TableName("repair_order")
public class RepairOrderDO {
    private Integer id;
    /**
     * 申报人姓名
     */
    private String name;
    /**
     * 部门
     */
    private String department;
    /**
     * 所在地址
     */
    private String address;
    /**
     * 设备号
     */
    private String equipmentNum;
    /**
     * 损坏详细信息
     */
    private String damage;
    /**
     * 状态：1（提交）、2（部门领导不同意）3（部门领导同意）、
     * 4（内部维修）、5（信息主任签字）、6（维修成功）、7（部门取回设备）、（4-7内部维修）
     * 8（外送维修）、9（报价）、10（部门领导确认）、11（信息室签请款单）、12（部门取回设备）、（8-12内部维修）
     */
    private Integer state;
    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;
}
