package com.techtest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.techtest.entiy.Favorites;
import com.techtest.entiy.MovieInfo;
 
/**
 * 
 * 気に入れマッパーインタフェース
 * @author fukuzawa
 *
 */
@Mapper
public interface FavoritesMapper {

	/**
	 * 
	 * 該当ユーザの気に入れ一覧取得
	 * 
	 * @param userid
	 * @return  List<MovieInfo>
	 */
	public List<MovieInfo> getFavoritesByUserid(String userId);
	
	/**
	 * 
	 * 気に入れに登録
	 * 
	 * @param favorites
	 * @return  なし
	 */
	public void insertFavorites(Favorites favorites);
	
	/**
	 * 
	 * 気に入れから削除
	 * 
	 * @param favorites
	 * @return  なし
	 */
	public void deleteFavorites(Favorites favorites);
	
	/**
	 * 
	 * 気に入れに存在するかチェック
	 * 
	 * @param favorites
	 * @return  int
	 */
	public int selectFavorites(Favorites favorites);
}
