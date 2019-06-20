//package com.yiji.openapi;/*
// * www.yiji.com Inc.
// * Copyright (c) 2016 All Rights Reserved
// */
//
///*
// * 修订记录:
// * zhike@yiji.com 2016-06-07 14:15 创建
// *
// */
//
//import com.alibaba.fastjson.JSON;
//import com.yiji.openapi.message.common.cashier.CommonCashierPayGatewayRequest;
//import com.yiji.openapi.message.common.oldtonew.BankNoQueryRequest;
//import com.yiji.openapi.message.common.oldtonew.BankNoQueryResponse;
//import com.yiji.openapi.message.common.query.AccountPageQueryRequest;
//import com.yiji.openapi.message.common.query.AccountPageQueryResponse;
//import com.yiji.openapi.message.common.trade.info.GoodsClause;
//import com.yiji.openapi.message.mpay.common.MpayResetLoginPasswordRedirectRequest;
//import com.yiji.openapi.message.product.ewpay.QftSetDefaultBankCardRequest;
//import com.yiji.openapi.sdk.ApiSdkConstants;
//import com.yiji.openapi.sdk.YijiPayClient;
//import com.yiji.openapi.sdk.common.utils.Ids;
//import com.yiji.openapi.sdk.common.utils.Money;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @author zhike@yiji.com
// */
//public class TestSpring extends AbstractDependencyInjectionSpringContextTests {
//	private static Log logger = LogFactory.getLog(TestSpring.class);
//
//	private YijiPayClient yijiPayClient;
//
//	@Override
//	public String[] getConfigLocations() {
//		logger.info("加载配置文件");
//		String[] configLocations = { "/spring/yiji-openapi-sdk.xml" };
//		return configLocations;
//	}
//
//	/**
//	 * 测试同步接口
//	 */
//	public void testBankNoQuery() {
//		BankNoQueryRequest request = new BankNoQueryRequest();
//		request.setBankId("CCB");
//		request.setDistrictName("宁波市");
//		request.setSignType("MD5");
//		BankNoQueryResponse response = yijiPayClient.request(request);
//		System.out.println(response.getBranchName());
//	}
//
//	/**
//	 * 测试复杂响应报文
//	 */
//	public void testAccountPageQuery() {
//		AccountPageQueryRequest request = new AccountPageQueryRequest();
//		request.setTradeNo("20160616000010042391");
//		request.setCardNo("20141229010000062189");
//		request.setBeginCreateTime(StrToDate("2016-06-01 00:00:00"));
//		request.setEndCreateTime(StrToDate("2016-06-30 00:00:00"));
//		AccountPageQueryResponse response = yijiPayClient.request(request);
//		System.out.println("响应报文："+JSON.toJSONString(response));
//	}
//
//	/**
//	 * 执行自己组装服务
//	 */
//	public void testPostRequest() {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("bankId", "CCB");
//		map.put("districtName", "宁波市");
//		map.put("signType", "MD5");
//		map.put("protocol", "HTTP_FORM_JSON");
//		map.put("orderNo", Ids.oid());
//		map.put("service", "bankNoQuery");
//		map.put("partnerId", "20141106020000058750");
//		map.put("version", "1.0");
//		//签名
//		String signString = yijiPayClient.sign(map);
//		map.put("sign", signString);
//		//请求响应
//		String responseStr = yijiPayClient.post(ApiSdkConstants.SERVICE_GATEWAY, map);
//		//验签
//		yijiPayClient.verySign(responseStr);
//		System.out.println("响应报文：" + responseStr);
//
//	}
//
////	/**
////	 * 测试加解密
////	 */
////	public void testEncryptAndDecrypt() {
////		String encryptStr = yijiPayClient.encrypt("20150122020000063895", "RSA");
////		System.out.println("加密后的字符串" + encryptStr);
////		String decryptStr = yijiPayClient.decrypt(encryptStr, "RSA");
////		System.out.println("解密后的字符串" + decryptStr);
////	}
//
//	/**
//	 * 测试map签名
//	 */
//	public void testSignMap() {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("bankId", "CCB");
//		map.put("districtName", "宁波市");
//		map.put("signType", "MD5");
//		System.out.println(yijiPayClient.sign(map));
//	}
//
//	/**
//	 * 测试list签名
//	 */
//	public void testSignList() {
//		List<String> list = new ArrayList<String>();
//		list.add("bankId=CCB");
//		list.add("districtName=宁波市");
//		list.add("signType=MD5");
//		System.out.println(yijiPayClient.sign(list));
//	}
//
//	/**
//	 * 测试字符串签名
//	 */
//	public void testSignStr() {
//		String waitSignStr = "bankId=CCB&districtName=宁波市&orderNo=O00116071210144618200000&partnerId=20141106020000058750&protocol=HTTP_FORM_JSON&service=bankNoQuery&signType=RSA&version=1.0";
//		System.out.println(yijiPayClient.sign(waitSignStr));
//	}
//
//	public void testVerySign() {
//		//		boolean isPass = yijiPayClient.verySign("{\"sign\":\"b03d3b83e3ec404844f15f9b24acde09\",\"protocol\":\"HTTP_FORM_JSON\",\"orderNo\":\"O00116071210375118200000\",\"signType\":\"MD5\",\"service\":\"bankNoQuery\",\"resultCode\":\"EXECUTE_SUCCESS\",\"branchName\":\"中国建设银行余姚城建支行\",\"partnerId\":\"20141106020000058750\",\"bankLasalle\":\"105332452436\",\"resultMessage\":\"成功\",\"success\":true,\"version\":\"1.0\"}");
//		boolean isPass = yijiPayClient
//			.verySign("{\"accountName\":\"openapi@qq.com\",\"withdrawType\":\"WITHDRAW_SINGLE\",\"resultCode\":\"EXECUTE_SUCCESS\",\"sign\":\"b95d7bcd9a538959ee2b04f36b96746f\",\"bankAccountNo\":\"6227003814170172872\",\"memo\":\"提现\",\"bankName\":\"建设银行\",\"protocol\":\"httpJson\",\"payAmount\":140.00,\"rawAddTime\":\"2016-04-27 18:45:55\",\"accountNo\":\"20141211010000060833\",\"payAmountIn\":150.00,\"signType\":\"MD5\",\"withdrawId\":\"000700m01ftbwfdtdc33\",\"bankAccountName\":\"ahuang\",\"bankCode\":\"CCB\",\"orderNo\":\"1461750711612339\",\"outBizNo\":\"1461750711612339\",\"accountType\":\"PERSONAL\",\"outBizContext\":{\"CURRENCY\":\"CNY\",\"CHARGE_ROLES_KEY\":\"TRADE_MERCHANT=20150312020000066904,TRADE_PARTNER=20150312020000066904,\",\"CHARGE_OPTION\":\"Y\",\"SPECIAL_EASY_TRADE\":\"N\",\"DUBBO_GROUP\":\"openapi.c.y.p.r.s.ReceivePayAckWithResultService\",\"WITHDRAW_INTER_FACADE_FLAG\":\"NY\",\"TRADE_BIZ_PRODUCT_CODE\":\"openapi_withDraw_actor\",\"TRANS_ACTIONS_CONTEXT\":\"bizCode=DEFAULT,subBizCode=PAY_MOBILE,\",\"CHARGE_RULE\":\"chargeV2\",\"DUBBO_VERSION\":\"1.5\"},\"resultMessage\":\"成功\",\"version\":\"1.0\",\"success\":true,\"service\":\"applyWithdraw\",\"payAmountCurrency\":\"CNY\",\"bankCnapsNo\":\"52013\",\"bizIdentity\":\"MERCHANT\",\"subContractCode\":\"ASYNC_WITHDRAW_CONTRACT\",\"partnerId\":\"20150312020000066904\",\"status\":\"SUBMIT_SETTLED\"}");
//		System.out.println("验签结果：" + isPass);
//	}
//
//	/**
//	 * 测试跳转服务
//	 */
//	public void testMpayResetLoginPasswordRedirectRedirect() {
//		MpayResetLoginPasswordRedirectRequest request = new MpayResetLoginPasswordRedirectRequest();
//		request.setUserId("20151123010000082871");
//		request.setSignType("RSA");
//		request.setOutOrderNo("20151123010000082871");
//		request
//			.setReturnUrl("http://192.168.57.74:8080/openapiexecuterdemo/mpayResetLoginPasswordRedirect/returnView.html");
//		request
//			.setNotifyUrl("http://192.168.57.74:8080/openapiexecuterdemo/mpayResetLoginPasswordRedirect/commonNotifyView.html");
//		String response = yijiPayClient.redirect(request);
//		System.out.println(response);
//	}
//
//	public void testQftSetDefaultBankCardRedirect() {
//		QftSetDefaultBankCardRequest request = new QftSetDefaultBankCardRequest();
//		request.setUserId("20151123010000082871");
//		request.setUserId("20151123010000082871");
//		request.setPartnerShopId("20151123010000082871");
//		request.setSignType("MD5");
//		String redirectURL = yijiPayClient.redirect(request);
//		System.out.println("跳转地址：" + redirectURL);
//	}
//
//    public void testGetEncode() {
//        CommonCashierPayGatewayRequest request = new CommonCashierPayGatewayRequest();
//        request.setSignType("MD5");
//        request.setBankCode("CCB");
//        request.setDebitCreditType("DEBIT");
//        request.setPersonalCorporateType("PERSONAL");
//        request.setSellerUserId("20141211010000060833");
//        request.setTradeAmount(new Money(100));
//        List<GoodsClause> goodsClauses = new ArrayList<GoodsClause>();
//        GoodsClause goods = new GoodsClause();
//        goods.setName("食品");
//        goodsClauses.add(goods);
//        request.setGoodsClauses(goodsClauses);
//        request.setBuyerMobile("15215172440");
//        request.setCurrency("CNY");
//        String outOrderNo = "001" + System.currentTimeMillis();
//        request.setOutOrderNo(outOrderNo);
//        request.setReturnUrl("http://192.168.49.182:8080/openapiexecuterdemo/mpayResetLoginPasswordRedirect/returnView.html");
//        request.setNotifyUrl(ApiSdkConstants.NOTIFY_URL);
//        String redirectURL = yijiPayClient.redirect(request);
//        System.out.println("跳转地址：" + redirectURL);
//    }
//	/**
//	 * 字符串转化为时间格式
//	 * @param str
//	 * @return
//	 */
//	public static Date StrToDate(String str) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = null;
//		try {
//			date = format.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
//
//    public YijiPayClient getYijiPayClient() {
//        return yijiPayClient;
//    }
//
//    public void setYijiPayClient(YijiPayClient yijiPayClient) {
//        this.yijiPayClient = yijiPayClient;
//    }
//
//}
