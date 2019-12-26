package com.tmri.rfid.framework.bean;
import com.tmri.rfid.framework.util.StringUtil;
/**
 *
 * <p>Title: TLWWW</p>
 *
 * <p>Description: 系统用户表</p>
 *
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * <p>Company: TMRI.HT</p>
 *
 * <p>Author: wengyufeng</p>
 *
 * <p>Date: 2018-10-16 11:55:08</p>
 *
 */
public class SysUser extends Bean{
	private String yhdh;	//用户代号
	private String yhzh;	//用户帐号
	private String xm;	//姓名
	private String mm;	//密码
	private String glbm;	//管理部门
	private String sfzmhm;	//身份证明号码
	private String rybh;	//人员编号
	private String qxms;	//权限类型：1-发行系统
	private String zt;	//状态：0-删除，1-启用，2-锁定，3-停用
	private String lrsj;	//录入时间
	private String zjdlsj;	//最近登录时间
	private String zjdlip;	//最近登录IP
	private String jyw;	//校验位
	private String gxsj;	//更新时间
	private String bz;	//备注
	//[特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	public String ztmc;	
	public String bmmc;
	public String ip;
	//[/特定属性]。

	public String getYhdh() {
		return yhdh;
	}
	public void setYhdh(String yhdh) {
		this.yhdh = yhdh;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getRybh() {
		return rybh;
	}
	public void setRybh(String rybh) {
		this.rybh = rybh;
	}
	public String getQxms() {
		return qxms;
	}
	public void setQxms(String qxms) {
		this.qxms = qxms;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getLrsj() {
		return StringUtil.transBlank(lrsj);
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getZjdlsj() {
		return StringUtil.transBlank(zjdlsj);
	}
	public void setZjdlsj(String zjdlsj) {
		this.zjdlsj = zjdlsj;
	}
	public String getZjdlip() {
		return zjdlip;
	}
	public void setZjdlip(String zjdlip) {
		this.zjdlip = zjdlip;
	}
	public String getJyw() {
		return jyw;
	}
	public void setJyw(String jyw) {
		this.jyw = jyw;
	}
	public String getGxsj() {
		return StringUtil.transBlank(gxsj);
	}
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getZtmc() {
		return ztmc;
	}
	public void setZtmc(String ztmc) {
		this.ztmc = ztmc;
	}
	public String getBmmc(){
		return bmmc;
	}
	public void setBmmc(String bmmc){
		this.bmmc=bmmc;
	}
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip=ip;
	}
	
}