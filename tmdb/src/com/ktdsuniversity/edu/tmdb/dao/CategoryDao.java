package com.ktdsuniversity.edu.tmdb.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.tmdb.dao.query.CategoryQuery;
import com.ktdsuniversity.edu.tmdb.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.tmdb.db.helper.SQLType;
import com.ktdsuniversity.edu.tmdb.vo.CategoryVO;

public class CategoryDao {
	
	private DataAccessHelper dah;
	
	public CategoryDao(DataAccessHelper dah) {
		this.dah = dah;
	}

	public List<CategoryVO> selectCategoriesByMovieId(String movieId) {
		
		List<CategoryVO> result = new ArrayList<>();
		this.dah.preparedStatement(CategoryQuery.makeSelectQuery(), (pstmt) -> {
			pstmt.setString(1, movieId);
		});
		this.dah.executeQuery(SQLType.SELECT, (rs) -> {
			CategoryVO eachCategory = new CategoryVO();
			eachCategory.setCategoryId(rs.getString("CATEGORY_ID"));
			eachCategory.setCategoryName(rs.getString("CATEGORY_NAME"));
			result.add(eachCategory);
		});
		
		return result;
	}


}
