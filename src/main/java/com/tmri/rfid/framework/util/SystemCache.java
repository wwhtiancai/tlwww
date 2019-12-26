package com.tmri.rfid.framework.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


public class SystemCache {
	
	private static SystemCache applicationContext = null;
	private static Hashtable tab;
	private static Hashtable _cachServiceList;
	
	
	private SystemCache() {
		tab = new Hashtable();
		_cachServiceList = new Hashtable();
	}

	public static SystemCache getInstance() {
		if (applicationContext == null) {
			applicationContext = new SystemCache();
		}
		return applicationContext;
	}
	/**
	 * 需要全部重新加载
	 */
	public void refresh() {
		tab.clear();
	}

	public void remove(Object key) {
		tab.remove(key);
	}

	public Object getValue(Object key) {
		return tab.get(key);
	}

	public boolean repreg(Object key, Object value) {
		tab.put(key, value);
		return true;
	}

	public boolean reg(Object key, Object value) {
		if (tab.containsKey(key)) {
			return false;
		} else {
			if (value != null) {
				tab.put(key, value);
			}
			return true;
		}

	}
	
	public void putCachServiceList(String key,String name){
		_cachServiceList.put(key, name);
	}
	
	public boolean contains(Object key) {
		return tab.containsKey(key);
	}

	public String dumpContext() {
		StringBuffer buffer = new StringBuffer();

		for (Enumeration e = tab.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			buffer.append(key);
			buffer.append("\n");
			Map map = (Map) tab.get(key);
			Set set = map.keySet();
			for (Iterator i = set.iterator(); i.hasNext();) {
				Object dmz = i.next();
				Object object = map.get(dmz);
				buffer.append(dmz);
				buffer.append(object);
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	// 刷新缓存变量名称列表
	public List<CachType> getCachServiceList() {
		List<CachType> resultList = new Vector<CachType>();
		for (Iterator<String> itr = _cachServiceList.keySet().iterator(); itr.hasNext();) {
			String key = (String) itr.next();
			String name = (String)_cachServiceList.get(key);
			CachType type = new CachType();
			type.setKey(key);
			type.setName(name);
			resultList.add(type);
		}
		return resultList;
	}
}
