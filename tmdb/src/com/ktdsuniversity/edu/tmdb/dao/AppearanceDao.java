package com.ktdsuniversity.edu.tmdb.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.tmdb.dao.query.AppearanceQuery;
import com.ktdsuniversity.edu.tmdb.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.tmdb.db.helper.SQLType;
import com.ktdsuniversity.edu.tmdb.vo.ActorVO;
import com.ktdsuniversity.edu.tmdb.vo.AppearanceVO;

public class AppearanceDao {
	
	private DataAccessHelper dah;
	
	public AppearanceDao(DataAccessHelper dah) {
		this.dah = dah;
	}

	public List<AppearanceVO> selectAppearanceByMovieId(String movieId) {
		
		List<AppearanceVO> resut = new ArrayList<AppearanceVO>();
		this.dah.preparedStatement(AppearanceQuery.appearanceSelectQuery(), (pstmt) -> {
			pstmt.setString(1, movieId);
		});
		this.dah.executeQuery(SQLType.SELECT, (rs) -> {
			AppearanceVO eachAppearance = new AppearanceVO();
			eachAppearance.setActorId(rs.getString("APPEARANCE_ID"));
			eachAppearance.setActorId(rs.getString("ACTOR_ID"));
			eachAppearance.setMovieId(rs.getString("MOVIE_ID"));
			eachAppearance.setCharacter(rs.getString("CHARACTER"));
			
			ActorVO eachActor = new ActorVO();
			eachActor.setActorId(rs.getString("ACTOR_ID"));
			eachActor.setActorName(rs.getString("ACTOR_NAME"));
			eachActor.setActorProfileUrl(rs.getString("ACTOR_PROFILE_URL"));
			
			//이거 외워야겠다
			eachAppearance.setActor(eachActor);
			resut.add(eachAppearance);
		});
		
		return resut;
	}

}
