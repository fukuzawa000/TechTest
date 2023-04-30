package com.techtest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techtest.constants.MessageId;

import lombok.Data;

/**
 * 
 * HttpCodeが200のレスポンス
 * @author fukuzawa
 *
 */
@Data
public class SuccessResponse extends BaseResponse {

	//メッセージID
	private String msgId;
	
	//メッセージ内容
	private String msg;
	
	//コンストラクタ
	public SuccessResponse(MessageId m){
		this.msgId = m.getMessageId();
		this.msg = m.getMessage();
	}
	
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
