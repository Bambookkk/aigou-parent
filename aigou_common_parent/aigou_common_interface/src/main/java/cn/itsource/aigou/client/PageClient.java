package cn.itsource.aigou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = PageClientFallbackFactory.class)//服务名
public interface PageClient {

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    void createPage(@RequestBody Map<String,Object> params);
}