package com.tmri.rfid.framework.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.tmri.rfid.framework.util.StringUtil;
/**
 * <p>
 * Title:FRM_PROGRAM的持久类
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author long
 * @version 1.0
 */
public class Program extends Bean implements Serializable{
	private static final long serialVersionUID=1l;
	private String xtlb;
	private String cdbh;
	private String mldh;
	private String cxmc;
	private String ymdz;
	private BigDecimal sxh;
	private String cxsx;
	private String yxsjk;
	private String fwfs;
	private String tbmc;
	private String rzqlfs;
	private BigDecimal rzblts;
	private String ckdx;
	private String cxlx;
	private String gnid;
	private List gnList;
	private String xtmc;
	private String mlmc;
	private String gnmc;
	private String flag;
	private String cxlb;
	public String getCxlb(){
		return StringUtil.transBlank(cxlb);
	}
	public void setCxlb(String cxlb){
		this.cxlb=cxlb;
	}
	public String getFlag(){
		return flag;
	}
	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getXtmc(){
		return StringUtil.transBlank(xtmc);
	}
	public void setXtmc(String xtmc){
		this.xtmc=xtmc;
	}
	public String getMlmc(){
		return StringUtil.transBlank(mlmc);
	}
	public void setMlmc(String mlmc){
		this.mlmc=mlmc;
	}
	public String getGnmc(){
		return StringUtil.transBlank(gnmc);
	}
	public void setGnmc(String gnmc){
		this.gnmc=gnmc;
	}
	private String bz;
	public String getCdbh(){
		return cdbh;
	}
	public void setCdbh(String cdbh){
		this.cdbh=cdbh;
	}
	public List getGnList(){
		return gnList;
	}
	public void setGnList(List gnList){
		this.gnList=gnList;
	}
	public String getXtlb(){
		return this.xtlb;
	}
	public void setXtlb(String xtlb1){
		this.xtlb=xtlb1;
	}
	public String getMldh(){
		return this.mldh;
	}
	public void setMldh(String mldh1){
		this.mldh=mldh1;
	}
	public String getCxmc(){
		return this.cxmc;
	}
	public void setCxmc(String cxmc1){
		this.cxmc=cxmc1;
	}
	public String getYmdz(){
		return this.ymdz;
	}
	public void setYmdz(String ymdz1){
		this.ymdz=ymdz1;
	}
	public String getCxsx(){
		return this.cxsx;
	}
	public String getFwfs(){
		return fwfs;
	}
	public void setFwfs(String fwfs){
		this.fwfs=fwfs;
	}
	public String getRzqlfs(){
		return rzqlfs;
	}
	public void setCxsx(String cxsx1){
		this.cxsx=cxsx1;
	}
	public void setRzqlfs(String rzqlfs1){
		this.rzqlfs=rzqlfs1;
	}
	public String getBz(){
		return this.bz;
	}
	public void setBz(String bz1){
		this.bz=bz1;
	}
	public String getTbmc(){
		return tbmc;
	}
	public void setTbmc(String tbmc){
		this.tbmc=tbmc;
	}
	public BigDecimal getSxh(){
		return sxh;
	}
	public void setSxh(BigDecimal sxh){
		this.sxh=sxh;
	}
	public String getYxsjk(){
		return yxsjk;
	}
	public void setYxsjk(String yxsjk){
		this.yxsjk=yxsjk;
	}
	public BigDecimal getRzblts(){
		return rzblts;
	}
	public void setRzblts(BigDecimal rzblts){
		this.rzblts=rzblts;
	}
	public String getCkdx(){
		return ckdx;
	}
	public void setCkdx(String ckdx){
		this.ckdx=ckdx;
	}
	public String getCxlx(){
		return cxlx;
	}
	public void setCxlx(String cxlx){
		this.cxlx=cxlx;
	}
	public String getGnid(){
		return gnid;
	}
	public void setGnid(String gnid){
		this.gnid=gnid;
	}
	private String glysy;
	private String jyw;
	public String getGlysy(){
		return glysy;
	}
	public void setGlysy(String glysy){
		this.glysy=glysy;
	}
	public String getJyw(){
		return jyw;
	}
	public void setJyw(String jyw){
		this.jyw=jyw;
	}
}
