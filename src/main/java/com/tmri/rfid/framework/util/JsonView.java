package com.tmri.rfid.framework.util;

import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

/**
 * Created by IntelliJ IDEA.
 * User: xuwenbin
 * Date: 2010-7-22
 * Time: 12:22:12
 * Json的视图
 */
public class JsonView implements View {
	public static final JsonView instance = new JsonView();
	public static final String JSON_ROOT = "root";
  public String getContentType() {
		return "text/html;charset=UTF-8";
	}

  private JsonView() {}

//	@SuppressWarnings("unchecked")
	public void render(Map model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Object jsonStr = model.get(JSON_ROOT);
		if (jsonStr == null) {
			return;
		}
		PrintWriter writer = response.getWriter();
		writer.write(jsonStr.toString());
	}
}
