package com.techtest.entiy;

import lombok.Data;

/**
 * 
 * ログイン済みトークンエンティティ
 * @author fukuzawa
 *
 */
@Data
public class LoginToken {

	//ユーザID
	private String userId;
	
	//トークン
	private String token;
}
