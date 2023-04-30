package com.techtest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.techtest.entiy.MovieInfo;
import com.techtest.entiy.SearchKeyword;
 
/**
 * 
 * 映画マッパーインタフェース
 * @author fukuzawa
 *
 */
@Mapper
public interface MoviesMapper {

	/**
	 * 
	 * 映画検索
	 * 
	 * @param searchKeyword
	 * @return  List<MovieInfo>
	 */
	public List<MovieInfo> getMovies(SearchKeyword searchKeyword);

}
