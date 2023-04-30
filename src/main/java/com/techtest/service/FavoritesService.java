package com.techtest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techtest.entiy.Favorites;
import com.techtest.entiy.MovieInfo;
import com.techtest.mapper.FavoritesMapper;

/**
 * 
 * 気に入れサービス
 * @author fukuzawa
 *
 */
@Transactional
@Service
public class FavoritesService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FavoritesMapper favoritesMapper;
	
	/**
	 * 
	 * 該当ユーザの気に入れ一覧取得
	 * 
	 * @param userid
	 * @return  List<MovieInfo>
	 */
	public List<MovieInfo> getFavoritesByUserid(String userId){
		
		List<MovieInfo> movieList = null;
		
		try {
			movieList = favoritesMapper.getFavoritesByUserid(userId);
		} catch (Exception e) {
			logger.error("気に入れ一覧取得時、エラが発生しました。");
		}
		
        return movieList;
	}
	
	/**
	 * 
	 * 気に入れに登録
	 * 
	 * @param favorites
	 * @return  なし
	 */
	public void insertFavorites(Favorites favorites){
		
		try {
			favoritesMapper.insertFavorites(favorites);
		} catch (Exception e) {
			logger.error("気に入れ一覧取得時、エラが発生しました。");
		}
		
	}
	
	/**
	 * 
	 * 気に入れから削除
	 * 
	 * @param favorites
	 * @return  なし
	 */
	public void deleteFavorites(Favorites favorites){
		
		try {
			favoritesMapper.deleteFavorites(favorites);
		} catch (Exception e) {
			logger.error("気に入れから削除時、エラが発生しました。");
		}
		
	}
	
	/**
	 * 
	 * 気に入れに存在するかチェック
	 * 
	 * @param favorites
	 * @return  int
	 */
	public int selectFavorites(Favorites favorites){
		
		int count = 0;
		try {
			count = favoritesMapper.selectFavorites(favorites);
		} catch (Exception e) {
			logger.error("気に入れに存在するかチェック時、エラが発生しました。");
		}
		
        return count;
	}
}
