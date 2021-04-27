package zc.wx.bean;

import lombok.Getter;

/**
 *
 * @author zm
 * @date 2021/4/23 15:03
 */
@Getter
public enum ConstantEnum {

    SECRET_KEY("secret_key", "XEp2!GO!uw7WYIxO5mjL&h4bkLofR45C");

    private String key;

    private String value;

    ConstantEnum(String key, String value){
        this.key = key;
        this.value = value;
    }
}
