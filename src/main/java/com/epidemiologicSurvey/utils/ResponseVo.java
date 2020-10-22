package com.epidemiologicSurvey.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseVo extends JSONObject{
	
	private static final long serialVersionUID = 1L;
	
	public static enum Code {
		Ok(0),Error(500);
		private int value;

		private Code(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	public ResponseVo() {
		put("code", 0);
	}
	
	public static ResponseVo error() {
		return error(500, "未知异常,请联系管理员");
	}
	
	public static ResponseVo error(String msg) {
		return error(500, msg);
	}
	

	public static ResponseVo error(int code, String msg) {
		ResponseVo r = new ResponseVo();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static ResponseVo error(int code, JSONObject rst) {
		ResponseVo r = new ResponseVo();
		r.put("code", code);
		r.putAll(rst);
		return r;
	}

	public static ResponseVo ok(String msg) {
		ResponseVo r = new ResponseVo();
		r.put("msg", msg);
		return r;
	}
	
	public static ResponseVo ok(JSONObject rst) {
		ResponseVo r = new ResponseVo();
		r.putAll(rst);
		return r;
	}
	
	public static ResponseVo ok() {
		return new ResponseVo();
	}

	public ResponseVo put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
