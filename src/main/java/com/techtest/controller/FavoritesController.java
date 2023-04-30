package com.techtest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.constants.MessageId;
import com.techtest.entiy.Favorites;
import com.techtest.entiy.MovieInfo;
import com.techtest.entiy.SearchKeyword;
import com.techtest.response.FailureResponse;
import com.techtest.response.MovieListSuccessResponse;
import com.techtest.response.SuccessResponse;
import com.techtest.service.FavoritesService;
import com.techtest.service.MoviesService;

/**
 * 
 * 気に入れコントローラー
 * @author fukuzawa
 *
 */
@RestController
@CrossOrigin
public class FavoritesController{

	/*　気に入れサービス*/
	@Autowired
	FavoritesService favoritesService;
	
	/*　映画サービス*/
	@Autowired
	MoviesService moviceService;

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * お気に入り映画一覧検索
	 * 
	 * @param userid
	 * @return ResponseEntity<Object>
	 */
	@GetMapping("/favorites")
	public ResponseEntity<Object> getFavoritesByUserid(@RequestHeader("userid") String userid){
		
		logger.info("気に入れ一覧検索：開始");
		
		//気に入れ映画取得
		List<MovieInfo> favoritesMovies =  favoritesService.getFavoritesByUserid(userid);

		logger.info("気に入れ一覧検索：終了");
		
		return new MovieListSuccessResponse(favoritesMovies).makeResponse(HttpStatus.OK);
	}
	
	/**
	 * 
	 * 気に入れ映画登録
	 * 
	 * @param userid
	 * @param id
	 * 
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/favorites/{id}")
	public ResponseEntity<Object> insertFavorites(@RequestHeader("userid") String userid, @PathVariable("id") String id){
		
		logger.info("気に入れ登録：開始");

		SearchKeyword searchKeyword = new SearchKeyword();
		searchKeyword.setId(id);
		//映画存在チェック
		List<MovieInfo> movieList = moviceService.getMovies(searchKeyword);
		if (movieList.size() == 0) {
			return new FailureResponse(MessageId.MSG030001).makeResponse(HttpStatus.NOT_FOUND);
		}
		
		//パラメータ設定
		Favorites favorites = new Favorites();
		favorites.setUserid(userid);
		favorites.setMovieid(id);
		
		//気に入れ既に存在するかチェック
		int favoritesCnt = favoritesService.selectFavorites(favorites);
		if (favoritesCnt == 0) {
			//気に入れ登録
			favoritesService.insertFavorites(favorites);
			logger.info("気に入れ登録：終了");
			return new SuccessResponse(MessageId.MSG020001).makeResponse(HttpStatus.OK);
		}else {
			return new FailureResponse(MessageId.MSG020003).makeResponse(HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * 
	 * 気に入れ映画削除
	 * 
	 * @param userid
	 * @param id
	 * 
	 * @return ResponseEntity<Object>
	 */
	@DeleteMapping("/favorites/{id}")
	public ResponseEntity<Object> deleteFavorites(@RequestHeader("userid") String userid, @PathVariable("id") String id){
		
		logger.info("気に入れから削除：開始");
		
		//パラメータ設定
		Favorites favorites = new Favorites();
		favorites.setUserid(userid);
		favorites.setMovieid(id);
		
		//気に入れ削除
		favoritesService.deleteFavorites(favorites);
		
		logger.info("気に入れから削除：終了");
		
		return new SuccessResponse(MessageId.MSG020002).makeResponse(HttpStatus.OK);

	}
}
