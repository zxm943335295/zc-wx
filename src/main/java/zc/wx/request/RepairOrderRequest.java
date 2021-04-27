package zc.wx.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zm
 * @date 2021/4/23 14:45
 */
@Data
@ApiModel(value = "维修订单实体类",description = "维修订单实体类")
public class RepairOrderRequest {

    @ApiModelProperty(value = "申报人姓名",example = "zxm")
    private String name;

    @ApiModelProperty(value = "申报人部门",example = "技术部")
    private String department;

    @ApiModelProperty(value = "部门所在地址",example = "主楼三楼技术部")
    private String address;

    @ApiModelProperty(value = "设备号",example = "99999")
    private String equipmentNum;

    @ApiModelProperty(value = "损坏程度",example = "CPU损坏")
    private String damage;

}