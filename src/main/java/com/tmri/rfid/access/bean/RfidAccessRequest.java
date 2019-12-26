package com.tmri.rfid.access.bean;
import com.tmri.rfid.framework.util.StringUtil;
/**
 *
 * <p>Title: TLWWW</p>
 *
 * <p>Description: 采集接口写入日志表</p>
 *
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * <p>Company: TMRI.HT</p>
 *
 * <p>Author: wengyufeng</p>
 *
 * <p>Date: 2018-12-06 14:40:22</p>
 *
 */
public class RfidAccessRequest{
	private String xh;	//序号
	private String yhid;	//用户ID
	private String ip;	//请求IP
	private String zs;	//提交证书
	private String qm;	//提交签名
	private String ff;	//提交方法
	private String qqjg;	//请求结果：0-失败，1-成功
	private String qqsj;	//请求时间
	private String sr;	//输入
	private String sc;	//输出
	private String zt;	//状态：0-无效，1-有效
	private String jyw;	//校验位
	private String gxsj;	//更新时间
	private String bz;	//备注
	//[特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	public String ztmc;	
	//[/特定属性]。

	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYhid() {
		return yhid;
	}
	public void setYhid(String yhid) {
		this.yhid = yhid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public String getQm() {
		return qm;
	}
	public void setQm(String qm) {
		this.qm = qm;
	}
	public String getFf() {
		return ff;
	}
	public void setFf(String ff) {
		this.ff = ff;
	}
	public String getQqjg() {
		return qqjg;
	}
	public void setQqjg(String qqjg) {
		this.qqjg = qqjg;
	}
	public String getQqsj() {
		return StringUtil.transBlank(qqsj);
	}
	public void setQqsj(String qqsj) {
		this.qqsj = qqsj;
	}
	public String getSr() {
		return sr;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
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
}