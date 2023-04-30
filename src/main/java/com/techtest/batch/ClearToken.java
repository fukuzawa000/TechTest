package com.techtest.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techtest.service.AuthService;

/**
 * 
 * 期限切れトークン削除
 * @author fukuzawa
 *
 */
@Service
public class ClearToken {

    /**
     * ログ定義
     */
    protected final static Logger logger = LoggerFactory.getLogger(ClearToken.class);

	/*　認証用サービス*/
	@Autowired
	AuthService authService;
	
    /**
     * 期限切れトークンの削除
     * @param 無し
     * @return 無し
     */
    @Scheduled(cron = "0 * * * * ?")
    public void deleteExpiredToken() throws Exception {
    	
    	logger.info("トークン削除処理：開始");
    	
    	try {
    		authService.deleteExpiredToken();
    	} catch (Exception e) {
    		logger.error("トークン削除時、エラが発生しました。");
    		throw e;
    	}
    	
    	logger.info("トークン削除処理：終了");
    }

}
