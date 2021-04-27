package zc.wx.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zm
 * @date 2021/4/23 14:45
 */
@Data
@ApiModel(value = "测试对象",description = "新增测试对象")
public class TestRequest {

    /**
     * 收发货人姓名
     */
    @ApiModelProperty(value = "用户名",example = "zxm")
    private String name;

    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "密码",example = "123456")
    private String password;

}