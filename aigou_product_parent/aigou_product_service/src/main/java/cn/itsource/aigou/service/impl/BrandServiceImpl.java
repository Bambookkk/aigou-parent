package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.Brand;
import cn.itsource.aigou.mapper.BrandMapper;
import cn.itsource.aigou.query.BrandQuery;
import cn.itsource.aigou.service.IBrandService;
import cn.itsource.aigou.util.LetterUtil;
import cn.itsource.aigou.util.PageList;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author zk
 * @since 2019-03-30
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageList<Brand> queryPage(BrandQuery query) {
        long total = brandMapper.getTotal(query);
        //先判断总记录数是否为0，如果为0，也没必要去查记录了
        if(total==0L){
            return new PageList<>();
        }
        PageList<Brand> pageList = new PageList<>(total,brandMapper.getRows(query));
        return pageList;
    }

    @Override
    public boolean insert(Brand entity) {
        entity.setCreateTime(new Date().getTime());
        entity.setFirstLetter(LetterUtil.getFirstLetter(entity.getName()));
        return super.insert(entity);
    }

    @Override
    public boolean updateById(Brand entity) {
        entity.setUpdateTime(new Date().getTime());
        return super.updateById(entity);
    }
}
