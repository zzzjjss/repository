package com.uf.fanfan.util;
/**
 * 业务逻辑上的一些成功失败信息，可以通过该类返回
 * @author Jason
 *
 */
public class OperationResult {
	public static enum Result{
		SUCCESS,FAIL
	}
	private Result result;
	private String message;
	public OperationResult(Result result,String mess){
		this.result=result;
		this.message=mess;
	}
	public OperationResult(){
		
	}
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
