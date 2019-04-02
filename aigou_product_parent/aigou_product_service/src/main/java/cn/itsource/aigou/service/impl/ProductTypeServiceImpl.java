package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.client.PageClient;
import cn.itsource.aigou.client.RedisClient;
import cn.itsource.aigou.commom.GlobalConstant;
import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author zk
 * @since 2019-03-30
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private PageClient pageClient;

    @Override
    public List<ProductType> treeData() {
        //最终返回的treeData
        List<ProductType> treeData = new ArrayList<>();
        //获取所有数据
        List<ProductType> productTypes = productTypeMapper.selectList(new EntityWrapper<>());
        Map<Long,ProductType> map = new HashMap<>();
        //将所有的数据用map存放起来，找父类的时候方便，不用再发sql
        for (ProductType productType : productTypes) {
            map.put(productType.getId(), productType);
        }

        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){
                treeData.add(productType);//一级菜单，直接放到treeData中
            }else {
                map.get(productType.getPid()).getChildren().add(productType);//非一级菜单，放到其父类下
            }
        }
        return treeData;
    }

    @Override
    public List<ProductType> treeDataCache() {
        //先从缓存中查询
        String jsonArray = redisClient.get(GlobalConstant.TREE_DATA);
        //如果不为空则返回
        if(!StringUtils.isEmpty(jsonArray)){
            List<ProductType> productTypes = JSON.parseArray(jsonArray, ProductType.class);
            System.out.println("========cache=========");
            return productTypes;
        }else{
            //如果为空则从数据库中查出放到缓存中再返回
            List<ProductType> productTypes = treeData();
            String jsonString = JSON.toJSONString(productTypes);
            redisClient.set(GlobalConstant.TREE_DATA, jsonString);
            System.out.println("=========db=============");
            return productTypes;
        }
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean reslut = super.updateById(entity);
        //每修改一次，就更新一次缓存
        List<ProductType> productTypes = treeData();
        String jsonString = JSON.toJSONString(productTypes);
        redisClient.set(GlobalConstant.TREE_DATA, jsonString);
        System.out.println("=========修改=============");

        //每修改一次，就从新生成一次静态化页面
            //分类页面
        Map productTypeMap = new HashMap();
        productTypeMap.put(GlobalConstant.MODEL, productTypes);
        productTypeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm");
        productTypeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm.html");
        pageClient.createPage(productTypeMap);
            //主页面
        Map homeMap = new HashMap();
        Map model = new HashMap();
        model.put("staticRoot", "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\");
        homeMap.put(GlobalConstant.MODEL, model);
        homeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm");
        homeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm.html");
        pageClient.createPage(homeMap);
        return reslut;
    }

    @Override
    public boolean insert(ProductType entity) {
        boolean reslut = super.insert(entity);
        //每添加一次，就更新一次缓存
        List<ProductType> productTypes = treeData();
        String jsonString = JSON.toJSONString(productTypes);
        redisClient.set(GlobalConstant.TREE_DATA, jsonString);
        System.out.println("=========添加=============");

        //每添加一次，就从新生成一次静态化页面
        //分类页面
        Map productTypeMap = new HashMap();
        productTypeMap.put(GlobalConstant.MODEL, productTypes);
        productTypeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm");
        productTypeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm.html");
        pageClient.createPage(productTypeMap);
        //主页面
        Map homeMap = new HashMap();
        Map model = new HashMap();
        model.put("staticRoot", "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\");
        homeMap.put(GlobalConstant.MODEL, model);
        homeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm");
        homeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm.html");
        pageClient.createPage(homeMap);
        return reslut;
    }

    @Override
    public boolean deleteById(Serializable id) {
        boolean reslut = super.deleteById(id);
        //每删除一次，就更新一次缓存
        List<ProductType> productTypes = treeData();
        String jsonString = JSON.toJSONString(productTypes);
        redisClient.set(GlobalConstant.TREE_DATA, jsonString);
        System.out.println("=========添加=============");

        //每删除一次，就从新生成一次静态化页面
        //分类页面
        Map productTypeMap = new HashMap();
        productTypeMap.put(GlobalConstant.MODEL, productTypes);
        productTypeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm");
        productTypeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\product.type.vm.html");
        pageClient.createPage(productTypeMap);
        //主页面
        Map homeMap = new HashMap();
        Map model = new HashMap();
        model.put("staticRoot", "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\");
        homeMap.put(GlobalConstant.MODEL, model);
        homeMap.put(GlobalConstant.TEMPLATE_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm");
        homeMap.put(GlobalConstant.TARGET_PATH, "E:\\project4\\aigou-parent\\aigou_product_parent\\aigou_product_service\\src\\main\\resources\\template\\home.vm.html");
        pageClient.createPage(homeMap);
        return reslut;
    }
}
