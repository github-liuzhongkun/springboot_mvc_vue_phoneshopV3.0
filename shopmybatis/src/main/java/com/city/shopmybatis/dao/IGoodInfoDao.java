package com.city.shopmybatis.dao;

import java.util.List;

import com.city.shopmybatis.model.GoodInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IGoodInfoDao {
	//增加
	public int insert(GoodInfoModel gim) throws Exception;
	//有图片增加
	public int insertWithPhoto(GoodInfoModel gim) throws Exception;
	//修改
	public int update(GoodInfoModel gim) throws Exception;
	//删除
	public int delete(GoodInfoModel gim) throws Exception;
	//取得指定ID的对象
	public GoodInfoModel selectById(int id) throws Exception;
	//取得所有的列表
	public List<GoodInfoModel> selectListByAll() throws Exception;
	
}
