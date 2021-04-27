package zc.wx.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zm
 * @date 2021/4/23 14:45
 */
@Data
@ApiModel(value = "维修订单审核参数",description = "维修订单审核参数")
public class RepairOrderCheckRequest {

    @ApiModelProperty(value = "维修订单id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "审核人",example = "lxy")
    private String reviewer;

    @ApiModelProperty(value = "审核人所在部门",example = "技术部")
    private String reviewerDepartment;


    @ApiModelProperty(value = "状态：1（提交）、2（部门领导不同意）3（部门领导同意）、\n" +
            "4（内部维修）、5（信息主任签字）、6（维修成功）、7（部门取回设备）、（4-7内部维修）\n" +
            "8（外送维修）、9（报价）、10（部门领导确认）、11（信息室签请款单）、12（部门取回设备）、（8-12内部维修）",example = "3")
    private Integer state;

    @ApiModelProperty(value = "审核意见",example = "可以进入下一流程")
    private String remark;

}