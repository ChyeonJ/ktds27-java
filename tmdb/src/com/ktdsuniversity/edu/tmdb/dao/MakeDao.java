package com.ktdsuniversity.edu.tmdb.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.tmdb.dao.query.MakeQuery;
import com.ktdsuniversity.edu.tmdb.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.tmdb.db.helper.SQLType;
import com.ktdsuniversity.edu.tmdb.vo.MakeVO;
import com.ktdsuniversity.edu.tmdb.vo.ProducerVO;

public class MakeDao {
	
	private DataAccessHelper dah;
	
	public MakeDao(DataAccessHelper dah) {
		this.dah = dah;
		
	}

	public List<MakeVO> selectMakeByMovieId(String movieId) {
		
		List<MakeVO> reuslt = new ArrayList<MakeVO>();
		this.dah.preparedStatement(MakeQuery.makeSelectQuery(), (pstmt) -> {
			pstmt.setString(1, movieId);
		});
		this.dah.executeQuery(SQLType.SELECT, (rs) -> {
			MakeVO eachMake = new MakeVO();
			eachMake.setMakeId(rs.getString("MAKE_ID"));
			eachMake.setProducerId(rs.getString("PRODUCER_ID"));
			eachMake.setMovieId(rs.getString("MOVIE_ID"));
			eachMake.setRole(rs.getString("ROLE"));
			eachMake.setPart(rs.getString("PART"));
			
			ProducerVO eachProducer = new ProducerVO();
			eachProducer.setProducerId(rs.getString("PRODUCER_ID"));
			eachProducer.setProducerName(rs.getString("PRODUCER_NAME"));
			
			eachMake.setProducer(eachProducer);
			reuslt.add(eachMake);
		});
		
		return reuslt;
	}

}
