package zc.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zc.wx.mapper")
public class ZxWxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxWxApplication.class, args);
    }

}
