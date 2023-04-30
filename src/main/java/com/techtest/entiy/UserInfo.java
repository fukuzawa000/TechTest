package com.techtest.entiy;

import lombok.Data;

/**
 * 
 * ユーザ情報エンティティ
 * @author fukuzawa
 *
 */
@Data
public class UserInfo {

	//ユーザID
	private String userid;
	
	//パスワード
	private String password;
	
	//ユーザ名
	private String name;
}
