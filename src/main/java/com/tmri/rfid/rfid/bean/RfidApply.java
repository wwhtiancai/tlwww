package com.tmri.rfid.rfid.bean;

/**
*
* <p>Title: TLWWW</p>
*
* <p>Description: 发行申请表</p>
*
* <p>Copyright: Copyright (c) 2018</p>
*
* <p>Company: TMRI.HT</p>
*
* <p>Author: paladin</p>
*
* <p>Date: 2018-07-31 14:08:54</p>
*
*/
public class RfidApply{
	private String sqbh; // 申请编号
	private String lsh; // 业务流水号
	private String sqlx; // 申请类型：第一位：1-申领、2-补领、3-变更，第二位：业务原因
	private String jdcxh; // 机动车序号
	private String hpzl; // 号牌种类
	private String hphm; // 号牌号码
	private String sycplx; // 商业产品类型
	private String sycpid; // 商业产品ID
	private String xszh; // 行驶证号
	private String tid; // 标签ID
	private String kh; // 卡号，不带校验位
	private String ywdw; // 业务管理部门
	private String ywdwmc; // 业务管理部门名称
	private String lrr; // 录入人
	private String lrrmc; // 录入人姓名
	private String lrsj; // 录入时间
	private String gdr; // 归档人
	private String gdrmc; // 归档人姓名
	private String gdsj; // 归档时间
	private String tbyy; // 退办原因
	private String tbsm; // 退办说明
	private String tbyzt; // 退办原状态
	private String sfysw; // 退办时是否有实物：0-无，1-有
	private String clyw; // 处理业务
	private String clzt; // 处理状态：0-未处理，1-处理正确，2-处理失败
	private String clnr; // 处理内容
	private String zt; // 状态：0-无效，1-已生成，2-已写入（已使用），3-已失效（未使用），4-已取消（不使用）
	private String jyw; // 校验位
	private String gxr;// 更新人
	private String gxrxm; // 更新人姓名
	private String gxsj; // 更新时间
	private String bz; // 备注
	// [特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	public String hpzlmc;
	public String ztmc;
	public String sqlxmc;
	public String tbyztmc;
	public String sqyy;
	public String kssj;
	public String jssj;
	public String sjlx;
	public String tbyymc;
	public String sfyswmc;
	public String qssj;
	public String zzsj;
	public byte[] xsztp;
	public byte[] bsktp;
	public byte[] qctp;
	// [/特定属性]。
	
	public RfidApply() {}
	public RfidApply(String sqbh,String zt) {
		this.sqbh=sqbh;
		this.zt=zt;
	}
	public RfidApply(String hpzl,String hphm,String kh,String zt) {
		this.hpzl=hpzl;
		this.hphm=hphm;
		this.kh=kh;
		this.zt=zt;
	}
	public String getQssj(){
		return qssj;
	}
	public void setQssj(String qssj){
		this.qssj=qssj;
	}
	public String getZzsj(){
		return zzsj;
	}
	public void setZzsj(String zzsj){
		this.zzsj=zzsj;
	}
	public String getSycplx(){
		return sycplx;
	}
	public void setSycplx(String sycplx){
		this.sycplx=sycplx;
	}
	public String getSycpid(){
		return sycpid;
	}
	public void setSycpid(String sycpid){
		this.sycpid=sycpid;
	}
	public String getXszh(){
		return xszh;
	}
	public void setXszh(String xszh){
		this.xszh=xszh;
	}
	public String getTbyymc(){
		return tbyymc;
	}
	public void setTbyymc(String tbyymc){
		this.tbyymc=tbyymc;
	}
	public String getSjlx(){
		return sjlx;
	}
	public void setSjlx(String sjlx){
		this.sjlx=sjlx;
	}
	public String getKssj(){
		return kssj;
	}
	public void setKssj(String kssj){
		this.kssj=kssj;
	}
	public String getJssj(){
		return jssj;
	}
	public void setJssj(String jssj){
		this.jssj=jssj;
	}
	public String getHpzlmc(){
		return hpzlmc;
	}
	public void setHpzlmc(String hpzlmc){
		this.hpzlmc=hpzlmc;
	}
	public String getSqyy(){
		return sqyy;
	}
	public void setSqyy(String sqyy){
		this.sqyy=sqyy;
	}
	public String getZtmc(){
		return ztmc;
	}
	public void setZtmc(String ztmc){
		this.ztmc=ztmc;
	}
	public String getSqlxmc(){
		return sqlxmc;
	}
	public void setSqlxmc(String sqlxmc){
		this.sqlxmc=sqlxmc;
	}
	public String getTbyztmc(){
		return tbyztmc;
	}
	public void setTbyztmc(String tbyztmc){
		this.tbyztmc=tbyztmc;
	}
	public String getSqbh(){
		return sqbh;
	}
	public void setSqbh(String sqbh){
		this.sqbh=sqbh;
	}
	public String getLsh(){
		return lsh;
	}
	public void setLsh(String lsh){
		this.lsh=lsh;
	}
	public String getSqlx(){
		return sqlx;
	}
	public void setSqlx(String sqlx){
		this.sqlx=sqlx;
	}
	public String getJdcxh(){
		return jdcxh;
	}
	public void setJdcxh(String jdcxh){
		this.jdcxh=jdcxh;
	}
	public String getHpzl(){
		return hpzl;
	}
	public void setHpzl(String hpzl){
		this.hpzl=hpzl;
	}
	public String getHphm(){
		return hphm;
	}
	public void setHphm(String hphm){
		this.hphm=hphm;
	}
	public String getTid(){
		return tid;
	}
	public void setTid(String tid){
		this.tid=tid;
	}
	public String getKh(){
		return kh;
	}
	public void setKh(String kh){
		this.kh=kh;
	}
	public String getYwdw(){
		return ywdw;
	}
	public void setYwdw(String ywdw){
		this.ywdw=ywdw;
	}
	public String getYwdwmc(){
		return ywdwmc;
	}
	public void setYwdwmc(String ywdwmc){
		this.ywdwmc=ywdwmc;
	}
	public String getLrr(){
		return lrr;
	}
	public void setLrr(String lrr){
		this.lrr=lrr;
	}
	public String getLrrmc(){
		return lrrmc;
	}
	public void setLrrmc(String lrrmc){
		this.lrrmc=lrrmc;
	}
	public String getGdr(){
		return gdr;
	}
	public void setGdr(String gdr){
		this.gdr=gdr;
	}
	public String getGdrmc(){
		return gdrmc;
	}
	public void setGdrmc(String gdrmc){
		this.gdrmc=gdrmc;
	}
	public String getTbyy(){
		return tbyy;
	}
	public void setTbyy(String tbyy){
		this.tbyy=tbyy;
	}
	public String getTbsm(){
		return tbsm;
	}
	public void setTbsm(String tbsm){
		this.tbsm=tbsm;
	}
	public String getTbyzt(){
		return tbyzt;
	}
	public void setTbyzt(String tbyzt){
		this.tbyzt=tbyzt;
	}
	public String getSfysw(){
		return sfysw;
	}
	public void setSfysw(String sfysw){
		this.sfysw=sfysw;
	}
	public String getClyw(){
		return clyw;
	}
	public void setClyw(String clyw){
		this.clyw=clyw;
	}
	public String getClzt(){
		return clzt;
	}
	public void setClzt(String clzt){
		this.clzt=clzt;
	}
	public String getClnr(){
		return clnr;
	}
	public void setClnr(String clnr){
		this.clnr=clnr;
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
	public String getGxr(){
		return gxr;
	}
	public void setGxr(String gxr){
		this.gxr=gxr;
	}
	public String getGxrxm(){
		return gxrxm;
	}
	public void setGxrxm(String gxrxm){
		this.gxrxm=gxrxm;
	}
	public String getLrsj(){
		return lrsj;
	}
	public void setLrsj(String lrsj){
		this.lrsj=lrsj;
	}
	public String getGdsj(){
		return gdsj;
	}
	public void setGdsj(String gdsj){
		this.gdsj=gdsj;
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
	public String getSfyswmc(){
		return sfyswmc;
	}
	public void setSfyswmc(String sfyswmc){
		this.sfyswmc=sfyswmc;
	}
	public byte[] getXsztp(){
		return xsztp;
	}
	public void setXsztp(byte[] xsztp){
		this.xsztp=xsztp;
	}
	public byte[] getBsktp(){
		return bsktp;
	}
	public void setBsktp(byte[] bsktp){
		this.bsktp=bsktp;
	}
	public byte[] getQctp(){
		return qctp;
	}
	public void setQctp(byte[] qctp){
		this.qctp=qctp;
	}

}