package com.zrkj.ecp.service.serviceimpl.shopgoods;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shopgoods.ShopGoodsClassMapper;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsClassDto;
import com.zrkj.ecp.service.shopgoods.IShopGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/4/14.
 */
@Service("iShopGoodsClassService")
@Scope("prototype")
public class ShopGoodsClassServiceImpl implements IShopGoodsClassService{
    @Autowired
    ShopGoodsClassMapper shopGoodsClassMapper;

    /**
     * 获取顶级商品分类列表
     * @param page
     * @param rows
     * @param shopGoodsClassDto
     * @return
     */
    @Override
    public PageInfo<ShopGoodsClass> obtainTopShopGoodsClassList(int page, int rows, ShopGoodsClassDto shopGoodsClassDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopGoodsClassDto);
        List<ShopGoodsClass> resultList = shopGoodsClassMapper.selectTopShopGoodsClassList(shopGoodsClassDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }else {
            return new PageInfo<>(Collections.<ShopGoodsClass>emptyList());
        }
    }

    /**
     * 根据父id获取列表
     * @param shopGoodsClassDto
     * @return
     */
    @Override
    public List<ShopGoodsClass> obtainShopGoodsClassListByPid(ShopGoodsClassDto shopGoodsClassDto) {
        List<ShopGoodsClass> resultList = shopGoodsClassMapper.selectShopGoodsClassListByPid(shopGoodsClassDto);
        if(resultHandler(resultList)){
            return resultList;
        }
        return Collections.<ShopGoodsClass>emptyList();
    }

    /**
     * 添加商品分类
     * @param shopGoodsClass
     * @return
     */
    @Override
    public boolean addShopGoods(ShopGoodsClass shopGoodsClass) {
        try {
            if(shopGoodsClassMapper.insertSelective(shopGoodsClass)>0){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 编辑商品分类
     * @param shopGoodsClass
     * @return
     */
    @Override
    public boolean updateShopGoods(ShopGoodsClass shopGoodsClass) {
        try {
            if(shopGoodsClassMapper.updateByPrimaryKeySelective(shopGoodsClass)>0){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 上传头像
     * @param request
     * @param multipartFile
     * @return
     */
    @Override
    public Map<String,String> uploadPhoto(String uploadPath,HttpServletRequest request, MultipartFile multipartFile) {
        Map<String,String> map = new HashMap<String,String>();
        try {
            UUID uuid = UUID.randomUUID();
            String path = request.getServletContext().getRealPath(
                    "/");
            String userPath = "/" + uploadPath+"/";
            String realPath = path + userPath ;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String imagename = uuid+".png";
            String imageurl = realPath+imagename;
            multipartFile.transferTo(new File(imageurl));
            map.put("isSuccess","true");
            map.put("url",userPath+imagename);
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        map.put("isSuccess","false");
        map.put("url","");
        return map;
    }

    /**
     * 获取树级分类
     * @param id
     * @return
     */
    @Override
    public List<Map<String, String>> obtainClassTree(String id) {
        List<ShopGoodsClass> resultList;
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        if(id==null){
            id = "0";
        }
        ShopGoodsClassDto shopGoodsClassDto = new ShopGoodsClassDto();
        shopGoodsClassDto.setGcParentId(Integer.parseInt(id));
        resultList = shopGoodsClassMapper.selectShopGoodsClassListByPid(shopGoodsClassDto);
        if(resultHandler(resultList)){
            for(ShopGoodsClass shopGoodsClass:resultList){
                Map<String,String> map = new HashMap<>();
                map.put("id", shopGoodsClass.getId().toString());
                map.put("text", shopGoodsClass.getGcName());
                map.put("state", "closed");
                result.add(map);
            }
            return result;
        }
        return Collections.<Map<String, String>>emptyList();
    }

    /**
     * 判断返回值是否为空
     * @param resultList
     * @return
     */
    private boolean resultHandler(List<ShopGoodsClass> resultList){
        if(null != resultList && resultList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询条件模糊化
     */
    private void dtoHandler(ShopGoodsClassDto shopGoodsClassDto){
        if(shopGoodsClassDto.getGcName()!=null){
            shopGoodsClassDto.setGcName("%"+shopGoodsClassDto.getGcName()+"%");
        }else{
            shopGoodsClassDto.setGcName("%%");
        }
    }
}
