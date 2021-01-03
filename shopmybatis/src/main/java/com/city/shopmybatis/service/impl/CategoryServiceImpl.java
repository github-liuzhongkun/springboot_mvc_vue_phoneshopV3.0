package com.city.shopmybatis.service.impl;

import com.city.shopmybatis.dao.ICategoryDao;
import com.city.shopmybatis.model.CategoryModel;
import com.city.shopmybatis.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao = null;

    @Override
    public int add(CategoryModel cm) throws Exception {
        return iCategoryDao.insert(cm);
    }

    @Override
    public int modify(CategoryModel cm) throws Exception {
        return iCategoryDao.update(cm);
    }

    @Override
    public int delete(CategoryModel cm) throws Exception {
        return iCategoryDao.delete(cm);
    }

    @Override
    public CategoryModel getByIdWithoutGoods(int id) throws Exception {
        return iCategoryDao.selectById(id);
    }

    @Override
    public CategoryModel getByIdWithGoods(int id) throws Exception {
        return iCategoryDao.selectByIdWithGoods(id);
    }

    @Override
    public List<CategoryModel> getListByAll() throws Exception {
        return iCategoryDao.selectListByAll();
    }

    @Override
    public List<CategoryModel> getListByAllWithGoods() throws Exception {
        return iCategoryDao.selectListByAllWithGoods();
    }

    @Override
    public List<CategoryModel> getListByAllWithPage(int page, int rows) throws Exception {
        return iCategoryDao.selectListByAllWithPage(rows * (page - 1), rows);
    }

    @Override
    public int getCountByAll() throws Exception {
        return iCategoryDao.selectCountByAll();
    }

    @Override
    public int getPageCountByAll(int rows) throws Exception {
        int count = iCategoryDao.selectCountByAll();
        return count % rows == 0 ? count / rows : count / rows + 1;
    }
}
