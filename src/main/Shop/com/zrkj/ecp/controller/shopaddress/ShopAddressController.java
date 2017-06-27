package com.zrkj.ecp.controller.shopaddress;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopaddress.ShopAddress;
import com.zrkj.ecp.dto.shopaddress.ShopAddressDto;
import com.zrkj.ecp.service.shopaddress.IShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
@Controller
@Scope("prototype")
@RequestMapping("shopAddressController")
public class ShopAddressController {
    @Autowired
    IShopAddressService iShopAddressService;

    /**
     * 获取商城地址列表
     * **/
    @RequestMapping("jsonShopAddressList")
    @ResponseBody
    public PageInfo<ShopAddress> jsonShopAddressList(int page, int rows, ShopAddressDto shopAddressDto){
            return iShopAddressService.obtainShopAddressList(page,rows,shopAddressDto);
    }

    /**
     * 返回商城地址页面
     * @param mv
     * @return
     */
    @RequestMapping("shopAddressPage")
    public ModelAndView shopAddressPage(ModelAndView mv){
        mv.setViewName("Shop/shopaddress/shopAddressList");
        return mv;
    }

//    public static void main(String[] args) {
//        long unixstamp=1437646938000l;
//        Date dt=new Date(unixstamp);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        System.out.println(sdf.format(dt));
//    }
}
