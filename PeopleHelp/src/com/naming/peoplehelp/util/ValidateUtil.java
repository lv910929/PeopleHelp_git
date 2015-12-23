package com.naming.peoplehelp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	public static boolean validateMac(String macAddress) {

		boolean result = false;
		String regex = "[A-F0-9]{16}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(macAddress);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}

	public static boolean validateSNMacAddress(String macAddress) {

		boolean result = false;
		String regex = "^[0-9A-F]{12}&\\d{12}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(macAddress);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}

	public static boolean validateIP(String ipAddress) {

		boolean result = false;
		String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ipAddress);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}

	public static boolean validatePhone(String phone) {

		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(phone);
		b = m.matches();
		return b;
	}
	
	public static boolean validatePassword(String string){
		
		boolean result = false;
		String regex = "[0-9a-zA-Z]{5,12}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}
	
	public static boolean validateSmsCode(String smsCode){
		
		boolean result = false;
		String regex = "[0-9]{6}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(smsCode);
		if (matcher.matches()) {
			result = true;
		}
		return result;
	}
}
