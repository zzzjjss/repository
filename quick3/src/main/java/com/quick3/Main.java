package com.quick3;

public class Main {
	public static void main(String[] args) {
		Thread autoSyn=new Thread(new AutoSynch(),"auto synch");
		autoSyn.start();
		
	}
}
