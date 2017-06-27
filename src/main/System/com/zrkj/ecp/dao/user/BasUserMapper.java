package com.zrkj.ecp.dao.user;


import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.domain.user.SysRole;
import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.user.ExpressUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BasUserMapper{
    SysUser findByUserName(@Param("username") String userName);

    List<SysRole> findUserRoleByUserName(@Param("username") String userName);

    int deleteByPrimaryKey(Integer uid);

    int insert(BasUser record);

    int insertSelective(BasUser record);

    BasUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(BasUser record);

    int updateByPrimaryKey(BasUser record);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxid();

    /**
     * 根据职位id获取下属会员
     * @param posid
     * @return
     */
    List<ExpressUserDto> selectExpressUserDtoByPposid(Integer posid);

    /**
     * 登录验证
     * @param basUser
     * @return
     */
    String loginCheck(BasUser basUser);

    /**
     * 修改密码
     * @param uname
     * @param oldP
     * @param newP
     * @return
     */
    int updatePassword(@Param("uname")String uname,@Param("oldP") String oldP,@Param("newP")String newP);

}