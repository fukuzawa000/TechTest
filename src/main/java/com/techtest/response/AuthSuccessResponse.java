package com.techtest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 認証用レスポンス
 * @author fukuzawa
 *
 */
@Data
@AllArgsConstructor 
public class AuthSuccessResponse extends BaseResponse {

	//ユーザID
	private String userId;
	//ユーザ名
	private String name;
	//発行済みトークン
	private String accessToken;
	
	/**
	 * 
	 * レスポンス作成
	 * 
	 * @param HttpStatus
	 * @return ResponseEntity<Object>
	 */
	@Override
	public ResponseEntity<Object> makeResponse(HttpStatus status) {

		return ResponseEntity.status(status).body(this);
	}
}
