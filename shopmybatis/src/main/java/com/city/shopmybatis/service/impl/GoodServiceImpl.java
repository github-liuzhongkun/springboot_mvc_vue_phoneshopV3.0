package com.city.shopmybatis.service.impl;

import com.city.shopmybatis.dao.IGoodDao;
import com.city.shopmybatis.dao.IGoodInfoDao;
import com.city.shopmybatis.model.GoodInfoModel;
import com.city.shopmybatis.model.GoodModel;
import com.city.shopmybatis.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private IGoodDao iGoodDao = null;
    @Autowired
    private IGoodInfoDao iGoodInfoDao = null;


    @Override
    public int add(GoodModel gm) throws Exception {
        int sum = 0;
        sum += iGoodDao.insert(gm);
        if (gm.getGoodInfo() != null) {
            GoodInfoModel gim = gm.getGoodInfo();
            gim.setGood(gm);
            if (gim.getContentType() != null) {
                sum += iGoodInfoDao.insertWithPhoto(gim);
            } else {
                sum += iGoodInfoDao.insert(gim);
            }
        }

        return sum;
    }

    @Override
    public int modify(GoodModel gm) throws Exception {
        int sum = 0;
        sum += iGoodDao.update(gm);
        if (gm.getGoodInfo().getPhoto() != null) {
            GoodInfoModel gim = gm.getGoodInfo();
            gim.setGood(gm);
            sum += iGoodInfoDao.update(gim);
        }
        return sum;
    }

    @Override
    public int delete(GoodModel gm) throws Exception {
        int sum = 0;
        sum += iGoodDao.deleteAreas(gm.getId());
        GoodInfoModel gim = gm.getGoodInfo();
        gim.setGood(gm);
        sum += iGoodInfoDao.delete(gim);
        sum += iGoodDao.delete(gm);

        return sum;

    }

    @Override
    public GoodModel getByIdWithoutCategory(int id) throws Exception {
        return iGoodDao.selectById(id);
    }

    @Override
    public GoodModel getByIdWithCategory(int id) throws Exception {
        return iGoodDao.selectByIdWithCategory(id);
    }

    @Override
    public GoodModel getByIdWithCategoryAndGoodInfoAndAreas(int id) throws Exception {
        return iGoodDao.selectByIdWithCategoryAndGoodInfoAndAreas(id);
    }

    @Override
    public List<GoodModel> getListByAll() throws Exception {
        return iGoodDao.selectListByAll();
    }

    @Override
    public List<GoodModel> getListByAllWithCategory() throws Exception {
        return iGoodDao.selectListByAllWithCategory();
    }

    @Override
    public List<GoodModel> getListByAllWithPage(int page, int rows,int category) throws Exception {
        return iGoodDao.selectListByAllWithPage(rows * (page - 1), rows, category);
    }

    @Override
    public int getCountByAll(int category) throws Exception {
        return iGoodDao.selectCountByAll(category);
    }

    @Override
    public int getPageCountByAll(int rows,int category) throws Exception {
        int count = iGoodDao.selectCountByAll(category);
        return count % rows == 0 ? count / rows : count / rows + 1;
    }

    @Override
    public int addArea(int id, int AreaId) throws Exception {
        return iGoodDao.insertArea(id, AreaId);
    }

    @Override
    public int addAreas(int id, int[] AreaIds) throws Exception {
        return iGoodDao.insertAreas(id, AreaIds);
    }

    @Override
    public int deleteBehaves(int id) throws Exception {
        return iGoodDao.deleteAreas(id);
    }

    @Override
    public GoodInfoModel getByIdWithPhoto(int id) throws Exception {
        return iGoodInfoDao.selectById(id);
    }
}
