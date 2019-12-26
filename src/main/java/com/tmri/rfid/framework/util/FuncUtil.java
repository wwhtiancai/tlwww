package com.tmri.rfid.framework.util;

import javax.servlet.http.HttpServletRequest;

public class FuncUtil {
	public static String getRemoteIpdz(HttpServletRequest request) {
		String ipString = "";
		ipString = request.getHeader("X-Forwarded-For");
		if (ipString == null || ipString.length() == 0 || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getRemoteAddr();
		}
		return ipString;
	}

}