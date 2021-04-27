package zc.wx.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zm
 * @date 2021/4/23 14:45
 */
@Data
@ApiModel(value = "维修订单列表查询",description = "维修订单列表查询")
public class RepairOrderListRequest {

    @ApiModelProperty(value = "申报人姓名",example = "zxm")
    private String name;

    @ApiModelProperty(value = "申报人部门",example = "技术部")
    private String department;

    @ApiModelProperty(value = "设备号",example = "99999")
    private String equipmentNum;

    @ApiModelProperty(value = "状态：1（提交）、2（部门领导不同意）3（部门领导同意）、\n" +
            "4（内部维修）、5（信息主任签字）、6（维修成功）、7（部门取回设备）、（4-7内部维修）\n" +
            "8（外送维修）、9（报价）、10（部门领导确认）、11（信息室签请款单）、12（部门取回设备）、（8-12内部维修）",example = "[1, 2, 3]")
    private List<Integer> stateList;

    @ApiModelProperty(value = "分页-页码",example = "1")
    private Integer page;

    @ApiModelProperty(value = "分页-每页容量",example = "10")
    private Integer size;

}