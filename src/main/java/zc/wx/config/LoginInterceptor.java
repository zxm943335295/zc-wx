package zc.wx.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import zc.wx.bean.ConstantEnum;
import zc.wx.bean.ReturnBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器拦截没有加接口密钥
 * @author zm
 * @date 2021/2/26 16:17
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        try {
            String secretKey = request.getHeader(ConstantEnum.SECRET_KEY.getKey());
            if (secretKey == null) {
                secretKey = request.getParameter(ConstantEnum.SECRET_KEY.getKey());
            }
            if (StringUtils.isNotBlank(secretKey) && ConstantEnum.SECRET_KEY.getValue().equals(secretKey)) {
                return true;
            }
        } catch (Exception e) {
            log.error("拦截器错误:{}",e);
        }
        log.info(JSONObject.toJSONString(ReturnBean.error("密钥错误")));
        response.getWriter().println(JSONObject.toJSONString(ReturnBean.error("密钥错误")));
        return false;
    }


}