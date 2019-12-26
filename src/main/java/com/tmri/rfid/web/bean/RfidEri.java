package com.tmri.rfid.web.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import com.tmri.rfid.framework.util.StringUtil;
/**
 * Created by wuweihong on 2018/9/19.
 */
public class RfidEri implements Serializable{
	private String tid; // 标签ID
	private String pch; // 批次号，8位序列号
	private String xh; // 箱号，10位序列号
	private String hh; // 盒号，12位序列号
	private String kh; // 卡号，不带校验位
	private String lsh; // 流水号
	private String fkbbh; // 发卡版本号，处理读写密钥历史遗留问题
	private String fklx; // 发卡类型，1-交通管理机关发行卡，2-汽车厂家预装卡
	private String jdcxh; // 机动车序号
	private String hpzl; // 号牌种类
	private String hphm; // 号牌号码
	private String clpp; // 车辆品牌
	private String gcjk; // 国产/进口
	private String clsbdh; // 车辆识别代号
	private String fdjh; // 发动机号
	private String cllx; // 车辆类型
	private String syxz; // 使用性质
	private String csys; // 车身颜色
	private String jdcsyr; // 机动车所有人
	private String jdcsyrlxfs; // 机动车所有人联系方式
	private String ccdjrq; // 初次登记日期
	private String yxqz; // 检验有效期止
	private String qzbfqz; // 强制报废期止
	private String fzjg; // 发证机关
	private String glbm; // 管理部门
	private String glbmmc; // 管理部门名称
	private String bxzzrq; // 保险终止日期
	private String jdczt; // 机动车状态
	private String jdclsh; // 机动车流水号
	private BigDecimal pl; // 排量
	private BigDecimal gl; // 功率
	private BigDecimal zzl; // 总质量
	private BigDecimal zbzl; // 整备质量
	private BigDecimal hdzzl; // 核定载质量
	private BigDecimal hdzk; // 核定载客
	private BigDecimal zqyzl; // 准牵引质量
	private String ccrq; // 出厂日期
	private String ylcs; // 原料厂商
	private String ylcsmc; // 原料厂商名称
	private String scdw; // 生产单位，初始化前确定生产单位
	private String scdwmc; // 生产单位名称
	private String cshsj; // 初始化时间
	private String dgdw; // 订购单位，出库后确定订购单位
	private String dgdwmc; // 订购单位名称
	private String sydw; // 使用单位，领用后确定使用单位
	private String sydwmc; // 使用单位名称
	private String dxqxh; // 读写器序号，写卡的读写器
	private String aqmkxlh; // 安全模块序列号
	private String gxhsj; // 个性化时间
	private String czyw; // 操作业务，1-初始化，2-申领，3-补领，4-变更，5-注销，6-重置，7-作废
	private String ydbj; // 异地标记，1-本地车，2-异地车
	private String xxly; // 信息来源：1-本地办理，2-异地办理转入
	private String zksj; // 装卡时间
	private String bfyy; // 报废原因
	private String bfyysm; // 报废原因说明
	private String bfsj; // 报废时间
	private String jdcywclbj; // 机动车业务处理标记，0-有更新未处理，1-已处理
	private String jdcywclxh; // 机动车业务处理序号
	private String zt; // 状态，0-作废，1-初始，2-已初始化，3-已入库，4-已出库，5-已签收，6-已领用，7-已个性化，8-已装卡
	private String jyw; // 校验位
	private String gxr; // 更新人
	private String gxrxm; // 更新人姓名
	private String gxsj; // 更新时间
	private String bz; // 备注
	// [特定属性]<!--本区域内开发用户可加入特定属性，特定属性可以被工具软件自动化生成时保留。-->
	public String hpzlmc;
	public String gcjkmc;
	public String cllxmc;
	public String syxzmc;
	public String csysmc;
	public String jdcztmc;
	public String czywmc; // 操作业务 名称
	public String ydbjmc; // 异地标记 名称
	public String sjlx; // 时间类型
	public String kssj; // 开始时间
	public String jssj; // 结束时间
	public String ztmc; // 状态 名称
	public String xxlymc; // 信息来源 名称
	public String fklxmc; // 发卡类型 名称
	public String ywlx;
	public String ywyy;
	public String jdcywclbjmc;
	public String xzqh;
	public String bfyymc; // 报废原因名称
	public String clsbdhmc;
	// [/特定属性]。
	private static final long serialVersionUID=1L;
	public static final String OBJECT_KEY="RFIDERI";
	public RfidEri(){
	}
	public RfidEri(String tid,String pch,String xh){
		super();
		this.tid=tid;
		this.pch=pch;
		this.xh=xh;
	}
	public String getTid(){
		return tid;
	}
	public void setTid(String tid){
		this.tid=tid;
	}
	public String getPch(){
		return pch;
	}
	public void setPch(String pch){
		this.pch=pch;
	}
	public String getXh(){
		return xh;
	}
	public void setXh(String xh){
		this.xh=xh;
	}
	public String getHh(){
		return hh;
	}
	public void setHh(String hh){
		this.hh=hh;
	}
	public String getKh(){
		return kh;
	}
	public void setKh(String kh){
		this.kh=kh;
	}
	public String getLsh(){
		return lsh;
	}
	public void setLsh(String lsh){
		this.lsh=lsh;
	}
	public String getFkbbh(){
		return fkbbh;
	}
	public void setFkbbh(String fkbbh){
		this.fkbbh=fkbbh;
	}
	public String getFklx(){
		return fklx;
	}
	public void setFklx(String fklx){
		this.fklx=fklx;
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
	public String getClpp(){
		return clpp;
	}
	public void setClpp(String clpp){
		this.clpp=clpp;
	}
	public String getGcjk(){
		return gcjk;
	}
	public void setGcjk(String gcjk){
		this.gcjk=gcjk;
	}
	public String getClsbdh(){
		return clsbdh;
	}
	public void setClsbdh(String clsbdh){
		this.clsbdh=clsbdh;
	}
	public String getFdjh(){
		return fdjh;
	}
	public void setFdjh(String fdjh){
		this.fdjh=fdjh;
	}
	public String getCllx(){
		return cllx;
	}
	public void setCllx(String cllx){
		this.cllx=cllx;
	}
	public String getSyxz(){
		return syxz;
	}
	public void setSyxz(String syxz){
		this.syxz=syxz;
	}
	public String getCsys(){
		return csys;
	}
	public void setCsys(String csys){
		this.csys=csys;
	}
	public String getJdcsyr(){
		return jdcsyr;
	}
	public void setJdcsyr(String jdcsyr){
		this.jdcsyr=jdcsyr;
	}
	public String getJdcsyrlxfs(){
		return jdcsyrlxfs;
	}
	public void setJdcsyrlxfs(String jdcsyrlxfs){
		this.jdcsyrlxfs=jdcsyrlxfs;
	}
	public String getCcdjrq(){
		return StringUtil.transBlank(ccdjrq);
	}
	public void setCcdjrq(String ccdjrq){
		this.ccdjrq=ccdjrq;
	}
	public String getYxqz(){
		return StringUtil.transBlank(yxqz);
	}
	public void setYxqz(String yxqz){
		this.yxqz=yxqz;
	}
	public String getQzbfqz(){
		return StringUtil.transBlank(qzbfqz);
	}
	public void setQzbfqz(String qzbfqz){
		this.qzbfqz=qzbfqz;
	}
	public String getFzjg(){
		return fzjg;
	}
	public void setFzjg(String fzjg){
		this.fzjg=fzjg;
	}
	public String getGlbm(){
		return glbm;
	}
	public void setGlbm(String glbm){
		this.glbm=glbm;
	}
	public String getGlbmmc(){
		return glbmmc;
	}
	public void setGlbmmc(String glbmmc){
		this.glbmmc=glbmmc;
	}
	public String getBxzzrq(){
		return StringUtil.transBlank(bxzzrq);
	}
	public void setBxzzrq(String bxzzrq){
		this.bxzzrq=bxzzrq;
	}
	public String getJdczt(){
		return jdczt;
	}
	public void setJdczt(String jdczt){
		this.jdczt=jdczt;
	}
	public String getJdclsh(){
		return jdclsh;
	}
	public void setJdclsh(String jdclsh){
		this.jdclsh=jdclsh;
	}
	public BigDecimal getPl(){
		return pl;
	}
	public void setPl(BigDecimal pl){
		this.pl=pl;
	}
	public BigDecimal getGl(){
		return gl;
	}
	public void setGl(BigDecimal gl){
		this.gl=gl;
	}
	public BigDecimal getZzl(){
		return zzl;
	}
	public void setZzl(BigDecimal zzl){
		this.zzl=zzl;
	}
	public BigDecimal getZbzl(){
		return zbzl;
	}
	public void setZbzl(BigDecimal zbzl){
		this.zbzl=zbzl;
	}
	public BigDecimal getHdzzl(){
		return hdzzl;
	}
	public void setHdzzl(BigDecimal hdzzl){
		this.hdzzl=hdzzl;
	}
	public BigDecimal getHdzk(){
		return hdzk;
	}
	public void setHdzk(BigDecimal hdzk){
		this.hdzk=hdzk;
	}
	public BigDecimal getZqyzl(){
		return zqyzl;
	}
	public void setZqyzl(BigDecimal zqyzl){
		this.zqyzl=zqyzl;
	}
	public String getCcrq(){
		return StringUtil.transBlank(ccrq);
	}
	public void setCcrq(String ccrq){
		this.ccrq=ccrq;
	}
	public String getYlcs(){
		return ylcs;
	}
	public void setYlcs(String ylcs){
		this.ylcs=ylcs;
	}
	public String getYlcsmc(){
		return ylcsmc;
	}
	public void setYlcsmc(String ylcsmc){
		this.ylcsmc=ylcsmc;
	}
	public String getScdw(){
		return scdw;
	}
	public void setScdw(String scdw){
		this.scdw=scdw;
	}
	public String getScdwmc(){
		return scdwmc;
	}
	public void setScdwmc(String scdwmc){
		this.scdwmc=scdwmc;
	}
	public String getCshsj(){
		return StringUtil.transBlank(cshsj);
	}
	public void setCshsj(String cshsj){
		this.cshsj=cshsj;
	}
	public String getDgdw(){
		return dgdw;
	}
	public void setDgdw(String dgdw){
		this.dgdw=dgdw;
	}
	public String getDgdwmc(){
		return dgdwmc;
	}
	public void setDgdwmc(String dgdwmc){
		this.dgdwmc=dgdwmc;
	}
	public String getSydw(){
		return sydw;
	}
	public void setSydw(String sydw){
		this.sydw=sydw;
	}
	public String getSydwmc(){
		return sydwmc;
	}
	public void setSydwmc(String sydwmc){
		this.sydwmc=sydwmc;
	}
	public String getDxqxh(){
		return dxqxh;
	}
	public void setDxqxh(String dxqxh){
		this.dxqxh=dxqxh;
	}
	public String getAqmkxlh(){
		return aqmkxlh;
	}
	public void setAqmkxlh(String aqmkxlh){
		this.aqmkxlh=aqmkxlh;
	}
	public String getGxhsj(){
		return StringUtil.transBlank(gxhsj);
	}
	public void setGxhsj(String gxhsj){
		this.gxhsj=gxhsj;
	}
	public String getCzyw(){
		return czyw;
	}
	public void setCzyw(String czyw){
		this.czyw=czyw;
	}
	public String getYdbj(){
		return ydbj;
	}
	public void setYdbj(String ydbj){
		this.ydbj=ydbj;
	}
	public String getXxly(){
		return xxly;
	}
	public void setXxly(String xxly){
		this.xxly=xxly;
	}
	public String getZksj(){
		return StringUtil.transBlank(zksj);
	}
	public void setZksj(String zksj){
		this.zksj=zksj;
	}
	public String getBfyy(){
		return bfyy;
	}
	public void setBfyy(String bfyy){
		this.bfyy=bfyy;
	}
	public String getBfyysm(){
		return bfyysm;
	}
	public void setBfyysm(String bfyysm){
		this.bfyysm=bfyysm;
	}
	public String getBfsj(){
		return StringUtil.transBlank(bfsj);
	}
	public void setBfsj(String bfsj){
		this.bfsj=bfsj;
	}
	public String getJdcywclbj(){
		return jdcywclbj;
	}
	public void setJdcywclbj(String jdcywclbj){
		this.jdcywclbj=jdcywclbj;
	}
	public String getJdcywclxh(){
		return jdcywclxh;
	}
	public void setJdcywclxh(String jdcywclxh){
		this.jdcywclxh=jdcywclxh;
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
	public String getGxsj(){
		return StringUtil.transBlank(gxsj);
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
	public String getKey(){
		return getXh();
	}
	public String getObjectKey(){
		return OBJECT_KEY;
	}
	public String getHpzlmc(){
		return hpzlmc;
	}
	public void setHpzlmc(String hpzlmc){
		this.hpzlmc=hpzlmc;
	}
	public String getGcjkmc(){
		return gcjkmc;
	}
	public void setGcjkmc(String gcjkmc){
		this.gcjkmc=gcjkmc;
	}
	public String getCllxmc(){
		return cllxmc;
	}
	public void setCllxmc(String cllxmc){
		this.cllxmc=cllxmc;
	}
	public String getSyxzmc(){
		return syxzmc;
	}
	public void setSyxzmc(String syxzmc){
		this.syxzmc=syxzmc;
	}
	public String getCsysmc(){
		return csysmc;
	}
	public void setCsysmc(String csysmc){
		this.csysmc=csysmc;
	}
	public String getJdcztmc(){
		return jdcztmc;
	}
	public void setJdcztmc(String jdcztmc){
		this.jdcztmc=jdcztmc;
	}
	public String getCzywmc(){
		return czywmc;
	}
	public void setCzywmc(String czywmc){
		this.czywmc=czywmc;
	}
	public String getYdbjmc(){
		return ydbjmc;
	}
	public void setYdbjmc(String ydbjmc){
		this.ydbjmc=ydbjmc;
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
	public String getZtmc(){
		return ztmc;
	}
	public void setZtmc(String ztmc){
		this.ztmc=ztmc;
	}
	public String getXxlymc(){
		return xxlymc;
	}
	public void setXxlymc(String xxlymc){
		this.xxlymc=xxlymc;
	}
	public String getFklxmc(){
		return fklxmc;
	}
	public void setFklxmc(String fklxmc){
		this.fklxmc=fklxmc;
	}
	public String getYwlx(){
		return ywlx;
	}
	public void setYwlx(String ywlx){
		this.ywlx=ywlx;
	}
	public String getYwyy(){
		return ywyy;
	}
	public void setYwyy(String ywyy){
		this.ywyy=ywyy;
	}
	public String getJdcywclbjmc(){
		return jdcywclbjmc;
	}
	public void setJdcywclbjmc(String jdcywclbjmc){
		this.jdcywclbjmc=jdcywclbjmc;
	}
	public String getXzqh(){
		return xzqh;
	}
	public void setXzqh(String xzqh){
		this.xzqh=xzqh;
	}
	public String getBfyymc(){
		return bfyymc;
	}
	public void setBfyymc(String bfyymc){
		this.bfyymc=bfyymc;
	}
	public String getClsbdhmc(){
		return clsbdhmc;
	}
	public void setClsbdhmc(String clsbdhmc){
		this.clsbdhmc=clsbdhmc;
	}
}