package cn.itsource.aigou.mapper;

import cn.itsource.aigou.ProductApplication8003;
import cn.itsource.aigou.query.BrandQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication8003.class)
public class BrandMapperTest {
    @Autowired
    private BrandMapper brandMapper;
    @Test
    public void getTotal() {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setKeyword("鸟");
        System.out.println(brandMapper.getTotal(brandQuery));
    }

    @Test
    public void getRows() {
    }
}