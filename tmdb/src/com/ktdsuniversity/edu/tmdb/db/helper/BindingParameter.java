package com.ktdsuniversity.edu.tmdb.db.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface BindingParameter {

	public void bind(PreparedStatement pstmt) throws SQLException;
	
}