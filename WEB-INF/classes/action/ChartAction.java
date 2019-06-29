package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class ChartAction extends ActionSupport {

	private Map<String,String> msgMap = new HashMap();
	private String cusername;
	private String lookMsg;
	private String sendMsg;
	
	public String getMsg() throws IOException {
		System.out.println(cusername);
	    ServletContext servletContext = ServletActionContext.getServletContext();
		msgMap = (Map<String, String>)servletContext.getAttribute("msgMap");
		Set<String> set = msgMap.keySet();
		for (String key : set) {
			System.out.println("msg:"+msgMap.get(key));
		}
		lookMsg = msgMap.get(cusername);
		Map<String,String> map = new HashMap<>();
		map.put("lookMsg", lookMsg);
		
		JSONObject msg = JSONObject.fromObject(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		System.out.println(msg.toString());
		ServletActionContext.getResponse().getWriter().println(msg.toString());
		return NONE;
	}
	public String sendMsg() {
		System.out.println("sendMsg:"+cusername);
		ServletContext servletContext = ServletActionContext.getServletContext();
		msgMap = (Map<String, String>)servletContext.getAttribute("msgMap");
		String oldMsg = msgMap.get(cusername);
		lookMsg = oldMsg +"\n"+sendMsg;
		msgMap.put(cusername, lookMsg);
		servletContext.setAttribute("msgMap",msgMap);
		
		return NONE;
	}
	public String changeCustomer() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		msgMap = (Map<String, String>)servletContext.getAttribute("msgMap");
		ServletActionContext.getRequest().setAttribute("cusername", cusername);
		
		return "change";
	}
	
	public Map<String, String> getMsgMap() {
		return msgMap;
	}
	public void setMsgMap(Map<String, String> msgMap) {
		this.msgMap = msgMap;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	public String getLookMsg() {
		return lookMsg;
	}
	public void setLookMsg(String lookMsg) {
		this.lookMsg = lookMsg;
	}
	public String getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}
	
	
}
