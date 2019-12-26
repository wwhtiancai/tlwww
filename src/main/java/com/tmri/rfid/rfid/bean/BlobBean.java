package com.tmri.rfid.rfid.bean;
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
public class BlobBean {
	private String table;//表名
	private String item;//字段名
	private String zjSql;//主键sql条件

	private byte[] wd; // 文档

	public byte[] getWd() {
		return wd;
	}

	public void setWd(byte[] wd) {
		this.wd = wd;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getZjSql() {
		return zjSql;
	}

	public void setZjSql(String zjSql) {
		this.zjSql = zjSql;
	}
}