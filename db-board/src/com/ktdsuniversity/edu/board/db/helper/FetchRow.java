package com.ktdsuniversity.edu.board.db.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface FetchRow {

	public void fetch(ResultSet rs) throws SQLException;
	
}