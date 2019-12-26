package com.tmri.rfid.framework.bean;
import java.io.Serializable;
import com.tmri.rfid.framework.util.StringUtil;

/**
 * <p>Title:FRM_SYSPARA_VALUE的持久类 </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 * @author long
 * @version 1.0
 */
public class SysparaValue extends Bean implements Serializable{
	private static final long serialVersionUID=1l;
    private String xtlb;
    private String glbm;
    private String gjz;
    private String csz;
    private String csbj;
    private String bjcsbj;
    public String getCsbj(){
			return csbj;
		}
		public void setCsbj(String csbj){
			this.csbj=csbj;
		}
		public String getXtlb(){
        return this.xtlb;
    }
    public void setXtlb(String xtlb1) {
        this.xtlb =xtlb1;
    }
    public String getGlbm(){
        return this.glbm;
    }
    public void setGlbm(String glbm1) {
        this.glbm =glbm1;
    }
    public String getGjz(){
        return this.gjz;
    }
    public void setGjz(String gjz1) {
        this.gjz =gjz1;
    }
    public String getCsz(){
        return StringUtil.transBlank(this.csz);
    }
    public void setCsz(String csz1) {
        this.csz =csz1;
    }
	public String getBjcsbj() {
		return bjcsbj;
	}
	public void setBjcsbj(String bjcsbj) {
		this.bjcsbj = bjcsbj;
	}
    
}
