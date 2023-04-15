package cn.itcast.order.config;

import cn.itcast.order.pojo.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Order test() {
        return new Order();
    }
}
