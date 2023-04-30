package com.techtest.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.techtest.constants.MessageId;
import com.techtest.entiy.LoginToken;
import com.techtest.response.FailureResponse;
import com.techtest.service.AuthService;

@Component
public class ServiceInterceptor implements HandlerInterceptor{

	/*　認証用サービス*/
	@Autowired
	AuthService authService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		//特定なリクエストを許可する
		if ("OPTIONS".equals(request.getMethod())){
			return true;
		}

		//リクエストのユーザIDとTokenを取得
		String userId = Optional.ofNullable(request.getHeader("UserId")).orElse("");
		String token = Optional.ofNullable(request.getHeader("Token")).orElse("");

		if ("".equals(userId) || "".equals(token)) {
			//未認証
			returnResponse(response, MessageId.MSG010004);
			return false;
		}

		//保存しているトークンの取得
		LoginToken loginToken = authService.getLoginToken(userId);
		if (loginToken == null) {
			returnResponse(response, MessageId.MSG010004);
			return false;
		}
		
		String savedTokenString = loginToken.getToken();

		if (Objects.isNull(savedTokenString)) {
			//Token利用期限が切れた
			returnResponse(response, MessageId.MSG010002);
			return false;
		} else {
			if (!savedTokenString.equals(token)) {
				//Tokenが間違っている
				returnResponse(response, MessageId.MSG010003);
				return false;
			}
		}
		
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }
	
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,  Object o, Exception e) throws Exception {
    	
    }
    
    @SuppressWarnings("static-access")
	private void returnResponse(HttpServletResponse response, MessageId msgId) {
		Gson gson = new Gson();
		FailureResponse failureResponse = new FailureResponse(msgId);
		
		response.setStatus(response.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=utf-8");
		try (PrintWriter writer = response.getWriter()){
			writer.print(gson.toJson(failureResponse));
			response.flushBuffer();
		} catch (IOException e) {
	    }
	}
    
}
