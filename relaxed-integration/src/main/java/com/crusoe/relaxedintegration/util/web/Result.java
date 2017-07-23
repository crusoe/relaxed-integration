package com.crusoe.relaxedintegration.util.web;


public class Result {
	public static String FAIL = "fail";
	public static String FAIL_PARAM = "failParam";
	public static String SUCCESS = "success";
	private String status;
	private Integer code;
	private String msg;
	private Object result;
	
	/**成功的状态码*/
	public static final int CODE_SUCCESS = 1;
	/**失败的状态码*/
	public static final int CODE_FAIL = -1;
	/**参数传递错误的状态码*/
	public static final int CODE_FAIL_PAPAM = 0;
	
	public Result() {
		this.status=SUCCESS;
	}
	
	public String getStatus() {
		if(null==this.status){
			this.status=SUCCESS;
		}
		return status;
	}
	 
	public Result fail(){
		this.status = FAIL;
		return this;
	}
	
	public Result failParam(){
		this.status = FAIL_PARAM;
		return this;
	}
	
	public Result success(){
		this.status = SUCCESS;
		return this;
	}
	
	public Integer getCode() {
		if(null==this.code){
			if (SUCCESS.equals(this.getStatus())) {
				 this.code = CODE_SUCCESS;
			} else if (FAIL_PARAM.equals(this.getStatus())){
				this.code = CODE_FAIL_PAPAM;
			}else{
				this.code = CODE_FAIL;
			}
		}
		return code;
	}

	public Result setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		if(null==this.msg){
			if (SUCCESS.equals(this.getStatus())) {
				this.msg = "操作成功";
			} else if (FAIL_PARAM.equals(this.getStatus())){
				this.msg = "参数错误";
			}else{
				this.msg = "操作失败";
			}
		}
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getResult() {
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T geeResult() {
		return (T) result;
	}

	public Result setResult(Object result) {
		this.result = result;
		return this;
	}
	
	public boolean isSuccess(){
		if(null!=this.status&&this.status.equals(SUCCESS)){
			return true;
		}
		return false;
	}

	public Result setStatusError(){
		this.status = FAIL;
		return this;
	}
	
	public Result setStatusErrorParam(){
		this.status = FAIL_PARAM;
		return this;
	}
	public Result setStatusSuccess(){
		this.status = SUCCESS;
		return this;
	}

}
