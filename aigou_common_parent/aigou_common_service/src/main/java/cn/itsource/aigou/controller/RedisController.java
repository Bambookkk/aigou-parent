package cn.itsource.aigou.controller;

import cn.itsource.aigou.client.RedisClient;
import cn.itsource.aigou.util.RedisUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController implements RedisClient {

    @Override
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    public void set(@RequestParam("key")String key,@RequestParam("value")String value){
        RedisUtil.set(key, value);
    }

    @Override
    @RequestMapping(value = "/redis/{key}",method = RequestMethod.GET)
    public String get(@PathVariable("key") String key){
        return RedisUtil.get(key);
    }
}
