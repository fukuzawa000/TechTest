package com.techtest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.constants.MessageId;
import com.techtest.entiy.MovieInfo;
import com.techtest.entiy.SearchKeyword;
import com.techtest.response.FailureResponse;
import com.techtest.response.MovieListSuccessResponse;
import com.techtest.service.MoviesService;

/**
 * 
 * 映画コントローラー
 * @author fukuzawa
 *
 */
@RestController
@CrossOrigin
public class MoviesController{

	/*　映画サービス*/
	@Autowired
	MoviesService moviceService;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 
	 * 映画一覧の取得
	 * 
	 * @param searchKeyword
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/movies")
	public ResponseEntity<Object> getMovieList(@RequestParam(name = "search", required = false ) String name){
		
		logger.info("映画検索：開始");

		//パラメータ設定
		SearchKeyword searchKeyword = new SearchKeyword();
		searchKeyword.setName(name);
		//映画検索
		List<MovieInfo> movieList = moviceService.getMovies(searchKeyword);

		logger.info("映画検索：終了");

		return new MovieListSuccessResponse(movieList).makeResponse(HttpStatus.OK);
	}
		
	/**
	 * 
	 * 映画情報の取得
	 * 
	 * @param id
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/movies/{id}")
	public ResponseEntity<Object> getMovieInfo(@PathVariable("id") String id){
		
		logger.info("映画情報取得：開始");
		ResponseEntity<Object> retMoviesResponse = null;
		
		//パラメータ設定
		SearchKeyword searchKeyword = new SearchKeyword();
		searchKeyword.setId(id);
		//映画検索
		List<MovieInfo> movieList = moviceService.getMovies(searchKeyword);

		//検索結果の判定
		if (movieList.size() != 0 ) {
			retMoviesResponse = new MovieListSuccessResponse(movieList).makeResponse(HttpStatus.OK);
		} else {
			retMoviesResponse = new FailureResponse(MessageId.MSG030001).makeResponse(HttpStatus.NOT_FOUND);
		}
		
		logger.info("映画情報取得：終了");
		return retMoviesResponse;
	}
}
