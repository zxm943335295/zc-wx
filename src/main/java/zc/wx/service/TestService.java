package zc.wx.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import zc.wx.bean.ReturnBean;
import zc.wx.entity.TestDO;
import zc.wx.mapper.TestMapper;
import zc.wx.request.TestRequest;

/**
 * @author zm
 * @date 2021/4/23 14:48
 */
@Service
public class TestService extends ServiceImpl<TestMapper, TestDO> {

    public ReturnBean add(TestRequest testRequest) {
        TestDO testDO = new TestDO();
        BeanUtils.copyProperties(testRequest, testDO);
        int rows = baseMapper.insert(testDO);
        if(rows > 0){
            return ReturnBean.ok();
        }
        return ReturnBean.error("添加测试样例失败！");
    }


}
