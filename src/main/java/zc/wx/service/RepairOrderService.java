package zc.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zc.wx.bean.ReturnBean;
import zc.wx.entity.RepairOrderDO;
import zc.wx.entity.RepairOrderProgressDO;
import zc.wx.mapper.RepairOrderMapper;
import zc.wx.mapper.RepairOrderProgressMapper;
import zc.wx.request.RepairOrderCheckRequest;
import zc.wx.request.RepairOrderListRequest;
import zc.wx.request.RepairOrderRequest;

import java.util.Date;
import java.util.List;

/**
 * @author zm
 * @date 2021/4/23 14:48
 */
@Service
public class RepairOrderService extends ServiceImpl<RepairOrderMapper, RepairOrderDO> {

    @Autowired
    private RepairOrderProgressMapper repairOrderProgressMapper;

    public ReturnBean addRepairOrder(RepairOrderRequest repairOrderRequest) {
        RepairOrderDO repairOrderDO = new RepairOrderDO();
        BeanUtils.copyProperties(repairOrderRequest, repairOrderDO);
        repairOrderDO.setState(1);
        repairOrderDO.setSubmitTime(new Date());
        boolean result = save(repairOrderDO);
        if(result){
            return ReturnBean.ok();
        } else {
            return ReturnBean.error("维修申请提交失败！");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ReturnBean checkRepairOrder(RepairOrderCheckRequest repairOrderCheckRequest) {
        RepairOrderDO repairOrderDO = new RepairOrderDO();
        repairOrderDO.setId(repairOrderCheckRequest.getId());
        repairOrderDO.setState(repairOrderCheckRequest.getState());
        boolean result = updateById(repairOrderDO);
        if(result){
            RepairOrderProgressDO repairOrderProgressDO = new RepairOrderProgressDO();
            repairOrderProgressDO.setRepairOrderId(repairOrderCheckRequest.getId());
            repairOrderProgressDO.setReviewer(repairOrderCheckRequest.getReviewer());
            repairOrderProgressDO.setReviewerDepartment(repairOrderCheckRequest.getReviewerDepartment());
            repairOrderProgressDO.setReviewTime(new Date());
            repairOrderProgressDO.setState(repairOrderCheckRequest.getState());
            repairOrderProgressDO.setRemark(repairOrderCheckRequest.getRemark());
            int row = repairOrderProgressMapper.insert(repairOrderProgressDO);
            if(row > 0){
                return ReturnBean.error("审核成功！");
            }
        }
        return ReturnBean.error("审核失败！");
    }

    public ReturnBean listRepairOrder(RepairOrderListRequest repairOrderListRequest) {
        LambdaQueryWrapper<RepairOrderDO> wrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(repairOrderListRequest.getName())){
            wrapper.like(RepairOrderDO::getName, repairOrderListRequest.getName());
        }
        if(StringUtils.isNotBlank(repairOrderListRequest.getDepartment())){
            wrapper.like(RepairOrderDO::getDepartment, repairOrderListRequest.getDepartment());
        }
        if(StringUtils.isNotBlank(repairOrderListRequest.getEquipmentNum())){
            wrapper.eq(RepairOrderDO::getEquipmentNum, repairOrderListRequest.getEquipmentNum());
        }
        if(repairOrderListRequest.getStateList() != null && repairOrderListRequest.getStateList().size() > 0){
            wrapper.in(RepairOrderDO::getState, repairOrderListRequest.getStateList());
        }
        if(repairOrderListRequest.getPage() == null && repairOrderListRequest.getSize() == null){
            IPage<RepairOrderDO> ipage= baseMapper.selectPage(new Page<>(repairOrderListRequest.getPage(),repairOrderListRequest.getSize()),wrapper);
            return ReturnBean.ok(ipage.getRecords());
        } else{
            return ReturnBean.ok(list(wrapper));
        }

    }

    public ReturnBean getRepairOrder(Integer id) {
        if (id != null){
            RepairOrderDO repairOrderDO = getById(id);
            List<RepairOrderProgressDO> list = repairOrderProgressMapper.selectList(new LambdaQueryWrapper<RepairOrderProgressDO>().eq(RepairOrderProgressDO::getRepairOrderId, id));
            JSONObject result = new JSONObject();
            result.put("repairOrder", repairOrderDO);
            result.put("repairOrderProgress", list);
            return ReturnBean.ok(result);
        }
        return ReturnBean.error("请查询正确的订单信息！");

    }
}
