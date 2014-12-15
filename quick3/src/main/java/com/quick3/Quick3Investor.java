package com.quick3;

public class Quick3Investor {
	public static  int sum;
	public static void main(String[] args) {
		preLoss(10,40,12);
		System.out.println(sum);
	}
	
	
	public static  int  preLoss(int baseWin,int  step,int resultMoney){
		int preLoss=0;
		if(step==1){
			preLoss=0;
		}else{
			preLoss=preLoss(baseWin,step-1,resultMoney);
		}
		int x=resultMoney-2;
		double times=Math.ceil((double)(preLoss+baseWin)/(double)x);
		 System.out.println("step "+step+" ----->"+times+" times ->invest:"+2*(int)times+"--->win:"+(resultMoney*times-preLoss-2*times));
		 sum=sum+(2*(int)times);
		 return (preLoss+2*(int)times);
	}	
}
