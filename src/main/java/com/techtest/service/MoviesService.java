package com.techtest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techtest.entiy.MovieInfo;
import com.techtest.entiy.SearchKeyword;
import com.techtest.mapper.MoviesMapper;

/**
 * 
 * 映画サービス
 * @author fukuzawa
 *
 */
@Transactional
@Service
public class MoviesService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MoviesMapper moviceMapper;
	
	/**
	 * 
	 * 映画検索
	 * 
	 * @param searchKeyword
	 * @return  List<MovieInfo>
	 */
	public List<MovieInfo> getMovies(SearchKeyword searchKeyword){
		
		List<MovieInfo> movieList = null;
		
		try {
			movieList = moviceMapper.getMovies(searchKeyword);
		} catch (Exception e) {
			logger.error("映画検索時、エラが発生しました。");
		}
		
        return movieList;
	}

}
