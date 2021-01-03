package com.city.shophibernate.service;

import com.city.shophibernate.model.GoodInfoModel;
import com.city.shophibernate.model.GoodModel;

import java.util.List;

public interface IGoodService {
    //增加
    public int add(GoodModel gm) throws Exception;

    //修改
    public int modify(GoodModel gm) throws Exception;

    //删除
    public int delete(GoodModel gm) throws Exception;

    //取得指定的对象，不取关联的对象
    public GoodModel getByIdWithoutCategory(int id) throws Exception;

    //取得指定的对象，同时取关联的对象集合
    public GoodModel getByIdWithCategory(int id) throws Exception;

    //取得指定的对象，同时取关联的所有对象集合
    public GoodModel getByIdWithCategoryAndGoodInfoAndAreas(int id) throws Exception;

    //取得列表，不分页模式，不取关联的对象
    public List<GoodModel> getListByAll() throws Exception;

    //取得列表，不分页模式，抓取关联的对象
    public List<GoodModel> getListByAllWithCategory() throws Exception;

    //取得列表，分页模式
    public List<GoodModel> getListByAllWithPage(int page, int rows, int category) throws Exception;

    //取得所有个数
    public int getCountByAll(int category) throws Exception;

    //取得页数
    public int getPageCountByAll(int rows, int category) throws Exception;


    //为商品增加区域，单个区域
    public int addArea(int id, int AreaId) throws Exception;

    //为员商品增加区域，多个区域
    public int addAreas(int id, int[] AreaIds) throws Exception;

    //删除指定商品的全部区域
    public int deleteBehaves(int id) throws Exception;


    public GoodInfoModel getByIdWithPhoto(int id) throws Exception;
}
