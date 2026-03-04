package com.ktdsuniversity.edu.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.query.BoardQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.BoardVO;

/**
 * Dao : Data Access Object
 * - Java에서 DB로 데이터 생성, 수정, 삭제, 조회를 하기위한 클래스
 */
public class BoardDao {
	
	private DataAccessHelper dah;
	
	public BoardDao(DataAccessHelper dah) {
		this.dah = dah;
	}
	
	public List<BoardVO> readAllArticles() {
		//UDPATE와 SELECT를 써야함
		// SELECT => 게시글의 내용을 조회.
		List<BoardVO> result = new ArrayList<>();
		this.dah.preparedStatement(BoardQuery.makeSelectAllQuery(), null);
		this.dah.executeQuery(SQLType.SELECT, rs -> {
			BoardVO eachArticle = new BoardVO();
			eachArticle.setId(rs.getString("ID"));
			eachArticle.setTitle(rs.getString("TITLE"));
			eachArticle.setContent(rs.getString("CONTENT"));
			eachArticle.setViewCount(rs.getInt("VIEW_COUNT"));
			eachArticle.setWriteDate(rs.getString("WRITE_DATE"));
			eachArticle.setLatestModifyDate(rs.getString("LATEST_MODIFY_DATE"));
			result.add(eachArticle);
		});
		return result;
	}
	
	public void updateViewCount(String articleId) {
		// UPDATE => 조회수를 1 증가.
		this.dah.preparedStatement(BoardQuery.makeUpdateViewCountQuery(), (pstmt) -> {
			pstmt.setString(1, articleId);
		});
		this.dah.executeQuery(SQLType.UPDATE, null);
	}

	public BoardVO readArticle(String articleId) {
		// SELECT => 게시글의 내용을 조회.
		BoardVO result = new BoardVO();
		this.dah.preparedStatement(BoardQuery.makeSelectOneQuery(), (pstmt) -> {
			pstmt.setString(1, articleId);
		});
		this.dah.executeQuery(SQLType.SELECT, rs -> {
			result.setId(rs.getString("ID"));
			result.setTitle(rs.getString("TITLE"));
			result.setContent(rs.getString("CONTENT"));
			result.setViewCount(rs.getInt("VIEW_COUNT"));
			result.setWriteDate(rs.getString("WRITE_DATE"));
			result.setLatestModifyDate(rs.getString("LATEST_MODIFY_DATE"));
		});
		
		return result;
	}
	
	public void deleteArticle(String articeId) {
		
		this.dah.preparedStatement(BoardQuery.makeDeleteQuery(), (pstmt) -> {
			pstmt.setString(1, articeId);
		});	
		this.dah.executeQuery(SQLType.DELETE, null);
	}
	
	public void modifyArticle(BoardVO modifyArticle) {
		
		this.dah.preparedStatement(BoardQuery.makeUpdateQuery(), (pstmt) -> {
			pstmt.setString(1, modifyArticle.getTitle());
			pstmt.setString(2, modifyArticle.getContent());
			pstmt.setString(3, modifyArticle.getId());
		});
		this.dah.executeQuery(SQLType.UPDATE, null);
	}
	
	public void createNewArticle2 (BoardVO newArticle) {
		
		this.dah.preparedStatement(BoardQuery.makeInsertQuery(), (pstmt) -> {
				pstmt.setString(1, newArticle.getTitle());
				pstmt.setString(2, newArticle.getContent());
			});
		this.dah.executeQuery(SQLType.INSERT, null);
	}
	
	public int createNewArticle(BoardVO newArticle) {
		
		// 1. ojdbc11.jar 파일이 프로젝트에 존재하는지 확인
		try {
			// oracle.jdbc.driver.OracleDriver 클래스를 불러온다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// oracle.jdbc.driver.OracleDriver를 찾지 못했을 때
			System.out.println("오라클 데이터베이스에 접속하기 위한 라이브러리가 없습니다.");
			return 0;
		}
		
		// 2. OracleDB에 접속
		Connection connection = null;
		//DriverManager - 자바에서 데이터베이스를 관리하고, 적잘한 드라이버를 통해 DB 커넥션을 획든하는 클래스
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "BOARD", "BOARD");
			//MANUAL COMMIT으로 변경. => 트랜젝션 설정
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// 주소가 틀렸거나, 계정이 틀렸을 경우 예외처리 반환,
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}
		
		// 3. INSERT Query 작성
		// Strin Buffer를 사용한다.
		StringBuffer query = new StringBuffer();            
		query.append(" INSERT INTO BOARD.BOARD                                                            ");
		query.append("  (ID                                                                               ");
		query.append("  , TITLE                                                                           ");
		query.append("  , CONTENT                                                                         ");
		query.append("  , WRITE_DATE)                                                                     ");
		query.append(" VALUES                                                                             ");
		query.append("  ('BO-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_BOARD_PK.NEXTVAL, 6, '0')     ");
		query.append(" , ?                                                                             ");
		query.append(" , ?                                                                              ");
		query.append(" , SYSDATE)                                                                         ");
		
		// 3-1 ?에 데이터 할당하기
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query.toString());
			//setString(1) => 위의 Query의 물음표중 첫번째 즉 title에 TestMain에 작성해서 파라미터로 넘어올 newArticle을 작성해라 라는 
			pstmt.setString(1, newArticle.getTitle());
			pstmt.setString(2, newArticle.getContent());
		} catch (SQLException e) {
			//pstmt의 파이프가 만들어져 있는 상태에서 예외가 발생했다면
			// 닫는다.
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {}
			}
			//연결된 Connection을 닫는다.
			try {
				connection.close();
			} catch (SQLException e1) {}
			
			// 1. 데이터베이스와 연결이 끊어져 있을 때 
			// 2. 작성된 쿼리의 내용이 잘못되었을 때
			System.out.println("쿼리 내용에 문제가 있습니다.");
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}
		
		// 4. INSERT Query 실행.
		try {
			int insertCount = pstmt.executeUpdate();
			//수동 커밋 상태에서 오류가 없다면 Commit 수행
			connection.commit();
			return insertCount;
		} catch (SQLException e) {
			// 수동커밋 상태에서 오류가 발생한다면 롤백해라
			try {
				connection.rollback();
			} catch (SQLException e1) {}
			// 1. Insert 쿼리에 파라미터 할당이 잘못되었을 때 (예 > ?는 2개인데, 할당한 데이터는 1개일 때)
			// 2. PK가 중복되었을 때
			// 3. 컬럼의 타입과 INSERT 하는 값이 다를 때
			// 4. 컬럼이 허용하는 최대 길이보다 값의 길이가 더 클 때
			System.out.println("쿼리 실행을 실패했습니다.");
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}//에러가나도, 실행이 끝나도 파이프를 닫아라
		finally {
			try {
				pstmt.close();
			} catch (SQLException e) {}
			try {
				connection.close();
			} catch (SQLException e) {}
		}
	}

	
}
