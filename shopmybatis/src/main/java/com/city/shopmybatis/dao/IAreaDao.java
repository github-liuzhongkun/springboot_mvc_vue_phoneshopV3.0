package com.city.shopmybatis.dao;

import com.city.shopmybatis.model.AreaModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAreaDao {
    //插入
    public int insert(AreaModel am) throws Exception;
    //修改
    public int update(AreaModel am) throws Exception;
    //删除
    public int delete(AreaModel am) throws Exception;
    //查询
    public AreaModel selectById(int id) throws Exception;
    //查询及关联的对象
    public AreaModel selectByIdWithGoods(int id) throws Exception;
    //查询id关联的对象
    public List<AreaModel> selectByGoodIdWithAreas(int id) throws Exception;
    //查询所有
    public List<AreaModel> selectListByAll() throws Exception;
    //查询所有及关联的对象
    public List<AreaModel> selectListByAllWithGoods() throws Exception;
    //分页查询
    public List<AreaModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;
    //查询总数
    public int selectCountByAll() throws Exception;

}
