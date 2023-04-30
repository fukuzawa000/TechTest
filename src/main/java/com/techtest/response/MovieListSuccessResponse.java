package com.techtest.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techtest.entiy.MovieInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 映画検索レスポンス
 * @author fukuzawa
 *
 */
@Data
@AllArgsConstructor
public class MovieListSuccessResponse extends BaseResponse {

	//映画一覧
	private List<MovieInfo> movies;
	
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
