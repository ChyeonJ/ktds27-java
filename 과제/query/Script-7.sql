-- 케이팝 데몬헌터스의 영화 아이디 조회
SELECT MOVIE_ID
  FROM MOVIE
 WHERE TITLE = '케이팝 데몬 헌터스'
;
-- 케이팝 데먼 헌터스 영화의 정보를 조회한다.
SELECT *
  FROM MOVIE
 WHERE TITLE = '케이팝 데몬 헌터스'
;
-- 케이팝 데몬 헌터스의 장르명을 조회한다.
SELECT *
  FROM CATEGORY
 WHERE CATEGORY_ID IN (SELECT DISTINCT CATEGORY_ID
                         FROM GENRE
                        WHERE MOVIE_ID = (SELECT MOVIE_ID
                                            FROM MOVIE
                                           WHERE TITLE = '케이팝 데몬 헌터스'))
;
-- 케이팝 데몬 헌터스를 제작한 제작진의 이름과 역할 그리고 파트를 조회한다.
SELECT P.PRODUCER_NAME
     , M.ROLE
     , M.PART
  FROM PRODUCER P
 INNER JOIN MAKE M
    ON P.PRODUCER_ID = M.PRODUCER_ID
 WHERE M.MOVIE_ID = (SELECT MOVIE_ID
  FROM MOVIE
 WHERE TITLE = '케이팝 데몬 헌터스')
;
-- 케이팝 데몬 헌터스에 출연한 출연진의 이름을 배역명을 조회한다.,
SELECT *
  FROM ACTOR A
 INNER JOIN APPEARANCE AP
    ON A.ACTOR_ID = AP.ACTOR_ID 
 WHERE AP.MOVIE_ID = (SELECT MOVIE_ID
                        FROM MOVIE
                       WHERE TITLE = '케이팝 데몬 헌터스')
;

SELECT *
  FROM APPEARANCE AP
 WHERE AP.MOVIE_ID = (SELECT MOVIE_ID
                        FROM MOVIE
                       WHERE TITLE = '케이팝 데몬 헌터스')
;

UPDATE APPEARANCE
   SET MOVIE_ID = REPLACE(MOVIE_ID, 'AC-', 'MV-')

MV-20260227-000001
MV-20260227-000001

AP-20260227-000001
AC-20260227-000001
AC-20260227-000001