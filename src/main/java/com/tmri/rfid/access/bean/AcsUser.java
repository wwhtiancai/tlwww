package com.tmri.rfid.access.bean;
import java.math.BigDecimal;
import com.tmri.rfid.framework.util.StringUtil;
/**
 *
 * <p>Title: TLWWW</p>
 *
 * <p>Description: 接口用户表</p>
 *
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * <p>Company: TMRI.HT</p>
 *
 * <p>Author: wengyufeng</p>
 *
 * <p>Date: 2018-12-06 16:35:54</p>
 *
 */
public class AcsUser{
	private String xh;	//UID
	private String rjmc;	//软件名称
	private String dydw;	//调用单位编号
	private String yfdwmc;	//研发单位名称
	private String lxrxm;	//联系人姓名
	private String lxfs;	//联系方式
	private String ipxz;	//IP限制：多个IP，用半角逗号分隔
	private String yxqz;	//有效期止
	private String sdxz;	//时段限制：多个小时，用半角逗号分隔
	private BigDecimal csxz;	//次数限制
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
	public String getRjmc() {
		return rjmc;
	}
	public void setRjmc(String rjmc) {
		this.rjmc = rjmc;
	}
	public String getDydw() {
		return dydw;
	}
	public void setDydw(String dydw) {
		this.dydw = dydw;
	}
	public String getYfdwmc() {
		return yfdwmc;
	}
	public void setYfdwmc(String yfdwmc) {
		this.yfdwmc = yfdwmc;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	public String getIpxz() {
		return ipxz;
	}
	public void setIpxz(String ipxz) {
		this.ipxz = ipxz;
	}
	public String getYxqz() {
		return StringUtil.transBlank(yxqz);
	}
	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}
	public String getSdxz() {
		return sdxz;
	}
	public void setSdxz(String sdxz) {
		this.sdxz = sdxz;
	}
	public BigDecimal getCsxz() {
		return csxz;
	}
	public void setCsxz(BigDecimal csxz) {
		this.csxz = csxz;
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