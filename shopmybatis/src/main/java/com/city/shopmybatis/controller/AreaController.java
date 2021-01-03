package com.city.shopmybatis.controller;

import com.city.shopmybatis.model.AreaModel;
import com.city.shopmybatis.model.GoodModel;
import com.city.shopmybatis.result.ResultList;
import com.city.shopmybatis.result.ResultOne;
import com.city.shopmybatis.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private IAreaService iAreaService=null;

    @RequestMapping("add")
    public ResultOne add(@RequestBody AreaModel am) throws Exception{
        ResultOne<String> resultOne=new ResultOne<String>();
        if (iAreaService.add(am)>0){
            resultOne.setCode(20000);
            resultOne.setMessage("添加成功");
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("添加失败");
        }
        return resultOne;
    }

    @RequestMapping("modify")
    public ResultOne modify(@RequestBody AreaModel am) throws Exception {
        ResultOne<String> resultOne=new ResultOne<String>();
        if (iAreaService.modify(am)>0){
            resultOne.setCode(20000);
            resultOne.setMessage("修改成功");
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("修改失败");
        }
        return resultOne;
    }

    @RequestMapping("delete")
    public ResultOne delete(@RequestBody AreaModel am) throws Exception {
        ResultOne<String> resultOne=new ResultOne<String>();
        if (iAreaService.getByIdWithGoods(am.getId()).getGoods().isEmpty()==true){
            if (iAreaService.delete(am)>0){
                resultOne.setCode(20000);
                resultOne.setMessage("删除成功");
            }else{
                resultOne.setCode(500);
                resultOne.setMessage("删除失败");
            }
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("删除失败,请先删除区域下的所有商品");
        }
        return resultOne;
    }

    @RequestMapping("getById")
    public ResultOne getById(int id) throws Exception {
        ResultOne<AreaModel> resultOne = new ResultOne<AreaModel>();
        resultOne.setCode(20000);
        resultOne.setMessage("获取成功");
        resultOne.setData(iAreaService.getByIdWithoutGoods(id));
        return resultOne;
    }

    @RequestMapping("getByGoodId")
    public ResultList getByGoodId(int id) throws Exception {
        ResultList<AreaModel> resultList=new ResultList<AreaModel>();
        resultList.setCode(20000);
        resultList.setMessage("获取成功");
        resultList.setData(iAreaService.getByGoodIdWithAreas(id));
        return resultList;
    }

    @RequestMapping("getListByAll")
    public ResultList getListByAll() throws Exception {
        ResultList<AreaModel> resultList=new ResultList<AreaModel>();
        resultList.setCode(20000);
        resultList.setData(iAreaService.getListByAll());
        resultList.setMessage("获取成功");
        return resultList;
    }

    @RequestMapping("getListByAllWithPage")
    public ResultList getListByAllWithPage(@RequestParam(value = "page",required = false,defaultValue = "1") int page, @RequestParam(value="rows",required = false,defaultValue = "10") int rows) throws Exception {
        ResultList<AreaModel> resultList=new ResultList<AreaModel>();
        resultList.setRows(rows);
        resultList.setPage(page);
        resultList.setCount(iAreaService.getCountByAll());
        resultList.setPageCount(iAreaService.getPageCountByAll(rows));
        resultList.setCode(20000);
        resultList.setData(iAreaService.getListByAllWithPage(page,rows));
        resultList.setMessage("获取成功");
        return resultList;
    }
}
