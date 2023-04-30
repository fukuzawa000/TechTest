package com.techtest.entiy;

import lombok.Data;

/**
 * 
 * 映画情報エンティティ
 * @author fukuzawa
 *
 */
@Data
public class MovieInfo {

	//映画ID
	private String Id;
	
	//映画名
	private String Name;
	
	//映画概要
	private String Overview;
	
	//映画上映開始時間
	private String ShowBeginDate;
	
	//映画上映終了時間
	private String ShowEndDate;
}
