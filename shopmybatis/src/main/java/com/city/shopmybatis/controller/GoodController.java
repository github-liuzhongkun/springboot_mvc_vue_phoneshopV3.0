package com.city.shopmybatis.controller;

import com.city.shopmybatis.model.GoodInfoModel;
import com.city.shopmybatis.model.GoodModel;
import com.city.shopmybatis.result.ResultList;
import com.city.shopmybatis.result.ResultOne;
import com.city.shopmybatis.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("good")
public class GoodController {

    @Autowired
    private IGoodService iGoodService=null;

    @RequestMapping("add")
    public ResultOne add(GoodModel gm, @RequestParam(required = false) MultipartFile photo,@RequestParam(required = false) int[] areaNos) throws Exception {
        for (int area : areaNos) {
            System.out.println(area);
        }
        ResultOne<String> resultOne = new ResultOne<String>();
        if (photo != null && (!photo.isEmpty())) {
            gm.getGoodInfo().setContentType(photo.getContentType());
            gm.getGoodInfo().setFilename(gm.getName() + ":photo:" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")));
            gm.getGoodInfo().setPhoto(photo.getBytes());
        }
        if (iGoodService.add(gm) > 0) {
            if (areaNos != null) {
                iGoodService.addAreas(gm.getId(), areaNos);
            }
            resultOne.setCode(20000);
            resultOne.setMessage("添加成功");
        } else {
            resultOne.setCode(500);
            resultOne.setMessage("添加失败");
        }

        return resultOne;
    }

    @RequestMapping("modify")
    public ResultOne modify(GoodModel gm,@RequestParam(required = false) MultipartFile photo,@RequestParam(required = false) int[] areaNos) throws Exception {
        for (int area : areaNos) {
            System.out.println(area);
        }
        ResultOne<String> resultOne=new ResultOne<String>();
        if (photo != null && (!photo.isEmpty())) {
            gm.getGoodInfo().setContentType(photo.getContentType());
            gm.getGoodInfo().setFilename(gm.getName() + ":photo:" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")));
            gm.getGoodInfo().setPhoto(photo.getBytes());
        }
        if (iGoodService.modify(gm)>0){
            iGoodService.deleteBehaves(gm.getId());
            if (areaNos!=null){
                iGoodService.addAreas(gm.getId(),areaNos);
            }
            resultOne.setCode(20000);
            resultOne.setMessage("修改成功");
        }else{
            resultOne.setCode(500);
            resultOne.setMessage("修改失败");
        }
        return resultOne;
    }

    @RequestMapping("delete")
    public ResultOne delete(@RequestBody GoodModel gm) throws Exception {
        ResultOne<String> resultOne = new ResultOne<String>();
        GoodInfoModel gim=new GoodInfoModel();
        gim.setGood(gm);
        gm.setGoodInfo(gim);
        if (iGoodService.delete(gm) > 0) {
            resultOne.setCode(20000);
            resultOne.setMessage("删除成功");
        } else {
            resultOne.setCode(500);
            resultOne.setMessage("删除失败");
        }
        return resultOne;
    }

    @RequestMapping("getById")
    public ResultOne getById(int id) throws Exception {
        ResultOne<GoodModel> resultOne=new ResultOne<GoodModel>();
        resultOne.setCode(20000);
        resultOne.setMessage("获取成功");
        resultOne.setData(iGoodService.getByIdWithCategoryAndGoodInfoAndAreas(id));
        return resultOne;
    }

    @RequestMapping("getListByAllWithPage")
    public ResultList getListByAllWithPage(@RequestParam(value = "page",required = false,defaultValue = "1") int page, @RequestParam(value="rows",required = false,defaultValue = "10") int rows,@RequestParam(value="category",required = false,defaultValue = "0") int category) throws Exception {
        ResultList<GoodModel> resultList = new ResultList<GoodModel>();
        resultList.setRows(rows);
        resultList.setPage(page);
        resultList.setCount(iGoodService.getCountByAll(category));
        resultList.setPageCount(iGoodService.getPageCountByAll(rows, category));
        resultList.setCode(20000);
        resultList.setData(iGoodService.getListByAllWithPage(page, rows, category));
        resultList.setMessage("获取成功");
        return resultList;
    }

    @RequestMapping("showphoto/{id}/*")
    public HttpEntity<byte[]> showPhoto(@PathVariable int id) throws Exception{
        GoodInfoModel gim=iGoodService.getByIdWithPhoto(id);
        if(gim!=null&&gim.getContentType()!=null) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", gim.getContentType());
            return new HttpEntity<byte[]>(gim.getPhoto(), responseHeaders);
        }
        else {
            return null;
        }
    }
}
