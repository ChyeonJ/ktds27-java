package com.ktdsuniversity.edu.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.board.vo.BoardVO;


/**
 * 서비스와 다르게 Mapper는 class를 생성하지 않는다, MyBatis가 생성해준다
 */
@Mapper
public interface BoardDao {

	List<BoardVO> selectBoardList();

	int selectBoardCount();

}
