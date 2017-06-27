package com.zrkj.ecp.service.serviceimpl.expresssignphoto;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expresssignphoto.ExpressSignPhoto;
import com.zrkj.ecp.dao.expresssignphoto.ExpressSignPhotoMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssignphoto.IExpressSignPhoto;

@Service
public class ExpressSignPhotoServiceImpl implements IExpressSignPhoto{
	 @Resource
	    ExpressSignPhotoMapper expressSignPhotoMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssSignPhotoList(ExpressDto expressDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressSignPhotoMapper.selectExpressSignPhotoList(expressDto));
	        map.put("total",expressSignPhotoMapper.selectExpressSignPhotoCount(expressDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param SignPhotoId
	     * @return
	     */
	    @Override
	    public ExpressSignPhoto obtainExpressSignPhotoById(Integer SignPhotoId) {
	        return expressSignPhotoMapper.selectByPrimaryKey(SignPhotoId);
	    }

	    /**
	     * 添加
	     * @param expressSignPhoto
	     * @return
	     */
	    @Override
	    public boolean addExpressSignPhoto(ExpressSignPhoto expressSignPhoto) {
	        try {
	            Integer maxid = expressSignPhotoMapper.selectMaxId();
	            if(maxid==null){
	                expressSignPhoto.setPhid(1);
	            }else {
	                expressSignPhoto.setPhid(maxid+1);
	            }
	            expressSignPhotoMapper.insertSelective(expressSignPhoto);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressSignPhoto
	     * @return
	     */
	    @Override
	    public boolean updateExpressSignPhoto(ExpressSignPhoto expressSignPhoto) {
	        try {
				if(expressSignPhotoMapper.updateByPrimaryKeySelective(expressSignPhoto)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param SignPhotoIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressSignPhoto(String SignPhotoIdStr) {
	        return false;
	    }

}
