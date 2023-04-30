package com.techtest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * レスポンス親クラス
 * @author fukuzawa
 *
 */
public abstract class BaseResponse {

	//レスポンス作成メソッド
	public abstract ResponseEntity<Object> makeResponse(HttpStatus status);
}
