package com.ambow.util;

public class Parameter {
	public Object paramValue ; //存储?的值得
	public int direction ; //输出 ， 输入
	
	public static final int IN = 1;
	public static final int OUT = 2;
	public static final int INOUT = 3;
	
	
	public Parameter(Object paramValue, int direction) {
		super();
		this.paramValue = paramValue;
		this.direction = direction;
	}

	public Parameter() {
		super();
	}
}