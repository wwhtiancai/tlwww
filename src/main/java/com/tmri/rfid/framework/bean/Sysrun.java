package com.tmri.rfid.framework.bean;
import java.math.BigDecimal;
import com.tmri.rfid.framework.util.StringUtil;
/**
 *
 * <p>Title: TLWWW</p>
 *
 * <p>Description: 系统运行日志表</p>
 *
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * <p>Company: TMRI.HT</p>
 *
 * <p>Author: wengyufeng</p>
 *
 * <p>Date: 2018-11-22 16:04:22</p>
 *
 */
public class Sysrun extends Bean{
	private String bh;	//编号
	private String ip;	//IP地址
	private String fwym;	//访问页面
	private String fwsj;	//访问时间
	private BigDecimal fwys;	//访问用时 单位：ms
	private String glbm;	//管理部门
	private String yhdh;	//用户代号
	private String cdbh;	//菜单编号
	private String xzqh;	//行政区划
	private String jyw;	//校验位
	private String bz;	//备注
	//[特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	
	//[/特定属性]。

	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFwym() {
		return fwym;
	}
	public void setFwym(String fwym) {
		this.fwym = fwym;
	}
	public String getFwsj() {
		return StringUtil.transBlank(fwsj);
	}
	public void setFwsj(String fwsj) {
		this.fwsj = fwsj;
	}
	public BigDecimal getFwys() {
		return fwys;
	}
	public void setFwys(BigDecimal fwys) {
		this.fwys = fwys;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public String getYhdh() {
		return yhdh;
	}
	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}
	public String getCdbh() {
		return cdbh;
	}
	public void setCdbh(String cdbh) {
		this.cdbh = cdbh;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getJyw() {
		return jyw;
	}
	public void setJyw(String jyw) {
		this.jyw = jyw;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}