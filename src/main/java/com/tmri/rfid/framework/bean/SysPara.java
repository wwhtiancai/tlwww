package com.tmri.rfid.framework.bean;
import java.io.Serializable;
import java.math.BigDecimal;

public class SysPara extends Bean implements Serializable {
	private static final long serialVersionUID=1l;
	private String xtlb;
	private String cslx;
	private String gjz;
	private String csmc;
	private String cssm;
	private String mrz;
	private String xgjb;
	private String sfxs;
	private BigDecimal xssx;
	private String sjlx;
	private String sjgf;
	private String cssx;
	private String bz;
	private String xsxs;
	private String jyw;
	private String dmlb;
	private String fzmc;
	private String csbj;
	private String bjcsbj;
	
	public SysPara() {}
	public SysPara(String xtlb,String gjz) {
		this.xtlb=xtlb;
		this.gjz=gjz;
	}
	
	public String getDmlb() {
		return dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getXsxs() {
		return xsxs;
	}

	public void setXsxs(String xsxs) {
		this.xsxs = xsxs;
	}

	public String getXtlb() {
		return this.xtlb;
	}

	public void setXtlb(String xtlb1) {
		this.xtlb = xtlb1;
	}

	public String getCslx() {
		return this.cslx;
	}

	public void setCslx(String cslx1) {
		this.cslx = cslx1;
	}

	public String getGjz() {
		return this.gjz;
	}

	public void setGjz(String gjz1) {
		this.gjz = gjz1;
	}

	public String getCsmc() {
		return this.csmc;
	}

	public void setCsmc(String csmc1) {
		this.csmc = csmc1;
	}

	public String getCssm() {
		return this.cssm;
	}

	public void setCssm(String cssm1) {
		this.cssm = cssm1;
	}

	public String getMrz() {
		return this.mrz;
	}

	public void setMrz(String mrz1) {
		this.mrz = mrz1;
	}

	public String getXgjb() {
		return this.xgjb;
	}

	public void setXgjb(String xgjb1) {
		this.xgjb = xgjb1;
	}

	public String getSfxs() {
		return this.sfxs;
	}

	public void setSfxs(String sfxs1) {
		this.sfxs = sfxs1;
	}

	public String getSjlx() {
		return this.sjlx;
	}

	public void setSjlx(String sjlx1) {
		this.sjlx = sjlx1;
	}

	public String getSjgf() {
		return this.sjgf;
	}

	public void setSjgf(String sjgf1) {
		this.sjgf = sjgf1;
	}

	public String getCssx() {
		return this.cssx;
	}

	public void setCssx(String cssx1) {
		this.cssx = cssx1;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz1) {
		this.bz = bz1;
	}

	public String getJyw() {
		return jyw;
	}

	public void setJyw(String jyw) {
		this.jyw = jyw;
	}

	public BigDecimal getXssx() {
		return xssx;
	}

	public void setXssx(BigDecimal xssx) {
		this.xssx = xssx;
	}

	public String getFzmc() {
		return fzmc;
	}

	public void setFzmc(String fzmc) {
		this.fzmc = fzmc;
	}

	public String getCsbj() {
		return csbj;
	}

	public void setCsbj(String csbj) {
		this.csbj = csbj;
	}

	public String getBjcsbj() {
		return bjcsbj;
	}

	public void setBjcsbj(String bjcsbj) {
		this.bjcsbj = bjcsbj;
	}
}
