package com.quick3;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public final static Map<Integer, Integer> RESULT_WIN=new HashMap<Integer, Integer>();
	static{
		RESULT_WIN.put(9, 5);
		RESULT_WIN.put(10, 5);
		RESULT_WIN.put(11, 5);
		RESULT_WIN.put(12, 5);
		RESULT_WIN.put(8, 6);
		RESULT_WIN.put(13, 6);
		RESULT_WIN.put(7, 9);
		RESULT_WIN.put(14, 9);
		RESULT_WIN.put(6, 13);
		RESULT_WIN.put(15, 13);
		RESULT_WIN.put(5, 20);
		RESULT_WIN.put(16, 20);
		RESULT_WIN.put(4, 40);
		RESULT_WIN.put(17, 40);
		RESULT_WIN.put(3, 120);
		RESULT_WIN.put(18, 120);
	}
}
