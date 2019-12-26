package com.tmri.rfid.company.bean;
/**
*
* <p>Title: TLWWW</p>
*
* <p>Description: 发行申请图片表</p>
*
* <p>Copyright: Copyright (c) 2018</p>
*
* <p>Company: TMRI.HT</p>
*
* <p>Author: paladin</p>
*
* <p>Date: 2018-07-31 14:09:50</p>
*
*/
public class CpyRfidApplyDoc{
	private String xh; // 序号
	private String sqbh; // 申请编号
	private String lx; // 类型：11-行驶证，12-标签卡，13-车辆正面，21-原卡碎片，22-补领证明材料，41-国产合格证，42-海关进口说明
	private byte[] wd; // 文档
	private String zt; // 状态，0-无效，1-有效
	private String jyw; // 校验位
	private String gxsj; // 更新时间
	private String bz; // 备注
	// [特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	private String lxmc;
	// [/特定属性]。
	public CpyRfidApplyDoc(){
	}
	public CpyRfidApplyDoc(String xh,String sqbh,String lx,byte[] wd,String zt,String jyw, String bz){
		this.xh=xh;
		this.sqbh=sqbh;
		this.lx=lx;
		this.wd=wd;
		this.zt=zt;
		this.jyw=jyw;
		this.bz=bz;
	}
	public CpyRfidApplyDoc(String lx,byte[] wd){
		this.lx=lx;
		this.wd=wd;
	}
	public String getXh(){
		return xh;
	}
	public void setXh(String xh){
		this.xh=xh;
	}
	public String getSqbh(){
		return sqbh;
	}
	public void setSqbh(String sqbh){
		this.sqbh=sqbh;
	}
	public String getLx(){
		return lx;
	}
	public void setLx(String lx){
		this.lx=lx;
	}
	public byte[] getWd(){
		return wd;
	}
	public void setWd(byte[] wd){
		this.wd=wd;
	}
	public String getZt(){
		return zt;
	}
	public void setZt(String zt){
		this.zt=zt;
	}
	public String getJyw(){
		return jyw;
	}
	public void setJyw(String jyw){
		this.jyw=jyw;
	}
	public String getGxsj(){
		return gxsj;
	}
	public void setGxsj(String gxsj){
		this.gxsj=gxsj;
	}
	public String getBz(){
		return bz;
	}
	public void setBz(String bz){
		this.bz=bz;
	}
	public String getLxmc(){
		return lxmc;
	}
	public void setLxmc(String lxmc){
		this.lxmc=lxmc;
	}
}