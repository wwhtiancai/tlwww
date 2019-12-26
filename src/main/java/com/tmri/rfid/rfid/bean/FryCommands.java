package com.tmri.rfid.rfid.bean;

import java.util.Date;

/**
 * @author st
 * @Date 2018-12-19
 */
public class FryCommands {

	private String jhxh;	//交换序号
	private String sjbbh;	//数据包编号
	private String bm;	//表名
	private String zd;	//字段
	private String zj;	//主键
	private String zjz;	//主键值
	private String fzl;	//封装类
	private String ccgc;	//存储过程
	private String hdhs;	//回调函数
	private Date fssj;	//发送时间
	private String fscwnr;	//发送错误内容
	private String zt;	//状态
	private String jyw;	//校验位
	private Date gxsj;	//更新时间
	private String bz;	//备注

	private String sj; //数据

	public String getJhxh() {
		return jhxh;
	}

	public void setJhxh(String jhxh) {
		this.jhxh = jhxh;
	}

	public String getSjbbh() {
		return sjbbh;
	}

	public void setSjbbh(String sjbbh) {
		this.sjbbh = sjbbh;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZj() {
		return zj;
	}

	public void setZj(String zj) {
		this.zj = zj;
	}

	public String getZjz() {
		return zjz;
	}

	public void setZjz(String zjz) {
		this.zjz = zjz;
	}

	public String getFzl() {
		return fzl;
	}

	public void setFzl(String fzl) {
		this.fzl = fzl;
	}

	public String getCcgc() {
		return ccgc;
	}

	public void setCcgc(String ccgc) {
		this.ccgc = ccgc;
	}

	public String getHdhs() {
		return hdhs;
	}

	public void setHdhs(String hdhs) {
		this.hdhs = hdhs;
	}

	public Date getFssj() {
		return fssj;
	}

	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}

	public String getFscwnr() {
		return fscwnr;
	}

	public void setFscwnr(String fscwnr) {
		this.fscwnr = fscwnr;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getJyw() {
		return jyw;
	}

	public void setJyw(String jyw) {
		this.jyw = jyw;
	}

	public Date getGxsj() {
		return gxsj;
	}

	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}
}
