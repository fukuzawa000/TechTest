package com.techtest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.techtest.entiy.LoginToken;
import com.techtest.entiy.UserInfo;
 
/**
 * 
 * 認証マッパーインタフェース
 * @author fukuzawa
 *
 */
@Mapper
public interface AuthMapper {

	/**
	 * 
	 * 認証API
	 * 
	 * @param authInfo
	 * @return ResponseEntity<Object>
	 */
	public UserInfo authByUser(UserInfo user);
	
	/**
	 * 
	 * トークン登録
	 * 
	 * @param loginToken
	 * @return なし
	 */
	public void insertToken(LoginToken loginToken);
	
	/**
	 * 
	 * トークン更新
	 * 
	 * @param loginToken
	 * @return なし
	 */
	public void updateToken(LoginToken loginToken);
	
	/**
	 * 
	 * ログアウト処理
	 * 
	 * @param userid
	 * @return なし
	 */
	public void logout(String userid);
	
	/**
	 * 
	 * 既にログイン済みユーザのトークン取得
	 * 
	 * @param userid
	 * @return LoginToken
	 */
	public LoginToken loginTokenCheck(String userid);
	
	/**
	 * 
	 * 期限切れトークンの削除
	 * 
	 * @param なし
	 * @return なし
	 */
	public void deleteExpiredToken();
	
}
