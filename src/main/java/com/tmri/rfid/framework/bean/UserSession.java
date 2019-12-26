package com.tmri.rfid.framework.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserSession implements Serializable{
	private static final long serialVersionUID=1L;
	private SysUser sysuser;
	private Department department;
	private String sessionid;
	private String yhdh;
	private String glbm;
	private HashMap<String,String> rights;
	private List<String> menuList;
	public String scdlsj;
	
	public String getSessionid(){
		return sessionid;
	}
	public void setSessionid(String sessionid){
		this.sessionid=sessionid;
	}
	public Department getDepartment(){
		return department;
	}
	public void setDepartment(Department department){
		this.department=department;
	}
	public String getYhdh(){
		return yhdh;
	}
	public void setYhdh(String yhdh){
		this.yhdh=yhdh;
	}
	public SysUser getSysuser(){
		return sysuser;
	}
	public void setSysuser(SysUser sysuser){
		this.sysuser=sysuser;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public HashMap<String, String> getRights() {
		return rights;
	}
	public void setRights(HashMap<String, String> rights) {
		this.rights = rights;
	}
	
	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public void addMenu(String menuUrl) {
		if (menuList == null) menuList = new ArrayList<String>();
		menuList.add(menuUrl);
	}
	public String getScdlsj(){
		return scdlsj;
	}
	public void setScdlsj(String scdlsj){
		this.scdlsj=scdlsj;
	}
	
}
