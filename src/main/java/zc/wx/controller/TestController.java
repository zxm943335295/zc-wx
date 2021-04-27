package zc.wx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.wx.bean.ReturnBean;
import zc.wx.request.TestRequest;
import zc.wx.service.TestService;

/**
 * API 测试部分
 * @author zm
 * @date 2021/4/23 14:38
 */
@Api(tags = "测试模块")
@RestController
@RequestMapping("/api/test/v1")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation("添加测试对象")
    @PostMapping("add")
    public ReturnBean add(@ApiParam(value = "测试对象实体", required = true)@RequestBody TestRequest testRequest){
        if(testRequest == null){
            return ReturnBean.error("测试对象为空！");
        }
        ReturnBean result = testService.add(testRequest);
        return result;
    }

    @ApiOperation(value="查询测试对象")
    @PostMapping("list")
    public ReturnBean list() {
        return ReturnBean.ok(testService.list());
    }

}
