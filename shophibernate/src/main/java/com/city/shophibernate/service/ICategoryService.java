package com.city.shophibernate.service;

import com.city.shophibernate.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    //增加
    public int add(CategoryModel cm) throws Exception;

    //修改
    public int modify(CategoryModel cm) throws Exception;

    //删除
    public int delete(CategoryModel cm) throws Exception;

    //取得指定的对象，不取关联的对象
    public CategoryModel getByIdWithoutGoods(int id) throws Exception;

    //取得指定的对象，同时取关联的对象集合
    public CategoryModel getByIdWithGoods(int id) throws Exception;

    //取得列表，不分页模式，不取关联的对象
    public List<CategoryModel> getListByAll() throws Exception;

    //取得列表，不分页模式，抓取关联的对象
    public List<CategoryModel> getListByAllWithGoods() throws Exception;

    //取得列表，分页模式
    public List<CategoryModel> getListByAllWithPage(int page, int rows) throws Exception;

    //取得所有个数
    public int getCountByAll() throws Exception;

    //取得页数
    public int getPageCountByAll(int rows) throws Exception;
}
