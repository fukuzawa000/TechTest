package com.techtest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techtest.entiy.LoginToken;
import com.techtest.entiy.UserInfo;
import com.techtest.mapper.AuthMapper;

/**
 * 
 * 認証サービス
 * @author fukuzawa
 *
 */
@Transactional
@Service
public class AuthService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    AuthMapper authMapper;
	
	/**
	 * 
	 * ユーザー認証
	 * 
	 * @param authInfo
	 * @return UserInfo
	 */
	public UserInfo authByUser(UserInfo authInfo){
		
		UserInfo userInfo = null;
		try {
			userInfo = authMapper.authByUser(authInfo);
		} catch (Exception e) {
			logger.error("ユーザ認証時、エラが発生しました。");
		}
        return userInfo;
	}

	/**
	 * 
	 * トークン登録
	 * 
	 * @param loginToken
	 * @return なし
	 */
	public void insertToken(LoginToken loginToken){
		
		try {
			LoginToken chkLoginToken = authMapper.loginTokenCheck(loginToken.getUserId());
			
			if (chkLoginToken == null) {
				authMapper.insertToken(loginToken);
			} else {
				authMapper.updateToken(loginToken);
			}
		} catch (Exception e) {
			logger.error("トークン登録・更新時、エラが発生しました。");
		}
	}

	/**
	 * 
	 * ログアウト処理
	 * 
	 * @param userid
	 * @return なし
	 */
	public void logout(String userid){
		
		try {
			authMapper.logout(userid);
		} catch (Exception e) {
			logger.error("ログアウト時、エラが発生しました。");
		}
	}
	
	/**
	 * 
	 * 既にログイン済みユーザのトークン取得
	 * 
	 * @param userid
	 * @return LoginToken
	 */
	public LoginToken getLoginToken(String userId){
		
		LoginToken loginToken = null;
		try {
			loginToken = authMapper.loginTokenCheck(userId);
		} catch (Exception e) {
			logger.error("ログイントークン取得時、エラが発生しました。");
		}
		
		return loginToken;
	}
	
	/**
	 * 
	 * 期限切れトークンの削除
	 * 
	 * @param なし
	 * @return なし
	 */
	public void deleteExpiredToken(){
		
		try {
			authMapper.deleteExpiredToken();
		} catch (Exception e) {
			logger.error("期限切れトークンの削除時、エラが発生しました。");
		}
		
	}
}
