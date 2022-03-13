package com.kuang.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Schema(title = "全局统一返回结果")
public class BaseResponse<T> {

	@Schema(title = "是否成功")
	private Boolean success;

	@Schema(title = "返回码")
	private Integer code;

	@Schema(title = "返回消息")
	private String message;

	@Schema(title = "返回数据")
	private T data  ;


	public static <T> BaseResponse<T> ok(T data){


		return new BaseResponse<>(ResultCodeEnum.SUCCESS.getSuccess(), ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
	}

	public static <T> BaseResponse<T> error(){

		return new BaseResponse<>(ResultCodeEnum.UNKNOWN_REASON.getSuccess(),  ResultCodeEnum.UNKNOWN_REASON.getCode(),ResultCodeEnum.UNKNOWN_REASON.getMessage(), null);
	}

	public static <T> BaseResponse<T> setResult(ResultCodeEnum resultCodeEnum){

		return new BaseResponse<>(resultCodeEnum.getSuccess(), resultCodeEnum.getCode(), resultCodeEnum.getMessage(),null);
	}


}