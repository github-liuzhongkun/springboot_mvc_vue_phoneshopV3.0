package com.city.shopmybatis.dao;

import com.city.shopmybatis.model.CategoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ICategoryDao {
    //插入
    public int insert(CategoryModel cm) throws Exception;
    //修改
    public int update(CategoryModel cm) throws Exception;
    //删除
    public int delete(CategoryModel cm) throws Exception;
    //查询
    public CategoryModel selectById(int id) throws Exception;
    //查询及关联的对象
    public CategoryModel selectByIdWithGoods(int id) throws Exception;
    //查询所有
    public List<CategoryModel> selectListByAll() throws Exception;
    //查询所有及关联的对象
    public List<CategoryModel> selectListByAllWithGoods() throws Exception;
    //分页查询
    public List<CategoryModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;
    //查询总数
    public int selectCountByAll() throws Exception;

}
