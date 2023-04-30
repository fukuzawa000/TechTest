package com.techtest.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.constants.MessageId;
import com.techtest.entiy.LoginToken;
import com.techtest.entiy.UserInfo;
import com.techtest.response.AuthSuccessResponse;
import com.techtest.response.FailureResponse;
import com.techtest.response.SuccessResponse;
import com.techtest.service.AuthService;
import com.techtest.utils.Token;

/**
 * 
 * 認証コントローラー
 * @author fukuzawa
 *
 */
@RestController
@CrossOrigin
public class AuthController {

	/*　認証用サービス*/
	@Autowired
	AuthService authService;
	
	/*　トークンクラス*/
	@Autowired
	Token token;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * 認証API
	 * 
	 * @param authInfo
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/auth")
	public ResponseEntity<Object> auth(@RequestParam Map<String,String> authInfo){
		
		logger.info("ユーザー認証：開始");
		
		ResponseEntity<Object> retAuthResponse = null;
		
		//ユーザ認証
		if (authInfo.get("userid") == null || authInfo.get("password") == null) {
			//認証情報不足のため、認証失敗レスポンス作成
			retAuthResponse =  new FailureResponse(MessageId.MSG010001).makeResponse(HttpStatus.NOT_FOUND);
		} else {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserid(authInfo.get("userid"));
			userInfo.setPassword(authInfo.get("password"));
			
			//ユーザーパスワードチェック
			retAuthResponse = authByUser(userInfo);
		}

		logger.info("ユーザー認証：終了");
		
		return retAuthResponse;
	}

	/**
	 * 
	 * ユーザー認証
	 * 
	 * @param authInfo
	 * @return ResponseEntity<Object>
	 */
	private ResponseEntity<Object> authByUser(UserInfo userInfo) {
		
		//ユーザー名とパスワードのチェック
		UserInfo resUserInfo = authService.authByUser(userInfo);
		
		if (resUserInfo != null) {
			//OKの場合、トークン発行
			String accessToken = token.creatNewToken(userInfo.getUserid());
			logger.info("ユーザー認証結果：accessToken=" + accessToken);
			
			//トークン登録
			LoginToken loginToken = new LoginToken();
			loginToken.setUserId(userInfo.getUserid());
			loginToken.setToken(accessToken);
			authService.insertToken(loginToken);
			
			//成功レスポンスを作成
			return new AuthSuccessResponse(resUserInfo.getUserid(),resUserInfo.getName(),accessToken).makeResponse(HttpStatus.OK);
			
		} else {
			//NGの場合、失敗レスポンスを作成
			return new FailureResponse(MessageId.MSG010001).makeResponse(HttpStatus.NOT_FOUND);
		}
		
	}

	/**
	 * 
	 * ログアウトAPI
	 * 
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@DeleteMapping("/logout")
	public ResponseEntity<Object> logout(@RequestHeader("userid") String userid){
		
		logger.info("ユーザーログアウト：開始");
		
		//ログアウトでトークン削除
		authService.logout(userid);
		
		logger.info("ユーザーログアウト：終了");
		return new SuccessResponse(MessageId.MSG010005).makeResponse(HttpStatus.OK);
	}
}
