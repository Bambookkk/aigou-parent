package cn.itsource.aigou.web.controller;

import cn.itsource.aigou.ProductApplication8003;
import cn.itsource.aigou.query.BrandQuery;
import cn.itsource.aigou.service.IBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication8003.class)
public class BrandControllerTest {

    @Autowired
    private IBrandService brandService;

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void list() {
    }

    @Test
    public void json() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setKeyword("鸟");
        System.out.println(brandService.queryPage(brandQuery));
    }

    /*private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void testLog() {

        logger.error("用户为绑定就开始缴费");
    }*/
}