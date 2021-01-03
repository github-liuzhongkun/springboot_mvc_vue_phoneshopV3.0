package com.city.shopmybatis.service.impl;

import com.city.shopmybatis.dao.IAreaDao;
import com.city.shopmybatis.model.AreaModel;
import com.city.shopmybatis.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaDao iAreaDao = null;

    @Override
    public int add(AreaModel am) throws Exception {
        return iAreaDao.insert(am);
    }

    @Override
    public int modify(AreaModel am) throws Exception {
        return iAreaDao.update(am);
    }

    @Override
    public int delete(AreaModel am) throws Exception {
        return iAreaDao.delete(am);
    }

    @Override
    public AreaModel getByIdWithoutGoods(int id) throws Exception {
        return iAreaDao.selectById(id);
    }

    @Override
    public AreaModel getByIdWithGoods(int id) throws Exception {
        return iAreaDao.selectByIdWithGoods(id);
    }

    @Override
    public List<AreaModel> getByGoodIdWithAreas(int id) throws Exception {
        return iAreaDao.selectByGoodIdWithAreas(id);
    }

    @Override
    public List<AreaModel> getListByAll() throws Exception {
        return iAreaDao.selectListByAll();
    }

    @Override
    public List<AreaModel> getListByAllWithGoods() throws Exception {
        return iAreaDao.selectListByAllWithGoods();
    }

    @Override
    public List<AreaModel> getListByAllWithPage(int page, int rows) throws Exception {
//        int i=10/0;
        return iAreaDao.selectListByAllWithPage(rows * (page - 1), rows);
    }

    @Override
    public int getCountByAll() throws Exception {
        return iAreaDao.selectCountByAll();
    }

    @Override
    public int getPageCountByAll(int rows) throws Exception {
        int count = iAreaDao.selectCountByAll();
        return count % rows == 0 ? count / rows : count / rows + 1;
    }
}
