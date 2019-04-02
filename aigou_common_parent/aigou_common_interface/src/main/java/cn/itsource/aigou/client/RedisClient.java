package cn.itsource.aigou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = RedisClientFallbackFactory.class)//服务名
public interface RedisClient {

    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    void set(@RequestParam("key")String key, @RequestParam("value")String value);

    @RequestMapping(value = "/redis/{key}",method = RequestMethod.GET)
    String get(@PathVariable("key") String key);
}