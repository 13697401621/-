package myutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import entity.Customer;

/**
 * 自定义工具类
 * 
 * @author JiMI
 *
 */
public class EshopUtil {
	
	/**
	 * 生成uuid
	 * 
	 * @return
	 */
	public static String createUUID() {
		String uuid = UUID.randomUUID().toString();
		String s = uuid.replaceAll("-", "");
		return s.toUpperCase();
	}
	
	/**
	 * 获取当前时间，将该时间转化为字符串
	 * 
	 * @return 
	 */
	public static String getNowTime() {
		Date newTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeString = sdf.format(newTime);
		return timeString;
	}
	
	/**
	 * 判断是否登录
	 * @return
	 */
	public static boolean customerIsLogin() {
		Customer customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		if(customer == null) {
			return false;
		}
		return true;
	}
}
