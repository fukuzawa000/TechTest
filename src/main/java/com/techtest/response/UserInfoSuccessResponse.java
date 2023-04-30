package com.techtest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techtest.entiy.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * ユーザ情報レスポンス
 * @author fukuzawa
 *
 */
@Data
@AllArgsConstructor
public class UserInfoSuccessResponse extends BaseResponse {

	//ユーザ情報
	private UserInfo userInfo;
	
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
