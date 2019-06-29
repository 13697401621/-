package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092400583128";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoI"
			+ "BAQCAv+j7ArSaNcj+zriW+xPKiM0saKTkfhj2pjl2n3QrFtd4NXrSizf+5XA"
			+ "4qB4603YSC72ZIhM66i2LHte6SH3efMZ6zi1iTZUFZMbEEEXzPgVZQAtBsh9JQQ"
			+ "LPFL5Z0YV8HQ23+YIXLDfROwLJinm99ICn6EdcnCEvZwE7z2q4eUeRFvZUM2ORkDz0V"
			+ "zhbN3bz0QjaKvKMRiwByHX3ZZbexpCnbAQwRRRJ1WujSYgR8IWcTFUwJOEXsgVLN0LxtUmKm"
			+ "agluj5wJIbu8OO29L67PNLs6Q8WS/a8zeVZ7eOOY4yBUCsvkxtMhwZSzSdQn2MHV/pwBaMf136M"
			+ "pdaa23IfAgMBAAECggEATq96/vVfVSPr7DaAv91A8jX7Vtm5dN/P9+YNFn9LKw5xr0jYAJcPKNwm"
			+ "Yqn0l7Um4s6EGHOn1wjJvs2hwHqPWotdbi5VtE9/9OTrSWY8qfVMpYgrmQH4dvehdNfToecYMos7G"
			+ "CYIKItgraHoCS51Su+ENZoTCvlsmqDUMy6HpaeyL8lV7MoUHXXz5lEWzhT1S+da6jB/NY+cWt/vRDp"
			+ "qQvEHt97pLqFb7CVCDTvefyRhAqzOlixErIxihqqimed6eSwIsqghOxlPFWsn5bgb2UiQYYp/4Xr1XZx"
			+ "KGdpOfuLLk0IwPDJpuWRSkhmt8NT1orwCxr/kfWdxbwDo0rXWeQKBgQDKLyHOl/zxFZhRpx/btyEGIvc7"
			+ "2DL7wJSxh3ptk3qJv7C2QtGA2Xm5kRMw+0MIz8jK4C8G60Vo2u2KW1v19DJvRhX+jp9jbyFCQ8170d1amQ"
			+ "qzA9uEVyRbuy69JMBzYSrFnY6m9jYMvPO++LLfYe5rWTKkT20dacbvHM6WDHoVawKBgQCjBPA3ZmmpxH4h8"
			+ "o8Q0TAUwm1lLH0kTzTh6KcasC8XpWA/DciLZTKJJYWxlJbYXNjWeUXj6gFlm3OGTP41pr7fgSkJq3NDjHw9VT"
			+ "CaMnEPigYJuyf0mnZCurDRlrqUhIWYAegCcQrZeVoGDAdMQZVsZD4qDYgvHEWn4bKHDQFPHQJ/WT50rbxo6gG"
			+ "eOmyKo3EyIoNMnh3QY0+eUr56S+PNMwBqAAl+cGGOWVqwHe7KqBkPiH1sRKGVAbOhJnEtJmOj33oQO0y5D"
			+ "ir/g0Bqcz0vd84V6u7I9qGAav00pEiUqlk5iAED9q63LOg6rxEX44JWuwukTuLARztplCIjDhhzywKBgQCJ"
			+ "nNKXQamyuP9s8tBYdu4qqBIm1Q5yxzYx+e6ONeobAOylbaI8IdouMfGQ/Yj07jPRoSAhjFlPh5FW7smtSq"
			+ "xYCjU1R3kOrZn3QGG92RTd9Dqynj3VW0q3e9dlpeKtGs2XiwxqeziYartW3XKPqdFUZa6gWzn54tjeJkVJg"
			+ "e339QKBgAX6O9CywGJW8kORR++BdpCSOcV9TB695uFmfozSIAzmFv8fVxjcHPcCf8yuL2qb2h3ehv9K/NcS"
			+ "H7FbidpcFWneXE7EnhOkBoJEZb5ZULB5XRi1B4+sa1fg4MmNHPqJe1OJlu+X44zD1DBOXG/LoRlDyCfwmrA" + "Hx2eZe3jyrVAL";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4vdUnJKDoR"
			+ "ZkWyZOSWyHM7Jm4U5w1mOSKoMxb63oLV57y62MU4mjRYPxOIfAp51y8o8v3TjCFPvlMjzA2wsM25LbTm3GmtY"
			+ "hn/BIS9+tmhpJbirSO9SWDgiGCLbC5VKhm3efhhMlLgmcwZtvY+ajq0TjyhaLFDxMPhPWPFXPoWth2C1QsKfy"
			+ "7XbGXGHQSxAGdxrPSO8CjonWBRiy8ZT9LYRaQw5H+h7O/uvEoEnzpPZ8i4qXl0UXhArbfIfX3/Ls5tIUXR/G6"
			+ "X1uYeQ0GA95ii+7d5WaR4PoJp4FJY9z1mRtEKz3k+s9z1DWjw1+Uqs1uZJ9DJLvNRS8vStvcsyDqwIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/sshjar/jsps/order/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

	/*
	 * public static String return_url =
	 * "http://localhost:8080/sshjar/jsps/order/return_url.jsp";
	 */

	public static String return_url = "http://localhost:8080/sshjar/order/isPay.action";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
