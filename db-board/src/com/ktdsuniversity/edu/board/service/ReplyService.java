package com.ktdsuniversity.edu.board.service;

import com.ktdsuniversity.edu.board.dao.ReplyDao;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;

public class ReplyService {
	
	private DataAccessHelper dah;
	private ReplyDao replyDao;
	
	public ReplyService(DataAccessHelper dah) {
		this.dah = dah;
		this.replyDao = new ReplyDao(dah);
	}
	
	
	
}
