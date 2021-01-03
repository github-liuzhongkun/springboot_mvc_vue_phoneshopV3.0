package com.city.shopmybatis.dao;

import com.city.shopmybatis.model.GoodModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IGoodDao {
    //插入
    public int insert(GoodModel gm) throws Exception;

    //修改
    public int update(GoodModel gm) throws Exception;

    //删除
    public int delete(GoodModel gm) throws Exception;

    //查询
    public GoodModel selectById(int id) throws Exception;

    //查询及关联的对象
    public GoodModel selectByIdWithCategory(int id) throws Exception;

    public GoodModel selectByIdWithCategoryAndGoodInfoAndAreas(int id) throws Exception;

    //查询所有
    public List<GoodModel> selectListByAll() throws Exception;

    //通过关联对象查询
    public List<GoodModel> selectListByCategory(int id) throws Exception;

    //查询所有及关联的对象
    public List<GoodModel> selectListByAllWithCategory() throws Exception;

    //分页查询
    public List<GoodModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows, @Param("category") int category) throws Exception;


    //查询总数
    public int selectCountByAll(int category) throws Exception;


    //为商品增加区域，单个区域
    public int insertArea(@Param("id") int id, @Param("AreaId") int AreaId) throws Exception;

    //为员商品增加区域，多个区域
    public int insertAreas(@Param("id") int id, @Param("AreaIds") int[] AreaIds) throws Exception;

    //删除指定商品的全部区域
    public int deleteAreas(int id) throws Exception;
}
