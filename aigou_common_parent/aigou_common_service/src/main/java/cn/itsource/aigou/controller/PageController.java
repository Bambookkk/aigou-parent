package cn.itsource.aigou.controller;

import cn.itsource.aigou.client.PageClient;
import cn.itsource.aigou.commom.GlobalConstant;
import cn.itsource.aigou.util.VelocityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PageController implements PageClient {

    @Override
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public void createPage(@RequestBody Map<String,Object> params) {
        Object model = params.get(GlobalConstant.MODEL);
        String template_path = (String) params.get(GlobalConstant.TEMPLATE_PATH);
        String target_path = (String)params.get(GlobalConstant.TARGET_PATH);
        VelocityUtils.staticByTemplate(model, template_path, target_path);
    }
}
