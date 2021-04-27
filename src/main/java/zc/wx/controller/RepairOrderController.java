package zc.wx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.wx.bean.ReturnBean;
import zc.wx.request.RepairOrderCheckRequest;
import zc.wx.request.RepairOrderListRequest;
import zc.wx.request.RepairOrderRequest;
import zc.wx.service.RepairOrderService;

/**
 * @author zm
 * @date 2021/4/25 11:33
 */
@Api(tags = "维修订单接口")
@RestController
@RequestMapping("/api/repair_order/v1")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @ApiOperation("添加维修订单")
    @PostMapping("add")
    public ReturnBean add(@ApiParam(value = "维修订单提交参数", required = true)@RequestBody RepairOrderRequest repairOrderRequest){
        return repairOrderService.addRepairOrder(repairOrderRequest);
    }

    @ApiOperation(value="审核维修订单")
    @PostMapping("check")
    public ReturnBean check(@ApiParam(value = "审核维修订单参数", required = true)@RequestBody RepairOrderCheckRequest repairOrderCheckRequest) {
        return repairOrderService.checkRepairOrder(repairOrderCheckRequest);
    }

    @ApiOperation(value="查询维修订单列表")
    @PostMapping("list")
    public ReturnBean list(@ApiParam(value = "查询维修订单列表参数", required = true)@RequestBody RepairOrderListRequest repairOrderListRequest) {
        return repairOrderService.listRepairOrder(repairOrderListRequest);
    }

    @ApiOperation(value="查询维修订单详情")
    @PostMapping("detail")
    @ApiImplicitParam(paramType = "query",name = "id" , value = "维修订单id" , required = true, dataType = "Integer")
    public ReturnBean detail(Integer id) {
        return repairOrderService.getRepairOrder(id);
    }
}