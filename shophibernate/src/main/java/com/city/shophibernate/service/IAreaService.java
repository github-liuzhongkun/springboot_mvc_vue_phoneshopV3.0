package com.city.shophibernate.service;

import com.city.shophibernate.model.AreaModel;

import java.util.List;

public interface IAreaService {
    //增加
    public int add(AreaModel am) throws Exception;

    //修改
    public int modify(AreaModel am) throws Exception;

    //删除
    public int delete(AreaModel am) throws Exception;

    //取得指定的对象，不取关联的对象
    public AreaModel getByIdWithoutGoods(int id) throws Exception;

    //取得指定的对象，同时取关联的对象集合
    public AreaModel getByIdWithGoods(int id) throws Exception;

    //取得指定的对象，同时取关联的集合
    public List<AreaModel> getByGoodIdWithAreas(int id) throws Exception;

    //取得列表，不分页模式，不取关联的对象
    public List<AreaModel> getListByAll() throws Exception;

    //取得列表，不分页模式，抓取关联的对象
    public List<AreaModel> getListByAllWithGoods() throws Exception;

    //取得列表，分页模式
    public List<AreaModel> getListByAllWithPage(int page, int rows) throws Exception;

    //取得所有个数
    public int getCountByAll() throws Exception;

    //取得页数
    public int getPageCountByAll(int rows) throws Exception;
}
