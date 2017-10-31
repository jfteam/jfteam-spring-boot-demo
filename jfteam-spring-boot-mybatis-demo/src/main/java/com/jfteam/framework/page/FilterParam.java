package com.jfteam.framework.page;

import java.io.Serializable;

public class FilterParam implements Serializable {

	private static final long serialVersionUID = 6014940847937060639L;

	/**
	 * 过滤器名称
	 */
	private String fn;

	/**
	 * 过滤器类型
	 */
	private String ft;

	/**
	 * 过滤器值
	 */
	private Object fv;

	/**
	 * 待过滤条件关系
	 */
	private String fr;

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public Object getFv() {
		return fv;
	}

	public void setFv(Object fv) {
		this.fv = fv;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}
	
}
