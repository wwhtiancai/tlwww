package com.tmri.rfid.framework.util;

import java.util.List;
import org.springframework.http.HttpRequest;
import com.github.pagehelper.PageInfo;

public class PageController{
	public <T> PageController(HttpRequest request,List<T> list) {
		PageInfo<T> pi = new PageInfo<T>(list);
		
	}
}
