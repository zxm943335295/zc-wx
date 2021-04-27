package zc.wx.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据返回Bean
 * @author zm
 * @date 2019/5/29 16:42
 */
@Data
public class ReturnBean<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ReturnBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @return ReturnBean
     */
    public static ReturnBean ok() {
        return new ReturnBean(ReturnCode.OK.getCode(), ReturnCode.OK.getMsg());
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @return ReturnBean
     */
    public static <T> ReturnBean ok(T data) {
        //如果data中返回数据为空时，则返回一个object对象
        if(data ==null){
            return new ReturnBean<>(ReturnCode.OK.getCode(), ReturnCode.OK.getMsg(), new Object());
        }else{
            return new ReturnBean<>(ReturnCode.OK.getCode(), ReturnCode.OK.getMsg(), data);
        }
    }

    /**
     * 失败
     *
     * @return ReturnBean
     */
    public static ReturnBean error() {
        return new ReturnBean(ReturnCode.ERROR.getCode(), ReturnCode.ERROR.getMsg());
    }

    /**
     * 自定义失败信息(自定义错误msg)
     *
     * @return ReturnBean
     */
    public static ReturnBean error(String msg) {
        return new ReturnBean(ReturnCode.ERROR.getCode(), msg);
    }

    /**
     * 参数错误
     *
     * @return ReturnBean
     */
    public static ReturnBean paramError(String msg) {
        return new ReturnBean(ReturnCode.INVALID_REQUEST.getCode(), msg);
    }

    /**
     * 自定义失败信息(使用全局的响应码和消息提示)
     * @param rc 错误码
     * @return ReturnBean
     */
    public static ReturnBean error(ReturnCode rc) {
        return new ReturnBean(rc.getCode(), rc.getMsg());
    }

    /**
     * 自定义失败信息
     *
     * @param code 错误码
     * @return ReturnBean
     */
    public static ReturnBean error(int code, String msg) {
        return new ReturnBean(code, msg);
    }

}
