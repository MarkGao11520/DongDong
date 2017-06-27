package com.zrkj.ecp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zrkj.ecp.dao.shopgoods.ShopGoodsClassMapper;
import com.zrkj.ecp.dto.shoporder.ShopOrderDto;
import com.zrkj.ecp.dto.shoporder.ShopOrderGoodsDto;
import com.zrkj.ecp.service.shoporder.IShopOrderGoodsService;
import com.zrkj.ecp.service.shoporder.IShopOrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DongDongApplication.class)
//@SpringBootConfiguration(classes = DongDongApplication.class)
@SpringBootTest(classes = DongDongApplication.class)
@WebAppConfiguration
@Transactional
public class DongDongApplicationTests {
    @Autowired
    ShopGoodsClassMapper shopGoodsClassMapper;

	@Autowired
    IShopOrderService iShopOrderService;

	@Autowired
    IShopOrderGoodsService iShopOrderGoodsService;

    @Autowired
	WebApplicationContext webApplicationContext;

    MockMvc mvc;

    String expectedJson;

    @Before
	public void setUp() throws JsonProcessingException{
//    	expectedJson = JSON.toJSONString("电子设备");
//
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void contextLoads() throws Exception{
        ShopOrderGoodsDto shopOrderGoodsDto = new ShopOrderGoodsDto();
        shopOrderGoodsDto.setOrderId(447);
		System.out.println(JSON.toJSONString(iShopOrderGoodsService.obtianShopOrderGoodsList(1,10,shopOrderGoodsDto)));
//    	String uri = "shopGoodsClassController/jsonTopShopGoodsClassList?page=1&rows=10";
//
//		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
//
//		int stauts = result.getResponse().getStatus();
//		String content = result.getResponse().getContentAsString();
//
//		Assert.assertEquals("错误，正确的返回值为200",200,stauts);
//		Assert.assertEquals("错误，返回值和预期返回值不一致",expectedJson,content);
    }

}
