package com.quick3.theory;
import java.util.HashMap;
import java.util.Map;


public class TestQuick3 {
	public static void main(String[] args) {
		Map<String,Integer>  allCount=new HashMap<String, Integer>();
		for(int i=1;i<=6;i++){
			for(int j=1;j<=6;j++){
				for(int k=1;k<=6;k++){
					String sum=String.valueOf(i+j+k);
					Integer count=allCount.get(sum);
					if(count==null){
						allCount.put(sum, 1);
					}else{
						allCount.put(sum, count+1);
					}
				}
			}
		}
		allCount.put("32", 1);
		allCount.put("33", 1);
		allCount.put("34", 1);
		allCount.put("35", 1);
		
		int  all=6*6*6;
		
		for(String key:allCount.keySet()){
			Integer value=allCount.get(key);
			if(key.equals("6")||key.equals("9")||key.equals("12")||key.equals("15")){
				value=value-1;	
			}
			float percent=((float)value)/(float)all;
			float  money=0;
			if(key.equals("4")||key.equals("17")){
				money=percent*80;
			}else if(key.equals("5")||key.equals("16")){
				money=percent*40;
			}else if(key.equals("6")||key.equals("15")){
				money=percent*25;
			}else if(key.equals("7")||key.equals("14")){
				money=percent*16;
			}else if(key.equals("8")||key.equals("13")){
				money=percent*12;
			}else if(key.equals("9")||key.equals("12")){
				money=percent*10;
			}else if(key.equals("10")||key.equals("11")){
				money=percent*9;
			}else {
				money=percent*240;
			}
			System.out.println(key+"----->"+(percent*100)+"%--------->"+money);
		}
		
	}
}
