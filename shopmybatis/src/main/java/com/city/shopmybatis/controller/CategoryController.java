package com.city.shopmybatis.controller;

import com.city.shopmybatis.model.CategoryModel;
import com.city.shopmybatis.result.ResultList;
import com.city.shopmybatis.result.ResultOne;
import com.city.shopmybatis.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService=null;

    @RequestMapping("add")
    public ResultOne add(@RequestBody CategoryModel cm) throws Exception{
        ResultOne<String> resultOne=new ResultOne<String>();
        if (iCategoryService.add(cm)>0){
            resultOne.setCode(20000);
            resultOne.setMessage("添加成功");
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("添加失败");
        }
        return resultOne;
    }

    @RequestMapping("modify")
    public ResultOne modify(@RequestBody CategoryModel cm) throws Exception {
        ResultOne<String> resultOne=new ResultOne<String>();
        if (iCategoryService.modify(cm)>0){
            resultOne.setCode(20000);
            resultOne.setMessage("修改成功");
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("修改失败");
        }
        return resultOne;
    }

    @RequestMapping("delete")
    public ResultOne delete(@RequestBody CategoryModel cm) throws Exception {
        ResultOne<String> resultOne=new ResultOne<String>();
        if(iCategoryService.getByIdWithGoods(cm.getId()).getGoods().isEmpty()){
            if (iCategoryService.delete(cm)>0){
                resultOne.setCode(20000);
                resultOne.setMessage("删除成功");
            }else{
                resultOne.setCode(500);
                resultOne.setMessage("删除失败");
            }
        }else {
            resultOne.setCode(500);
            resultOne.setMessage("删除失败,请先删除品牌下的所有商品");
        }

        return resultOne;
    }

    @RequestMapping("getById")
    public ResultOne getById(int id) throws Exception {
        ResultOne<CategoryModel> resultOne=new ResultOne<CategoryModel>();
        resultOne.setCode(20000);
        resultOne.setMessage("获取成功");
        resultOne.setData(iCategoryService.getByIdWithoutGoods(id));
        return resultOne;
    }

    @RequestMapping("getListByAll")
    public ResultList getListByAll() throws Exception {
        ResultList<CategoryModel> resultList=new ResultList<CategoryModel>();
        resultList.setCode(20000);
        resultList.setData(iCategoryService.getListByAll());
        resultList.setMessage("获取成功");
        return resultList;
    }

    @RequestMapping("getListByAllWithPage")
    public ResultList getListByAllWithPage(@RequestParam(value = "page",required = false,defaultValue = "1") int page, @RequestParam(value="rows",required = false,defaultValue = "10") int rows) throws Exception {
        ResultList<CategoryModel> resultList=new ResultList<CategoryModel>();
        resultList.setRows(rows);
        resultList.setPage(page);
        resultList.setCount(iCategoryService.getCountByAll());
        resultList.setPageCount(iCategoryService.getPageCountByAll(rows));
        resultList.setCode(20000);
        resultList.setData(iCategoryService.getListByAllWithPage(page,rows));
        resultList.setMessage("获取成功");
        return resultList;
    }
}
