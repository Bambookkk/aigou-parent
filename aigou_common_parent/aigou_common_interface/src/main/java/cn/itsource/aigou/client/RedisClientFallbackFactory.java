package cn.itsource.aigou.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisClientFallbackFactory implements FallbackFactory<RedisClient> {

    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public void set(String key, String value) {

            }

            @Override
            public String get(String key) {
                String msg = "此服务已被降级，请稍后再试！";
                return msg;
            }
        };
    }
}