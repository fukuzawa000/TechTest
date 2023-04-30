package com.techtest.constants;

public enum MessageId {

	/*
	 * 	MSG01	認証関連
	 * 	MSG02	気に入れ関連
	 * 	MSG03	映画関連
	 * 
	 * 	MSG09	システム関連
	 * */
	
	//M01	認証関連
    MSG010001("MSG010001","ユーザIDかパスワードが間違っている"),
    MSG010002("MSG010002","Tokenの利用期限が切れたので、再取得してください。"),
    MSG010003("MSG010003","Tokenが間違っている"),
    MSG010004("MSG010004","未認証なので、トークンを取得してください"),
    MSG010005("MSG010005","正常にログアウトしました。"),
    
	//M02	気に入れ関連
	MSG020001("MSG020001","気に入れ登録処理が正常に実行しました"),
	MSG020002("MSG020002","気に入れ削除処理が正常に実行しました"),
	MSG020003("MSG020003","気に入れに既に存在している"),
    
	//MSG03	映画関連
	MSG030001("MSG030001","該当映画が存在しません"),
	
	//M09	システム関連
    
    //未使用
	MSG999999("MSG999999","");
	
    private final String messageId;
    private final String message;
    
    MessageId(String msgId, String msg){
        this.messageId = msgId;
        this.message = msg;
    }

    public String getMessageId() {
        return messageId;
    }
    
    public String getMessage() {
        return message;
    }
}
