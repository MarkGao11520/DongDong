package com.zrkj.ecp.service.serviceimpl.member;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.domain.member.MemberRealName;
import com.zrkj.ecp.dao.member.MemberMapper;
import com.zrkj.ecp.dao.member.MemberPropertyMapper;
import com.zrkj.ecp.dao.member.MemberRealNameMapper;
import com.zrkj.ecp.dto.member.EquipmentDto;
import com.zrkj.ecp.dto.member.MemberBasDeatilDto;
import com.zrkj.ecp.dto.member.MemberDto;
import com.zrkj.ecp.service.member.MemberService;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/1/16.
 */
@Service
public class MemberServiceImpl implements MemberService{
    @Resource
    MemberMapper memberMapper;
    @Resource
    MemberPropertyMapper memberPropertyMapper;
    @Resource
    MemberRealNameMapper memberRealNameMapper;
    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 获取会员列表
     * @param memberDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberList(MemberDto memberDto) {
        memberDto.setPage((memberDto.getPage()-1)*memberDto.getRows());
        if(memberDto.getUname()!=null){
            memberDto.setUname("%"+memberDto.getUname()+"%");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberMapper.selectMemberList(memberDto));
        map.put("total",memberMapper.selectMemberCount(memberDto));
        return map;
    }

    /**
     * 根据id获取会员信息
     * @param memberId
     * @return
     */
    @Override
    public Member obtainMemberByMemberId(Integer memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public MemberProperty obtainMemberPropertyByMemberId(Integer memberId,HttpServletRequest request) {
        MemberProperty memberProperty = memberPropertyMapper.selectByPrimaryKey(memberId);
//        memberProperty.setHeadpic(Base64EncoderMp3(memberId,request));
        return memberProperty;
    }

    public String Base64EncoderMp3(Integer id,HttpServletRequest request) {
        String path = memberPropertyMapper.selectByPrimaryKey(id).getHeadpic();
        try {
            String realPath = request.getServletContext().getRealPath(
                    "/");
            BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            File file = new File(realPath+path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return "data:image/png;base64,"+new BASE64Encoder().encode(buffer);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 更新会员信息
     * @param memberProperty
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateMemberProperty(MemberProperty memberProperty,Member member) {
        try {
            memberPropertyMapper.updateByPrimaryKeySelective(memberProperty);
            memberMapper.updateByPrimaryKeySelective(member);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param uname
     * @return
     */
    @Override
    public Boolean updateMemberPassword(String oldPassword, String newPassword, String uname) {
        try {
            memberMapper.updateMemberPassWord(oldPassword,uname,newPassword);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 注册
     * @param member
     * @return
     */
    @Override
    public Boolean MemberRegister(Member member, MemberProperty memberProperty, MemberRealName memberRealName,HttpServletRequest request) {
        try {
            Member member1 = memberMapper.checkMemberExists(member.getUname())==null?new Member():memberMapper.checkMemberExists(member.getUname());
            if (member1!=null&&member1.getNotes()!=null&&member1.getNotes().trim() == "-100") {
                member.setMid(member1.getMid());
                member.setNotes("无");
                memberMapper.updateByPrimaryKeySelective(member);
                return true;
            } else {
                Integer maxid = memberMapper.selectMaxAid();
                Integer mid;
                if (maxid != null) {
                    mid = maxid + 1;
                } else {
                    mid = 1;
                }
                member.setRegdate(new Date());
                member.setRegisterip(getIp(request));
                member.setMid(mid);
                memberProperty.setMid(mid);
                memberProperty.setTelephone(member.getUname());
                memberRealName.setMid(mid);
                memberMapper.insertSelective(member);
                memberPropertyMapper.insertSelective(memberProperty);
                memberRealNameMapper.insertSelective(memberRealName);
                return true;
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            return false;

    }

    /**
     * 删除
     * @param memberIdStr
     * @return
     */
    @Override
    public Boolean deleteMember(String memberIdStr) {
        try {
            Member member = new Member();
            MemberProperty memberProperty = new MemberProperty();
            MemberRealName memberRealName = new MemberRealName();
            String str[] = memberIdStr.split(",");
            for (String string : str) {
                member.setMid(Integer.parseInt(string));
                memberProperty.setMid(Integer.parseInt(string));
                memberRealName.setMid(Integer.parseInt(string));
                member.setIsdel(1);
                memberProperty.setIsdel(1);
                memberRealName.setIsdel(1);
                memberMapper.updateByPrimaryKeySelective(member);
                memberPropertyMapper.updateByPrimaryKeySelective(memberProperty);
                memberRealNameMapper.updateByPrimaryKeySelective(memberRealName);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 登录验证
     */
    @Override
    public Map<String,Object> loginCheck(Member member,HttpServletRequest request) {
        // TODO Auto-generated method stub
        Integer mid = memberMapper.loginCheck(member);
        if(mid!=null){
            member.setLoginip(getIp(request));
            member.setLastlogintime(new Date());
            member.setMid(mid);
            memberMapper.updateByPrimaryKeySelective(member);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("loginCheck",true);
            map.put("mid",mid);
//            map.put("member",memberMapper.selectByPrimaryKey(mid));
//            map.put("memberPro",memberPropertyMapper.selectByPrimaryKey(mid));
            return map;
        }else{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("loginCheck",false);
            map.put("mid",-1);
            return map;
        }
    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    @Override
    public String getIp(HttpServletRequest request) {
        // TODO Auto-generated method stub
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))  {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       return null;
    }

    @Override
    public List<EquipmentDto> obtainequipmentList(String q) {
        q = "%"+q+"%";
        return memberMapper.selectequipmentList(q);
    }

    @Override
    public MemberBasDeatilDto obtaionPhoneAndAddressByPhone(String phone) {
        phone =  "%"+phone+"%";
        return memberMapper.selectNameAndAdressByPhone(phone).get(0);
    }

    /**
     * excel导入
     */
    @Override
    public String excelImport(MultipartFile importFile,HttpServletRequest request) {
        // TODO Auto-generated method stub
        try {
//             System.out.println("excelImportDB");
            ArrayList<Member> memberList = new ArrayList<Member>();
//            ArrayList<MemberProperty> memberPropertyArrayList = new ArrayList<MemberProperty>();
            InputStream is = importFile.getInputStream();
//            System.out.println(importFile.getOriginalFilename());
            HSSFWorkbook hwb = new HSSFWorkbook(is);
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                HSSFSheet hs = hwb.getSheetAt(i);
                if (hs == null)
                    continue;
                else {
                    int firstrownum = hs.getFirstRowNum();
                    int lastrownum = hs.getLastRowNum();
//                     System.out.println(firstrownum);
//                     System.out.println(lastrownum);
                    for (int j = firstrownum + 1; j <= lastrownum; j++) {
                         System.out.println(j + "-------------------");
                        HSSFRow hr = hs.getRow(j);
                        int firstcolumnnum = hr.getFirstCellNum();
                        int lastcolumnnum = hr.getLastCellNum();
                        String[] value = new String[lastcolumnnum];
                        for (int k = firstcolumnnum; k < lastcolumnnum; k++) {
                            HSSFCell hc = hr.getCell(k);
                           value[k] = parseDB(hc.getCellType(), hc);
                            // System.out.println(value[k]);
                        }
                        Member member = new Member();
//                        MemberProperty memberProperty = new MemberProperty();
                        member.setUname((value[0]));
                        member.setNotes((value[1]));
//                        memberProperty.setTelephone((value[0]));
                        memberList.add(member);
//                        memberPropertyArrayList.add(memberProperty);
                    }
                }
            }

            for (Member member : memberList) {
                MemberProperty memberpro = new MemberProperty();
                memberpro.setTelephone(member.getUname());
                memberpro.setRealname(member.getNotes());
                member.setNotes("-100");
                MemberRegister(member,memberpro,new MemberRealName(),request);
            }
            // DBUtils smdb = new DBUtils();
            // boolean temp = smdb.importExcel2DB(stuList);
            // if (temp) {
            // System.out.println("导入数据成功");
            // } else
            // System.out.println("导入数据失败");
            return "数据成功导入到数据库";
        } catch (Exception e) {

            System.out.println("----" + e.getMessage());
            return "导入失败";
        }
    }

    @Override
    public int checkMemberExists(String uname) {
        try {
            Member member = memberMapper.checkMemberExists(uname)==null?new Member():memberMapper.checkMemberExists(uname);
            if (member.getMid() != null) {
                if (member.getNotes() != null) {
                    if (member.getNotes().trim() == "-100") {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Map<String,String> uploadPhoto(HttpServletRequest request, MultipartFile multipartFile, Integer mid) {
        Map<String,String> map = new HashMap<String,String>();
        try {
            UUID uuid = UUID.randomUUID();
            String path = request.getServletContext().getRealPath(
                    "/");
            String userPath = "/" + "userPhoto"+"/"+mid.toString()+"/";
            String realPath = path + userPath ;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String imagename = mid.toString()+".png";
            String imageurl = realPath+imagename;
            multipartFile.transferTo(new File(imageurl));
            MemberProperty memberProperty = new MemberProperty();
            memberProperty.setMid(mid);
            memberProperty.setHeadpic(userPath+imagename);
            memberPropertyMapper.updateByPrimaryKeySelective(memberProperty);
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

    @Override
    public Map<String,Object> obtainMemberByPhone(String phone) {
        Map<String,Object> map = new HashMap<String,Object>();
        Member member = memberMapper.checkMemberExists(phone);
        System.out.println(JSON.toJSONString(member));
        if(member!=null){
            map.put("pro",memberPropertyMapper.selectByPrimaryKey(member.getMid()));
            map.put("member",member);
        }else{
            map.put("pro",null);
            map.put("member",null);
        }
        return map;
    }

    @Override
    public int addOpenId(String openid, String uname) {
        try{
            Member member = memberMapper.checkMemberExists(uname);
            if(member==null){
                member = new Member();
                member.setOpenid(openid);
                member.setUname(uname);
                if(MemberRegister(member,new MemberProperty(),new MemberRealName(),httpServletRequest)){
                    return 1;
                }else{
                    return 0;
                }
            }else {
                if(memberMapper.updateOpenIdByPhone(openid,uname)>0){
                    return 1;
                }else{
                    return 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updatePhoneByOpenId(String openid, String uname) {
        return memberMapper.updatePhoneByOpenId(openid,uname);
    }

    static DecimalFormat df = new DecimalFormat("0");

    static public String parseDB(int celltype, HSSFCell hc) {
        if (celltype == HSSFCell.CELL_TYPE_STRING) {
            // System.out.println("s");
            return String.valueOf(hc.getStringCellValue());
        }
        if (celltype == HSSFCell.CELL_TYPE_NUMERIC) {
            //System.out.println("n");
            return String.valueOf(df.format(hc.getNumericCellValue()));
        }
        return "";
    }



}
