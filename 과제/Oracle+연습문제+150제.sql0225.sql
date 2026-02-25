-- SCALA QUERY
-- Select의 결과를 fetch 할 때 마다 서브쿼리를 실행 ==> 실행된 단 하나의 ROW & COLUMN을  Fetch하는 방법.
-- 사용 권장하지 않음 --> 성능 하락의 주요 원인.
--  ==> 3000개를 Fetch하면 Scala 쿼리가 3000번 수행됨
-- 사용을 해여할 때 - 어쩔 수 없는 상황일 때
-- 어떤 데이터 하나를 조회하기 위해 하나 이상의 Join이 더 필요할 때.
-- 기존의 조인 테이블의 수 == 7개 ==> 추가 정보를 얻어오기 위해 1개의 조인을 더 필요로 할 때
          --> 추가된 테이블이 기존의 조인과는 큰 관계가 없을 떄 사용
-- 부서명과 도시명을 조회한다 - 조인 X
SELECT D.DEPARTMENT_NAME
     , D.LOCATION_ID
     , (SELECT CITY -- SCALA 쿼리는 반드시 하나의 컬럼만 조회해야한다. & 반드시 하나의 ROW만 조회 되어야 한다. 복수 조회 시 "값의 수가 너무 많습니다" 에러 발생
          FROM LOCATIONS L
         WHERE L.LOCATION_ID = D.LOCATION_ID) AS CITY
     , '도시의 이름은' || (SELECT CITY
				          FROM LOCATIONS L
				         WHERE L.LOCATION_ID = D.LOCATION_ID) || '입니다.'
  FROM DEPARTMENTS D
;

-- 도시명과 부서의 이름을 조회한다. (JOIN X)
SELECT L.CITY
     , L.LOCATION_ID
     , (SELECT DEPARTMENT_NAME
          FROM DEPARTMENTS D
         WHERE D.LOCATION_ID = L.LOCATION_ID) AS DEPT_NAME
  FROM LOCATIONS L
;

-- 글자 채우기 (LPAD(), RPAD())
--   LPAD(컬럼, 자리수, 남은 자리수 만큼 채울 문자) -> LEFT PADDING (컬럼의 값 왼쪽에 자리수 만큼 채움)
--   RPAD(컬럼, 자리수, 남은 자리수 만큼 채울 문자) -> RIGHT PADDING (컬럼의 값 오른쪽에 자리수 만큼 채움)
-- 대소문자 바꾸기(UPPER(), LOWER()) 
--  UPPER(컬럼) --> 소문자들을 모두 대문자로 변경
--  LOWER(컬럼) --> 대문자들을 모두 소문자로 변경
-- 문자열 연결 연산자 (|| <-- Concat)
--   예> 'a' || 'b' ==> 'ab'
-- 문자열 바꾸기 함수 (REPLACE())
--   예> "Min Chang" ==> "Min_Chang" REPLACE(Min Chang', ' ', '_')
WITH TEMP AS (SELECT 'Hyeon Jong' AS FIRST_NAME
                   , 'Choi' AS LAST_NAME
                   , 'HyeonJongChoi' AS EMAIL
                FROM DUAL)
SELECT FIRST_NAME
     , LAST_NAME
     , EMAIL
     , LPAD(FIRST_NAME, 5, '-')
     , RPAD(FIRST_NAME, 5, '-')
     , RPAD(FIRST_NAME, 1, '-')
     , LOWER(FIRST_NAME)
     , UPPER(LAST_NAME)
     , FIRST_NAME || LAST_NAME
     , LOWER(FIRST_NAME || LAST_NAME)
     , REPLACE(FIRST_NAME, ' ', '0') || LAST_NAME
     , REPLACE(FIRST_NAME, ' ') || LAST_NAME
  FROM TEMP
;

-- 가장 많은 월급을 받는 사원 한 명만 조회한다.
SELECT *
  FROM EMPLOYEES
 WHERE SALARY = (SELECT MAX(SALARY)
                   FROM EMPLOYEES)
;

--최상위 직원의 모든 부하직원들의 사원번호, 이름, 상사사원번호를 계단식(계층식)으로 조회한다.
 SELECT LEVEL
      , EMPLOYEE_ID
      , FIRST_NAME
      , MANAGER_ID
   FROM EMPLOYEES
  START WITH MANAGER_iD IS NULL -- ROOT NODE 지정
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID -- 상하관계를 명시, (상위 노드 기준으로 작성 : 상사의 사원 번호는 다른 직원의 상사번호와 같다)

-- 101번 사원의 모든 부하직원들의 사원번호, 이름, 상사사원번호를 계층식으로 조회한다.
 SELECT LEVEL
      , EMPLOYEE_ID
      , FIRST_NAME
      , MANAGER_ID
   FROM EMPLOYEES
  START WITH EMPLOYEE_ID = 101
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;

-- 206번 사원의 모든 상사직원들의 사원번호, 이름, 상사사원번호를 계층식으로 조회한다.
 SELECT LEVEL
      , EMPLOYEE_ID
      , FIRST_NAME
      , MANAGER_ID
   FROM EMPLOYEES
  START WITH EMPLOYEE_ID = 206
CONNECT BY PRIOR MANAGER_ID = EMPLOYEE_ID -- 상하관계 명시 (상위 노드 기준으로 작성 : 부하직원의 상사 사원 번호는 누군가의 사원 번호다)
  ORDER BY LEVEL DESC

-- 직무명 별 수핼중인 사원의 수를 조회한다.
-- Step 1. 직무 아이디별 수행중인 사원의 수를 조회
SELECT JOB_ID
     , COUNT(EMPLOYEE_ID)
  FROM EMPLOYEES
 GROUP BY JOB_ID
;
-- Step 2. 직무명 별 수행중인 사원의 수를 조회
SELECT J.JOB_TITLE
     , COUNT(E.EMPLOYEE_ID)
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 GROUP BY J.JOB_TITLE
;

-- 목적 조인 + 그룹핑 사용하는 경우
-- 주문 목록 ( 상품 준비중 : 3, 배송시작 : 4, 배송중 : 1, 배송완료 : 40, 리뷰작성 : 30, 리뷰작성가능 : 3  )
-- 주문의 상태별로 COUNT를 

-- 도시별 근무중인 사원의 수를 조회한다.
SELECT L.CITY 
     , COUNT(E.EMPLOYEE_ID)
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 GROUP BY L.CITY 
;
-- 도시별, 부서별로 근무중인 사원의 수를 조회한다.
SELECT L.CITY 
     , D.DEPARTMENT_NAME 
     , COUNT(E.EMPLOYEE_ID)
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 GROUP BY L.CITY 
     , D.DEPARTMENT_NAME 
;

-- 사원의 이름과 성, 근무중인 부서 번호와 부서명을 조회한다 (교집합)
-- EQUIP QUERY => 쓰지 않는게 좋다
SELECT E.FIRST_NAME 
     , E.LAST_NAME
     , D.DEPARTMENT_ID 
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
;

-- 사원의 이름과 성, 월급, 수행중인 직무의 아이디, 직무의 이름, 직무별 최대 월급을 조회한다.
SELECT E.FIRST_NAME 
     , E.LAST_NAME 
     , E.SALARY 
     , E.JOB_ID 
     , J.JOB_TITLE 
     , J.MAX_SALARY 
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID
; 

-- 사원의 이름, 사원 번호, 월급, 수행중인 직무의 이름, 근무중인 부서의 이름을 조회한다.
SELECT E.FIRST_NAME 
     , E.EMPLOYEE_ID 
     , E.SALARY 
     , J.JOB_TITLE 
     , D.DEPARTMENT_NAME
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
;

-- EMPLOYEES - LOCATIONS 테이블이 관계가 없을 상황
-- 사원의 사원 번호, 이름, 성, 이메일과 근무중인 도시의 이름을 조회한다.
SELECT E.EMPLOYEE_ID 
     , E.FIRST_NAME 
     , E.EMAIL 
     , L.CITY
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
; 

-- 모든 사원들의 이름, 상사의 사원번호, 상사의 사원명을 조회한다.
-- 사원이 수행중인 직무의 이름, 상사가 수행중인 직무의 이름을 조회
SELECT E.FIRST_NAME 
     , M.EMPLOYEE_ID 
     , M.FIRST_NAME 
     , EJ.JOB_TITLE
     , MJ.JOB_TITLE 
  FROM EMPLOYEES E
 INNER JOIN EMPLOYEES M
    ON E.EMPLOYEE_ID = M.MANAGER_ID
 INNER JOIN JOBS EJ
    ON E.JOB_ID = EJ.JOB_ID 
 INNER JOIN JOBS MJ
    ON E.JOB_ID = MJ.JOB_ID 
; 

-- 중복 제거 문제
-- 모든 사원들의 상사 사원 번호를 조회한다 (중복 없이)
SELECT DISTINCT MANAGER_ID --DISTINCT가 하는 역할 한 row가 중복 없게 만들어준다
     , FIRST_NAME 
  FROM EMPLOYEES
;
-- 연습 문제
-- 최고 월급을 받는 사원들의 사원 번호와 월급을 조회한다.
-- 1. 모르는 것? ==> 최고 월급을 알지 못한다 => 24,000
--      최고 월급 = 사원의 월급
SELECT MAX(SALARY)
  FROM EMPLOYEES
;
SELECT EMPLOYEE_ID
     , SALARY
  FROM EMPLOYEES
 WHERE SALARY = (SELECT MAX(SALARY)
                   FROM EMPLOYEES)
;

-- 평균 월급보다 적게 받는 사원들의 사원번호와 월급을 조회한다.
-- 모르는 것 => 평균 월급이 얼마인지 모름
SELECT EMPLOYEE_ID
      , SALARY m                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
  FROM EMPLOYEES
 WHERE SALARY < (SELECT AVG(SALARY)
                   FROM EMPLOYEES)
;

-- 수행하는 직무의 이름이 'Finance Manager' 인 사원의 사원 번호와 직무 아이디를 조회한다.
-- 모르는 것 : 직무의 이름 'FIANCE MANAGER'
SELECT JOB_ID
  FROM JOBS
 WHERE JOB_TITLE = 'Finance Manager'
;
-- 모르는 것 => 'Finance Manager'인 사원의 사원 번호, 직무 아이디 조회
SELECT EMPLOYEE_ID
     , JOB_ID
  FROM EMPLOYEES
 WHERE JOB_ID = (SELECT JOB_ID
 				   FROM JOBS
                  WHERE JOB_TITLE = 'Finance Manager')
;

-- Seattle에서 근무중인 사원의 이름, 성, 부서 번호를 조회한다.
-- 1. 모르는 것 : Seattle의 지역 번호가 무엇인지 모른다
SELECT LOCATION_ID
  FROM LOCATIONS
 WHERE CITY = 'Seattle'
;
-- 2. 모르는 것 : Seattle에 있는 부서들의 번호가 무엇인지 모른다. ==> 지역변호가 1700인 부서의 번호가 무엇인지 모른다
SELECT  DEPARTMENT_ID
  FROM DEPARTMENTS
 WHERE LOCATION_ID = (SELECT LOCATION_ID
  						FROM LOCATIONS
 					   WHERE CITY = 'Seattle')
;
-- Seattle에 있는 부서들의 번호가 무엇인지 안다면, 사원의 이름과 성, 부서 번호를 조회할 수 있다.
--> 10, 30, 90, 100, 110
SELECT LAST_NAME
     , FIRST_NAME
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IN (SELECT  DEPARTMENT_ID
 						   FROM DEPARTMENTS
 						  WHERE LOCATION_ID = (SELECT LOCATION_ID
  												 FROM LOCATIONS
 					                            WHERE CITY = 'Seattle'))
;


-- 1. 현재 시간을 조회한다.
-- ORACLE 전용 (ANSI X 표준이 아님)
SELECT *
  FROM DUAL --ORACLE이 만든 가짜 테이블 (더미 데이터)
;
SELECT SYSDATE --SYSDATE는 컬럼이 아니고 예약어 (현재 시간을 가리키는 Oracle전용 예약어)
  FROM DUAL --ORACLE이 만든 가짜 테이블 (더미 데이터)
;
-- 오늘 기준으로 하루 전의 날짜와 시간을 조회
SELECT SYSDATE - 1 --하루전의 날짜와 시간이 노출함 +1이면 내일
  FROM DUAL
 ;
-- 현재 시간을 기준으로 3시간 전의 날짜와 시간을 조회.
SELECT SYSDATE - 3 / 24 --24시간을 3으로 나눠주면 3시간전 시간이 됨
  FROM DUAL
;
-- 현재 시간을 기준으로 15분전의 날짜와 시간을 조회
SELECT SYSDATE - 15 / 24 / 60 -- 분을 뺴는 방법
  FROM DUAL
;
-- 현재 시간을 기준으로 50초 이후의 날짜와 시간을 조회
SELECT SYSDATE + 50 / 24 / 60 / 60 -- 초를 다루는 방법
	 , SYSDATE
  FROM DUAL
;
-- 월 더하기
SELECT ADD_MONTHS(SYSDATE, -2) -- ADD_MONTHS까지 있다 
  FROM DUAL
;
-- 2. 현재 시간을 "연-월-일" 포멧으로 조회한다.
SELECT TO_CHAR(SYSDATE, 'YYYY') -- 연
	 , TO_CHAR(SYSDATE, 'MM') -- 월
	 , TO_CHAR(SYSDATE, 'DD') -- 일
	 , TO_CHAR(SYSDATE, 'HH') -- 12시간 기준의 시
	 , TO_CHAR(SYSDATE, 'HH24') -- 24시간 기준의 시
	 , TO_CHAR(SYSDATE, 'MI') -- 분
	 , TO_CHAR(SYSDATE, 'SS') -- 초
  FROM DUAL
;

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')
	 , TO_CHAR(SYSDATE, 'HH24:MI:SS')
  FROM DUAL
;
-- 3. 한 시간 전 시간을 "시:분:초" 포멧으로 조회한다.
SELECT TO_CHAR(SYSDATE - 1 / 24, 'HH24:MI:SS')
  FROM DUAL
;

-- 2월 12일 연습문제
-- '2026-02-12' 텍스트를 날짜타입으로 변경한다.
SELECT TO_DATE('2026-02-12','YYYY-MM-DD')
  FROM DUAL
;
-- '2026-02-12' 기준으로 50일 이후 날짜를 조회한다.
SELECT TO_DATE('2026-02-12', 'YYYY-MM-DD') +50
  FROM DUAL
;

-- '2026-02-13' 기준으로 3시간 이후의 날짜와 시간을 조회한다.
SELECT TO_DATE('2026-02-12', 'YYYY-MM-DD') + 3 / 24
  FROM DUAL
;

-- '2026-02-12' 기준으로 1일 후의 날짜만 조회한다 ★ 중요
SELECT TO_CHAR(TO_DATE('2026-02-12', 'YYYY-MM-DD') + 1, 'YYYY-MM-DD')
  FROM DUAL
;
-- '2026-02-12' 기준으로 3일 후의 날짜와 시만 조회한다 ★ 중요
SELECT TO_CHAR(TO_DATE('2026-02-12', 'YYYY-MM-DD') + 3, 'YYYY-MM-DD-HH24')
  FROM DUAL
;

-- 4. EMPLOYEES 테이블의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
;
-- 5. DEPARTMENTS 테이블의 모든 정보를 조회한다.
SELECT DEPARTMENT_ID
     , DEPARTMENT_NAME
     , MANAGER_ID
     , LOCATION_ID
  FROM DEPARTMENTS
;
-- 6. JOBS 테이블의 모든 정보를 조회한다.
SELECT JOB_ID
     , JOB_TITLE
     , MIN_SALARY
     , MAX_SALARY
  FROM JOBS
;
-- 7. LOCATIONS 테이블의 모든 정보를 조회한다.
SELECT LOCATIONS_ID
     , STREET_ADDRESS
     , POSTAL_CODE
     , CITY
     , STATE_PROVINCE
     , COUNTRY_ID
  FROM LOCATIONS
;
-- 8. COUNTRIES 테이블의 모든 정보를 조회한다.
SELECT COUNTRY_ID
     , COUNTRY_NAME
     , REGION_ID
  FROM COUNTRIES
;
-- 9. REGIONS 테이블의 모든 정보를 조회한다.
SELECT REGION_ID
     , REGION_NAME
  FROM REGIONS
;
-- 10. JOB_HISTORY 테이블의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , START_DATE
     , END_DATE
     , JOB_ID
     , DEPARTMENT_ID
  FROM JOB_HISTORY
;
-- 11. 90번 부서에서 근무하는 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 90
;
-- 12. 90번, 100번 부서에서 근무하는 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 90
    OR DEPARTMENT_ID = 100
;
-- 13. 100번 상사의 직속 부하직원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE MANAGER_ID = 100
;
-- 14. 직무 아이디가 AD_VP 인 사원의 모든 정보를 조회한다.
-- COLUMN TYPE => 날짜 = '2026-02-11', 숫자 = 1000, 텍스트 = 'AD_VP';
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE JOB_ID = 'AD_VP'
;

-- 15. 월급이 7000 이상인 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE SALARY > 7000
;
-- 16. 2005년 09월에 입사한 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE >= TO_DATE('2005-09-01', 'YYYY-MM-DD')
   AND HIRE_DATE < TO_DATE('2005-10-01', 'YYYY-MM-DD')
;
-- 16. 2005년 09월에 입사한 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN0 T0O_DATE('2005-09-01', 'YYYY-MM-DD') AND TO_DATE('2005-10-01', 'YYYY-MM-DD') -1 
 -- BETWEEN은 이하 처리가 되서 10월 01일 입사자도 노출하게 됨
;

-- 17. 111번 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID = '111'
;
-- 18. 인센티브를 안받는 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NULL --NULL을 비교할 때는 IS를 붙이면 된다
;
-- 19. 인센티브를 받는 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT > 0 -- IS NULL을 부정하는게 좀 더 나은 방법 => IS NOT NULL
;
-- 20. 이름의 첫 글자가 'D' 인 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE FIRST_NAME LIKE 'D%'
;
-- 21. 성의 마지막 글자가 'a' 인 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE LAST_NAME LIKE '%a'
;
-- 22. 전화번호에 '.124.'이 포함된 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE PHONE_NUMBER LIKE '%.124.%'
;
-- 23. 직무 아이디가 'PU_CLERK'인 사원 중 월급이 3000 이상인 사원들의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE JOB_ID = 'PU_CLERK' 
   AND SALARY >= 3000
;
-- 24. 평균 월급보다 많이 받는 사원들의 사원번호, 이름, 성, 월급을 조회한다.
-- 평균 월급보다 많이 받는 사원
SELECT AVG(SALARY)
  FROM EMPLOYEES
;
-- 사원들의 사원 번호, 이름, 성, 월급 조회
SELECT EMPLOYEE_ID 
     , FIRST_NAME
     , LAST_NAME 
     , SALARY 
  FROM EMPLOYEES
 WHERE SALARY > (SELECT AVG(SALARY)
                   FROM EMPLOYEES)
;

SELECT 
-- 25. 평균 월급보다 적게 받는 사원들의 사원번호, 월급, 부서번호를 조회한다.
-- 평균 월급보다 적게 받는 사원
SELECT AVG(SALARY)
  FROM EMPLOYEES
;
-- 사원들의 사원번호, 월급, 부서번호를 조회한다.
SELECT EMPLOYEE_ID 
     , FIRST_NAME
     , LAST_NAME 
     , SALARY 
  FROM EMPLOYEES
 WHERE SALARY < (SELECT AVG(SALARY)
                   FROM EMPLOYEES)
;

-- 26. 가장 많은 월급을 받는 사원의 사원번호, 이름, 월급을 조회한다.
-- 가장 많은 월급
SELECT MAX(SALARY)
  FROM EMPLOYEES
;
-- 사원의 사원번호, 이름, 월급을 조회한다.
SELECT EMPLOYEE_ID 
     , FIRST_NAME
     , SALARY 
  FROM EMPLOYEES
 WHERE SALARY  = (SELECT MAX(SALARY)
	                FROM EMPLOYEES)
;
-- 27. 이름이 4글자인 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE FIRST_NAME LIKE '____'
;
-- 이름이 4글자 이상인 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE FIRST_NAME LIKE '____%'
;

-- 28. 'SA_REP' 직무인 사원 중 가장 높은 월급과 가장 낮은 월급을 조회한다.
SELECT MAX(SALARY)
	 , MIN(SALARY)
  FROM EMPLOYEES
 WHERE JOB_ID = 'SA_REP'
;
-- 29. 직원의 입사일자를 '연-월-일' 형태로 조회한다.
SELECT TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') 
  FROM EMPLOYEES
;
-- 30. 가장 늦게 입사한 사원의 모든 정보를 조회한다.
-- 가장 늦게 입사
SELECT MAX()AX(HIRE_DATE)
  FROM EMPLOYEES
;
-- 모든 정보 조회
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE = (SELECT MAX(HIRE_DATE)
				      FROM EMPLOYEES)
;


-- 31. 가장 일찍 입사한 사원의 모든 정보를 조회한다.
-- 가장 일찍 입사
SELECT MIN(HIRE_DATE)
  FROM EMPLOYEES
;
-- 모든 정보 조회
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE = (SELECT MIN(HIRE_DATE)
				      FROM EMPLOYEES)
;
-- 32. 자신의 상사보다 더 많은 월급을 받는 사원의 모든 정보를 조회한다.
-- 자신의 상사?
SELECT EMPLOYEE_ID
	 , MANAGER_ID 
	 , SALARY 
  FROM EMPLOYEES
;
-- 자신의 상사의 월급
SELECT SALARY
  FROM EMPLOYEES MAN -- 상사
 WHERE EMPLOYEE_ID = (SELECT DISTINCT MANAGER_ID
                        FROM EMPLOYEES EMP --테이블엔 AS를 붙일 수 없다
                       WHERE EMP.MANAGER_ID = MAN.EMPLOYEE_ID )
;
-- 사원의 모든 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES EMP -- (자신 부하직원)
 WHERE SALARY > (SELECT SALARY
                   FROM EMPLOYEES MAN
                  WHERE MAN.EMPLOYEE_ID = EMP.MANAGER_ID)
; 
-- 33. 자신의 상사보다 더 일찍 입사한 사원의 모든 정보를 조회한다.
-- 자신의 상사보다 더 일찍
SELECT EMPLOYEE_ID 
     , MANAGER_ID 
     , HIRE_DATE 
  FROM EMPLOYEES
;
-- 자신? 상사? 날짜
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
 FROM EMPLOYEES EMP -- 자신
WHERE EMP.HIRE_DATE < (SELECT HIRE_DATE
                         FROM EMPLOYEES MAN
                        WHERE EMP.MANAGER_ID = MAN.EMPLOYEE_ID )
;
                         

-- 34. 부서아이디별 평균 월급을 조회한다.
SELECT DEPARTMENT_ID
     , AVG(SALARY)
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID 
;
-- 35. 직무아이디별 평균 월급, 최고월급, 최저월급을 조회한다.
SELECT JOB_ID 
	 , AVG(SALARY)
	 , MAX(SALARY)
	 , MIN(SALARY)
  FROM EMPLOYEES
 GROUP BY JOB_ID 
;
-- 36. 가장 많은 인센티브를 받는 사원의 모든 정보를 조회한다.
-- 가장 많은 인센티브
SELECT MAX(COMMISSION_PCT)
  FROM EMPLOYEES
;
-- 모든 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT = (SELECT MAX(COMMISSION_PCT)
  						   FROM EMPLOYEES)
;
-- 37. 가장 적은 인센티브를 받는 사원의 월급과 인센티브를 조회한다.
-- 가장 적은 인센티브
SELECT MIN(COMMISSION_PCT)
  FROM EMPLOYEES
;
-- 모든 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT = (SELECT MIN(COMMISSION_PCT)
  						   FROM EMPLOYEES)
;
-- 38. 직무아이디별 사원의 수를 조회한다.
SELECT COUNT(EMPLOYEE_ID)
  FROM EMPLOYEES
 GROUP BY JOB_ID 
;
-- 39. 상사아이디별 부하직원의 수를 조회한다. 단, 부하직원이 2명 이하인 경우는 제외한다.
SELECT MANAGER_ID 
     , COUNT(EMPLOYEE_ID)
  FROM EMPLOYEES
 GROUP BY MANAGER_ID 
HAVING COUNT(EMPLOYEE_ID) > 2
;
-- 40.  사원이 속한 부서의 평균월급보다 적게 받는 사원의 모든 정보를 조회한다.

-- 평균월급
SELECT AVG(SALARY)
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID = 90
;

-- 적게 받는 사원 모든 정보
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES EMP
 WHERE SALARY < (SELECT AVG(SALARY)
                   FROM EMPLOYEES DEP
                  WHERE DEP.DEPARTMENT_ID  = EMP.DEPARTMENT_ID)
 ;

-- 41. 사원이 근무하는 부서명, 이름, 성을 조회한다.
SELECT D.DEPARTMENT_NAME 
     , E.FIRST_NAME 
     , E.LAST_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.EMPLOYEE_ID = D.DEPARTMENT_ID 
;
-- 42. 가장 적은 월급을 받는 사원의 부서명, 이름, 성, 월급, 부서장사원번호를 조회한다.
-- SUBQUERY & JOIN
-- 가장 적은 월급
SELECT MIN(SALARY)
  FROM EMPLOYEES
;

SELECT *
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.EMPLOYEE_ID = D.DEPARTMENT_ID
 WHERE E.SALARY IN (SELECT MIN(E.SALARY)
                     FROM EMPLOYEES)
;

-- 43. 상사사원번호를 중복없이 조회한다.
-- 44. 50번 부서의 부서장의 이름, 성, 월급을 조회한다.
-- 50번 부서 부서장
SELECT MANAGER_ID 
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID = 50
;
-- 조회
SELECT FIRST_NAME
     , LAST_NAME
     , SALARY
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID = (SELECT MANAGER_ID 
  						FROM DEPARTMENTS
 					   WHERE DEPARTMENT_ID = 50)
;

-- 45. 부서명별 사원의 수를 조회한다.
SELECT D.DEPARTMENT_NAME 
     , COUNT(E.EMPLOYEE_ID)
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME  
;

-- 46. 사원의 수가 가장 많은 부서명, 사원의 수를 조회한다.
SELECT DEPARTMENT_NAME
     , CNT
  FROM (SELECT D.DEPARTMENT_NAME
             , COUNT(E.EMPLOYEE_ID) AS CNT
          FROM EMPLOYEES E
         INNER JOIN DEPARTMENTS D
            ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
         GROUP BY D.DEPARTMENT_NAME
         ORDER BY CNT DESC)
WHERE ROWNUM = 1
;
-- 47. 사원이 없는 부서명을 조회한다.
-- 사원이 있는 부서명을 조회한다.
-- 모르는것? => 사원이 근무중인 부서의 번호
SELECT DISTINCT DEPARTMENT_ID 
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
;
SELECT DEPARTMENT_NAME
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID NOT IN (SELECT DISTINCT DEPARTMENT_ID 
  							   FROM EMPLOYEES
                              WHERE DEPARTMENT_ID IS NOT NULL)
;
-- 48. 직무가 변경된 사원의 모든 정보를 조회한다.
-- 직무가 변경된 사원
SELECT EMPLOYEE_ID 
  FROM JOB_HISTORY
;
-- 사원 모든 정보출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID 
 				        FROM JOB_HISTORY)
;
-- 49. 직무가 변경된적 없는 사원의 모든 정보를 조회한다.
-- 직무가 변경된적 없는 사원
SELECT EMPLOYEE_ID 
  FROM JOB_HISTORY
;
-- 사원 모든 정보출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID NOT IN (SELECT EMPLOYEE_ID 
 				        FROM JOB_HISTORY)
;
-- 50. 직무가 변경된 사원의 과거 직무명과 현재 직무명을 조회한다.
SELECT J.JOB_TITLE 
     , JB.JOB_TITLE 
  FROM EMPLOYEES E
 INNER JOIN JOB_HISTORY JH
    ON E.EMPLOYEE_ID = JH.EMPLOYEE_ID 
 INNER JOIN JOBS J
    ON JH.JOB_ID = J.JOB_ID 
 INNER JOIN JOBS JB
    ON E.JOB_ID = JB.JOB_ID 
;

-- 직무가 변경된 사원 번호
-- 51. 직무가 가장 많이 변경된 부서의 이름을 조회한다.
SELECT DEPARTMENT_NAME 
  FROM (SELECT D.DEPARTMENT_NAME 
             , COUNT(J.EMPLOYEE_ID) AS CNT
          FROM DEPARTMENTS D
         INNER JOIN EMPLOYEES E
            ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
         INNER JOIN JOB_HISTORY J
            ON E.EMPLOYEE_ID = J.EMPLOYEE_ID
         GROUP BY D.DEPARTMENT_NAME
         ORDER BY CNT DESC)
  WHERE ROWNUM = 1
;

-- 부서 아이디별 EMPLOYEE_ID의 개수를 세서 내림차순으로 정렬 
SELECT J.DEPARTMENT_ID
     , COUNT(J.EMPLOYEE_ID) AS CNT
  FROM JOB_HISTORY J
 GROUP BY J.DEPARTMENT_ID
 ORDER BY CNT DESC
;

-- JOIN에 INNER VIEW 사용해서 해결
SELECT DEPARTMENT_NAME
  FROM DEPARTMENTS D
 INNER JOIN (SELECT J.DEPARTMENT_ID AS ID
                  , COUNT(J.EMPLOYEE_ID) AS CNT
               FROM JOB_HISTORY J
              GROUP BY J.DEPARTMENT_ID
              ORDER BY CNT DESC)
    ON D.DEPARTMENT_ID = ID
 WHERE ROWNUM = 1
;
 
-- 52. 'Seattle' 에서 근무중인 사원의 이름, 성, 월급, 부서명 을 조회한다.
-- SUBQUERY & JOIN
-- 'Seattle'에서 근무중인 사원
SELECT LOCATION_ID 
  FROM LOCATIONS
 WHERE CITY LIKE 'Seattle'
;

SELECT E.FIRST_NAME 
     , E.LAST_NAME 
     , E.SALARY 
     , D.DEPARTMENT_NAME 
     , L.CITY 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.LOCATION_ID IN (SELECT LOCATION_ID 
                           FROM LOCATIONS
                          WHERE CITY = 'Seattle')
;



-- 53. 'Seattle' 에서 근무하지 않는 모든 사원의 이름, 성, 월급, 부서명, 도시를 조회한다.
-- SUBQUERY & JOIN
-- 'Seattle'에서 근무하지 않는 사원
SELECT LOCATION_ID 
  FROM LOCATIONS
 WHERE CITY NOT LIKE 'Seattle'
;

SELECT E.FIRST_NAME 
     , E.LAST_NAME 
     , E.SALARY 
     , D.DEPARTMENT_NAME 
     , L.CITY 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.CITY != 'Seattle'
;

-- 54. 근무중인 사원이 가장 많은 도시와 사원의 수를 조회한다. 
-- 근무중인 사원 가장 많은 부서
SELECT DEPARTMENT_ID
     , COUNT(EMPLOYEE_ID) AS CNT
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
 ORDER BY CNT DESC
;

-- 사원이가 가장 많은 부서의 지역번호 추출
SELECT D.LOCATION_ID
     , COUNT(Q.DEPARTMENT_ID) AS CNT2
  FROM DEPARTMENTS D
 INNER JOIN (SELECT DEPARTMENT_ID
                  , COUNT(EMPLOYEE_ID) AS CNT
               FROM EMPLOYEES
              GROUP BY DEPARTMENT_ID
              ORDER BY CNT DESC) Q
    ON D.DEPARTMENT_ID = Q.DEPARTMENT_ID 
  GROUP BY D.LOCATION_ID
  ORDER BY CNT2 DESC
 ;

-- 결과
SELECT L.CLTY
     , W.CNT
 FROM LOCATIONS L 
INNER JOIN (SELECT D.LOCATION_ID
                 , COUNT(Q.DEPARTMENT_ID) AS CNT2
                 , COUNT(Q.EMPLOYEE_ID) AS CNT
              FROM DEPARTMENTS D
             INNER JOIN (SELECT DEPARTMENT_ID
                              , COUNT(EMPLOYEE_ID) AS CNT
                           FROM EMPLOYEES
                          GROUP BY DEPARTMENT_ID
                          ORDER BY CNT DESC) Q
                ON D.DEPARTMENT_ID = Q.DEPARTMENT_ID 
              GROUP BY D.LOCATION_ID
              ORDER BY CNT2 DESC) W
    ON L.LOCATION_ID = W.LOCATION_ID 
;
-- 55. 근무중인 사원이 없는 도시를 조회한다.
-- 56. 월급이 7000 에서 12000 사이인 사원이 근무중인 도시를 조회한다.
-- 월급 7000 12000 사이 직원
SELECT EMPLOYEE_ID 
  FROM EMPLOYEES
 WHERE SALARY BETWEEN 7000 AND 12000
;
-- 근무중인 부서번호
SELECT DEPARTMENT_ID 
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID 
  						FROM EMPLOYEES
 					   WHERE SALARY BETWEEN 7000 AND 12000)
;
-- 근무중인 부서번호의 지역 번호 조회
SELECT LOCATION_ID
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID 
                         FROM EMPLOYEES
                        WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID 
  						                        FROM EMPLOYEES
 					                            WHERE SALARY BETWEEN 7000 AND 12000))
;
-- 도시 조회
SELECT CITY
  FROM LOCATIONS
 WHERE LOCATION_ID IN (SELECT LOCATION_ID
                          FROM DEPARTMENTS
                         WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID 
                                                   FROM EMPLOYEES
                                                  WHERE SALARY BETWEEN 7000 AND 12000))
;
-- 57. 'Seattle' 에서 근무중인 사원의 직무명을 중복없이 조회한다.
-- 'Seattle' 지역번호
SELECT LOCATION_ID
  FROM LOCATIONS
 WHERE CITY = 'Seattle'
;
-- 지역번호가 일치하는 부서번호
SELECT DEPARTMENT_ID 
  FROM DEPARTMENTS
 WHERE LOCATION_ID = (SELECT LOCATION_ID
                        FROM LOCATIONS
                       WHERE CITY = 'Seattle')
;
-- 부서번호가 일치하는 사원번호
SELECT JOB_ID  
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID 
                          FROM DEPARTMENTS
                         WHERE LOCATION_ID = (SELECT LOCATION_ID
                                                FROM LOCATIONS
                                               WHERE CITY = 'Seattle'))
;
-- 사원 번호가 일치하는 직무명
SELECT JOB_TITLE
  FROM JOBS
 WHERE JOB_ID IN (SELECT JOB_ID  
                    FROM EMPLOYEES
                    WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID 
                                              FROM DEPARTMENTS
                                             WHERE LOCATION_ID = (SELECT LOCATION_ID
                                                                    FROM LOCATIONS
                                                                    WHERE CITY = 'Seattle')))
;
-- 58. 사내의 최고월급과 최저월급의 차이를 조회한다.
SELECT MAX(SALARY) - MIN(SALARY)
  FROM EMPLOYEES
;
-- 59. 이름이 'Renske' 인 사원의 월급과 같은 월급을 받는 사원의 모든 정보를 조회한다. 단, 'Renske' 사원은 조회에서 제외한다.
-- 이름은 'Renske' 인 사원의 월급
SELECT SALARY 
  FROM EMPLOYEES
 WHERE FIRST_NAME = 'Renske'
;
-- 'Renske' 사원 월급과 같은 월급 받는 사원 모든 정보 조회
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE SALARY IN (SELECT SALARY 
                    FROM EMPLOYEES
                   WHERE FIRST_NAME = 'Renske')
   AND FIRST_NAME != 'Renske'
;
-- 60. 회사 전체의 평균 월급보다 많이 받는 사원들 중 이름에 'u' 가 포함된 사원과 동일한 부서에서 근무중인 사원들의 모든 정보를 조회한다.
-- 회사 전체 평균 월급
SELECT AVG(SALARY)
  FROM EMPLOYEES
;
-- 이름에 'u'가 포함된 사원 동일한 부서
SELECT DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE FIRST_NAME LIKE '%u%'
   AND SALARY > (SELECT AVG(SALARY)
                   FROM EMPLOYEES)
;
-- 근무중인 사원들의 모든정보
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                           FROM EMPLOYEES
                          WHERE SALARY > (SELECT AVG(SALARY)
                                            FROM EMPLOYEES)
                            AND FIRST_NAME LIKE '%u%')
;
-- 61. 부서가 없는 국가명을 조회한다.
-- 부서가 없는 지역
SELECT LOCATION_ID
  FROM DEPARTMENTS
 WHERE LOCATION_ID IS NOT NULL
;

-- 부서가 없는 지역2
SELECT COUNTRY_ID
  FROM LOCATIONS
 WHERE LOCATION_ID IN (SELECT LOCATION_ID
                             FROM DEPARTMENTS
                            WHERE LOCATION_ID IS NOT NULL)
;
-- 부서가 없는 국가명
SELECT COUNTRY_NAME 
  FROM COUNTRIES
 WHERE COUNTRY_ID NOT IN (SELECT COUNTRY_ID
                        FROM LOCATIONS
                       WHERE LOCATION_ID IN (SELECT LOCATION_ID
                                               FROM DEPARTMENTS
                                              WHERE LOCATION_ID IS NOT NULL))
;

-- 62. 'Europe' 에서 근무중인 사원들의 모든 정보를 조회한다.
--'Europe'의 국가번호
SELECT REGION_ID
  FROM REGIONS
 WHERE REGION_NAME LIKE 'Europe'
;
-- 유럽의 국가 번호
SELECT COUNTRY_ID
  FROM COUNTRIES
 WHERE REGION_ID IN (SELECT REGION_ID
                       FROM REGIONS
                      WHERE REGION_NAME LIKE 'Europe')
;
-- 유럽 지역 번호
SELECT LOCATION_ID
  FROM LOCATIONS
 WHERE COUNTRY_ID IN (SELECT COUNTRY_ID
                        FROM COUNTRIES
                       WHERE REGION_ID IN (SELECT REGION_ID
                                             FROM REGIONS
                                            WHERE REGION_NAME LIKE 'Europe'))
;

-- 유럽에서 근무중인 부서 번호
SELECT DEPARTMENT_ID
  FROM DEPARTMENTS
 WHERE LOCATION_ID IN (SELECT LOCATION_ID
                         FROM LOCATIONS
                        WHERE COUNTRY_ID IN (SELECT COUNTRY_ID
                                               FROM COUNTRIES
                                              WHERE REGION_ID IN (SELECT REGION_ID
                                                                    FROM REGIONS
                                                                   WHERE REGION_NAME LIKE 'Europe')))
;

-- 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                           FROM DEPARTMENTS
                          WHERE LOCATION_ID IN (SELECT LOCATION_ID
                                                  FROM LOCATIONS
                                                 WHERE COUNTRY_ID IN (SELECT COUNTRY_ID
                                                                        FROM COUNTRIES
                                                                       WHERE REGION_ID IN (SELECT REGION_ID
                                                                                             FROM REGIONS
                                                                                            WHERE REGION_NAME LIKE 'Europe'))))
;

-- 63. 'Europe' 에서 가장 많은 사원들이 있는 부서명을 조회한다.

-- 64. 대륙 별 사원의 수를 조회한다.
SELECT R.REGION_NAME
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 INNER JOIN COUNTRIES C
    ON L.COUNTRY_ID = C.COUNTRY_ID 
 INNER JOIN REGIONS R
    ON C.REGION_ID = R.REGION_ID 
 GROUP BY R.REGION_NAME
;
-- 65. 월급이 2500, 3500, 7000 이 아니며 직업이 SA_REP 이나 ST_CLERK 인 사원들의 월급과 직무아이디를 조회한다.
-- 월급이 2500, 3500, 7000이 아닌 사원들의 사원 번호와 월급을 조회한다,
SELECT EMPLOYEE_ID 
     , SALARY
  FROM EMPLOYEES
 WHERE SALARY NOT IN (2500,3500,7000)
   AND JOB_ID IN ('SA_REP','ST_CLERK')
;
-- 직무 아이디가 SA_REP이나 ST_CLERK인 사원들의 사원번호와 직무 아이디를 조회한다.
SELECT EMPLOYEE_ID 
     , JOB_ID
  FROM EMPLOYEES
 WHERE JOB_ID IN ('SA_REP','ST_CLERK')
;

SELECT JOB_ID
     , SALARY
  FROM EMPLOYEES
 WHERE SALARY != 2500
   AND SALARY != 3500
   AND SALARY != 7000
   AND (JOB_ID = 'SA_REP'
    OR JOB_ID = 'ST_CLERK')
;
-- 66. 사원의 사원번호, 이름, 성, 상사의 사원번호, 상사의 이름, 상사의 성을 조회한다.
SELECT E.EMPLOYEE_ID 
     , E.FIRST_NAME 
     , E.LAST_NAME 
     , M.EMPLOYEE_ID 
     , M.FIRST_NAME 
     , M.LAST_NAME 
  FROM EMPLOYEES E
 INNER JOIN EMPLOYEES M
    ON E.EMPLOYEE_ID = M.MANAGER_ID 
;
-- 67. 101번 사원의 모든 부하직원 들의 이름, 성, 상사사원번호, 상사사원명을 조회한다.
 SELECT LEVEL
      , EMP.EMPLOYEE_ID
      , EMP.FIRST_NAME
      , EMP.LAST_NAME
      , EMP.MANAGER_ID
      , MAN.FIRST_NAME
      , MAN.LAST_NAME
   FROM EMPLOYEES EMP
  INNER JOIN EMPLOYEES MAN
     ON EMP.MANAGER_ID = MAN.EMPLOYEE_ID
  START WITH EMP.EMPLOYEE_ID = 101
CONNECT BY PRIOR EMP.EMPLOYEE_ID = EMP.MANAGER_ID
;

-- 68. 114번 직원의 모든 상사들의 이름, 성, 사원번호를 조회한다.
 SELECT *
   FROM EMPLOYEES E
  START WITH E.EMPLOYEE_ID = 114
CONNECT BY PRIOR E.EMPLOYEE_ID = E.MANAGER_ID 
;

-- 69. 114번 직원의 모든 상사들의 이름, 성, 사원번호를 조회한다. 단, 역순으로 조회한다.
 SELECT LEVEL
      , FIRST_NAME
      , LAST_NAME
      , EMPLOYEE_ID
   FROM EMPLOYEES
  START WITH EMPLOYEE_ID = 114
CONNECT BY PRIOR MANAGER_ID = EMPLOYEE_ID
  ORDER BY LEVEL DESC
;

-- 70. 모든 사원(EMPLOYES)들의 월급 오름차순 정렬하여 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 ORDER BY SALARY ASC
;
-- 71. 모든 사원들을 이름 내림차순 정렬하여 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 ORDER BY LAST_NAME DESC
;
-- 72. 모든 사원들의 이름, 성, 월급, 부서명을 부서번호로 내림차순 정렬하여 조회한다.
SELECT LAST_NAME
     , FIRST_NAME
     , SALARY
     , DEPARTMENT_NAME
  FROM EMPLOYEES
 INNER JOIN DEPARTMENTS
    ON EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID
 ORDER BY DEPARTMENT.DEPARTMENT_ID DESC
;
-- 73. 부서명별 월급의 합을 내림차순 정렬하여 조회한다.
SELECT D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
 ORDER BY SUM(E.SALARY) ASC
;
-- 74. 직무명별 사원의 수를 오름차순 정렬하여 조회한다.
SELECT J.JOB_TITLE 
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID
 GROUP BY J.JOB_TITLE
 ORDER BY J.JOB_TITLE DESC
;
-- 75. 모든 사원들의 모든 정보를 조회한다. 단, 인센티브를 받는 사원은 "인센티브여부" 컬럼에 "Y"를, 아닌 경우 "N"으로 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
	 , CASE -- 값 동등/크다/작다/다르다 등의 비교 가능
	   	 WHEN COMMISSION_PCT IS NULL THEN 'N'
	     ELSE 'Y'
	 END AS 인센티브여부
	 , CASE NVL(COMMISSION_PCT,0) -- 값 동등 비교만 할 수 있다. 같냐만 가능
	 	 WHEN 0 THEN 'N'
	 	 ELSE 'Y'
	 END AS 인센티브여부2
  FROM EMPLOYEES
;
-- 76. 모든 사원들의 이름을 10자리로 맞추어 조회한다.
SELECT LPAD(FIRST_NAME, 10, 'P') AS CO
  FROM EMPLOYEES
;

-- 77. 2007년에 직무가 변경된 사원들의 현재 직무명, 부서명, 사원번호, 이름, 성을 조회한다.
-- SUBQUERY & JOIN
-- 2007년에 직무가 변경된 사원번호
SELECT EMPLOYEE_ID
  FROM JOB_HISTORY
 WHERE TO_CHAR(START_DATE,'YYYY') LIKE '2007' 
;

SELECT J.JOB_TITLE 
     , D.DEPARTMENT_NAME 
     , E.EMPLOYEE_ID 
     , E.FIRST_NAME 
     , E.LAST_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 WHERE E.EMPLOYEE_ID IN (SELECT EMPLOYEE_ID
                           FROM JOB_HISTORY
                          WHERE TO_CHAR(START_DATE,'YYYY') LIKE '2007')
;

-- 78. 직무별 최대월급보다 더 많은 월급을 받는 사원의 모든 정보를 조회한다.
-- 직무별 최대 월급
SELECT MAX_SALARY
  FROM JOBS
;

-- 최대 월급보다 더 많은 월급을 받는 사원 정보
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID 
  FROM EMPLOYEES e
 WHERE SALARY > (SELECT MAX_SALARY
                   FROM JOBS j
                  WHERE j.JOB_ID = e.JOB_ID)
;
-- 79. 사원들의 이름, 성, 입사연도만 조회한다.
SELECT FIRST_NAME 
     , LAST_NAME 
     , TO_CHAR(HIRE_DATE , 'YYYY')
  FROM EMPLOYEES
;

-- 80. 사원들의 이름, 성, 입사연도, 입사월을 조회한다.
SELECT FIRST_NAME 
     , LAST_NAME 
     , TO_CHAR(HIRE_DATE , 'YYYY-MM')
  FROM EMPLOYEES
;
-- 81. 100번 사원의 모든 부하직원을 계층조회한다. 단, LEVEL이 4인 사원은 제외한다.
 SELECT LEVEL
      , EMPLOYEE_ID
   FROM EMPLOYEES
  WHERE LEVEL != 4
  START WITH EMPLOYEE_ID = 100
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;
-- 82. 많은 월급을 받는 10명을 조회한다.
SELECT *
  FROM (SELECT EMPLOYEE_ID
             , FIRST_NAME
             , LAST_NAME
             , EMAIL
             , PHONE_NUMBER
             , HIRE_DATE
             , JOB_ID
             , SALARY
             , COMMISSION_PCT
             , MANAGER_ID
             , DEPARTMENT_ID
          FROM EMPLOYEES
         ORDER BY SALARY DESC)
 WHERE ROWNUM <= 10
;
SELECT EMPLOYEE_ID
  FROM EMPLOYEES
 ORDER BY SALARY DESC
;
-- 83. 가장 적은 월급을 받는 사원의 상사명, 부서명을 조회한다.
-- 가장 적은 월급
SELECT MIN(SALARY)
  FROM EMPLOYEES;

SELECT M.MANAGER_ID 
     , M.FIRST_NAME 
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN EMPLOYEES M
    ON M.EMPLOYEE_ID = E.MANAGER_ID 
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 WHERE E.SALARY = (SELECT MIN(SALARY)
                     FROM EMPLOYEES)
;


-- 84. 많은 월급을 받는 사원 중 11번 째 부터 20번째를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , SALARY
  FROM (SELECT ROWNUM AS R_NUM
             , EMPLOYEE_ID
             , FIRST_NAME
             , LAST_NAME
             , SALARY
          FROM (SELECT EMPLOYEE_ID
                     , FIRST_NAME
                     , LAST_NAME
                     , SALARY
                  FROM EMPLOYEES
                 ORDER BY SALARY DESC)
          WHERE ROWNUM <= 20)
   WHERE R_NUM >= 11
;  
-- 85. 가장 적은 월급을 받는 중 90번 째 부터 100번째를 조회한다.
-- 86. 'PU_CLERK' 직무인 2번째 부터 5번째 사원의 부서명, 직무명을 조회한다.
-- 87. 모든 사원의 정보를 직무 오름차순, 월급 내림차순으로 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 ORDER BY JOB_ID ASC 
     , SALARY DESC
 ;
-- 88. 직무아이디별 평균월급을 평균월급순으로 오름차순 정렬하여 조회한다.
-- 직무아이디, 평균 월급
SELECT JOB_ID
	 , AVG(SALARY) AS AVG_SALARY
  FROM EMPLOYEES
 GROUP BY JOB_ID 
 ORDER BY AVG_SALARY ASC
;

-- 89. 부서별 평균월급을 내림차순 정렬하여 조회한다.
SELECT DEPARTMENT_ID
     , AVG(SALARY) AS AVG_SALARY 
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
 ORDER BY AVG_SALARY DESC
;

-- 90. 이름의 첫 번째 글자별 평균월급을 조회한다.
SELECT SUBSTR(FIRST_NAME, 1, 1)
     , AVG(SALARY)
  FROM EMPLOYEES
 GROUP BY SUBSTR(FIRST_NAME,1,1) 
 ORDER BY SUBSTR(FIRST_NAME,1,1) ASC
;

SELECT LETTER
     , AVG(SALARY)
  FROM (SELECT SUBSTR(FIRST_NAME,1,1) AS LETTER
             , SALARY
          FROM EMPLOYEES)
 GROUP BY LETTER
 ORDER BY LETTER ASC
;

-- 91. 입사연도별 최소월급을 조회한다.
SELECT YEAR
     , MIN(SALARY)
  FROM (SELECT TO_CHAR(HIRE_DATE,'YYYY') AS YEAR
             , SALARY
          FROM EMPLOYEES)
  GROUP BY YEAR
  ORDER BY YEAR ASC
;

-- 92. 월별 최대월급 중 2번째 부터 4번째 데이터만 조회한다.
SELECT HIRE_MONTH 
     , MAX_SALARY 
  FROM (SELECT HIRE_MONTH 
             , MAX_SALARY
             , ROWNUM AS R_NUM
          FROM (SELECT HIRE_MONTH
                     , MAX(SALARY) AS MAX_SALARY
                  FROM (SELECT TO_CHAR(HIRE_DATE, 'MM') AS HIRE_MONTH
                             , SALARY
                          FROM EMPLOYEES)
                 GROUP BY HIRE_MONTH 
                 ORDER BY MAX_SALARY DESC)
          WHERE ROWNUM <= 4)
  WHERE R_NUM >= 2
;

-- 93. 직무명별 최소월급을 조회한다.
SELECT J.JOB_TITLE 
     , MIN(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 GROUP BY J.JOB_TITLE 
;
-- 94. 부서명별 최대월급을 조회한다.
SELECT D.DEPARTMENT_NAME 
     , MAX(E.SALARY) 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
;


-- 95. 직무명, 부서명 별 사원 수, 평균월급을 조회한다.
SELECT J.JOB_TITLE 
     , D.DEPARTMENT_NAME 
     , COUNT(E.EMPLOYEE_ID)
     , AVG(E.SALARY)
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 INNER JOIN DEPARTMENTS D
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
     , J.JOB_TITLE 
;


-- 96. 도시별 사원 수를 조회한다.
SELECT L.CITY 
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 GROUP BY L.CITY 
;
-- 97. 국가별 사원 수, 최대월급, 최소월급을 조회한다.
SELECT C.COUNTRY_NAME 
     , COUNT(E.EMPLOYEE_ID )
     , MAX(E.SALARY )
     , MIN(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 INNER JOIN COUNTRIES C
    ON L.COUNTRY_ID  = C.COUNTRY_ID      
 GROUP BY C.COUNTRY_NAME 
;

-- 98. 대륙별 사원 수를 대륙명으로 오름차순 정렬하여 조회한다.
SELECT R.REGION_NAME
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 INNER JOIN COUNTRIES C
    ON L.COUNTRY_ID  = C.COUNTRY_ID
 INNER JOIN REGIONS R
    ON C.REGION_ID = R.REGION_ID 
 GROUP BY R.REGION_NAME
 ORDER BY R.REGION_NAME ASC
;

-- 99. 이름이나 성에 'A' 혹은 'a' 가 포함된 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE LAST_NAME LIKE '%A%'
    OR LAST_NAME LIKE '%a%'
    OR FIRST_NAME LIKE '%A%'
    OR FIRST_NAME LIKE '%a%'
;
-- 100. 국가별로 월급이 5000 이상인 사원의 수를 조회한다.
SELECT C.COUNTRY_NAME 
     , COUNT(E.EMPLOYEE_ID )
     , MAX(E.SALARY )
     , MIN(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 INNER JOIN COUNTRIES C
    ON L.COUNTRY_ID  = C.COUNTRY_ID 
 WHERE E.SALARY >= 5000
 GROUP BY C.COUNTRY_NAME 
;
-- 101. 인센티브를 안받는 사원이 근무하는 도시를 조회한다.
-- 102. 인센티브를 포함한 월급이 10000 이상인 사원의 모든 정보를 조회한다.
SELECT *
  FROM (SELECT EMPLOYEE_ID
             , FIRST_NAME
             , LAST_NAME
             , EMAIL
             , PHONE_NUMBER
             , HIRE_DATE
             , JOB_ID
             , SALARY
             , COMMISSION_PCT
             , MANAGER_ID
             , DEPARTMENT_ID
             , SALARY + SALARY * NVL(COMMISSION_PCT,0) AS TOTAL_SALARY
          FROM EMPLOYEES)
  WHERE TOTAL_SALARY >= 10000
;
-- 103. 가장 많은 부서가 있는 도시를 조회한다.
-- 104. 가장 많은 사원이 있는 부서의 국가명을 조회한다.
-- 105. 우편번호가 5자리인 도시에서 근무하는 사원명, 부서명, 도시명, 우편번호를 조회한다.
SELECT E.FIRST_NAME 
     , D.DEPARTMENT_NAME 
     , L.CITY 
     , L.POSTAL_CODE 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.POSTAL_CODE LIKE '_____'
;
-- 106. 우편번호에 공백이 없는 도시에서 근무하는 사원의 이름, 부서명, 우편번호를 조회한다.
SELECT E.FIRST_NAME 
     , D.DEPARTMENT_NAME 
     , L.CITY 
     , L.POSTAL_CODE 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.POSTAL_CODE NOT LIKE '% %'
;
-- 107. "주"가 없는 도시에서 근무하는 사원의 이름, 도시를 조회한다.
SELECT E.FIRST_NAME 
     , L.CITY 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.STATE_PROVINCE IS NULL
;

-- 108. 국가명이 6자리인 국가의 모든 정보를 조회한다.
SELECT COUNTRY_NAME
  FROM COUNTRIES
 WHERE COUNTRY_NAME LIKE '______'
;
-- 109. 사원의 이름과 성을 이용해 EMAIL과 같은 값으로 만들어 조회한다.
SELECT UPPER(LPAD(FIRST_NAME,1,1) || SUBSTR(REPLACE(LAST_NAME, ' '), 1, 7)) AS EMAIL
  FROM EMPLOYEES
;

-- 110. 모든 사원들의 이름을 10자리로 변환해 조회한다. 예> 이름 => " 이름"
SELECT LPAD(FIRST_NAME,10,'D')
  FROM EMPLOYEES
;
-- 111. 모든 사원들의 성을 10자리로 변환해 조회한다. 예> 성 => "성 "
SELECT RPAD(LAST_NAME,10,'R')
  FROM EMPLOYEES
;
-- 112. 109번 사원의 입사일 부터 1년 내에 입사한 사원의 모든 정보를 조회한다.
-- 109번 사원의 입사일
SELECT HIRE_DATE
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID = 109
;
-- 기준 1년 입사 사원 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN (SELECT HIRE_DATE 
                            FROM EMPLOYEES
                            WHERE EMPLOYEE_ID = 109)
                             AND (SELECT ADD_MONTHS(HIRE_DATE, 12) 
                                                   FROM EMPLOYEES
                                                  WHERE EMPLOYEE_ID = 109)
;

-- 113. 가장 먼저 입사한 사원의 입사일로부터 2년 내에 입사한사원의 모든 정보를 조회한다.
-- 가장 먼저 입사한 사원
SELECT MIN(HIRE_DATE)
  FROM EMPLOYEES
;
-- 가장 먼저 입사한 사원의 2년 이내
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN (SELECT MIN(HIRE_DATE) 
                            FROM EMPLOYEES)
                             AND (SELECT ADD_MONTHS(MIN(HIRE_DATE), 12 * 2) 
                                                   FROM EMPLOYEES)
;
-- 114. 가장 늦게 입사한 사원의 입사일 보다 1년 앞서 입사한 사원의 모든 정보를 조회한다.
-- 가장 늦게 입사한 사원
SELECT MAX(HIRE_DATE)
  FROM EMPLOYEES
;
-- 가장 먼저 입사한 사원의 2년 이내
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN ( SELECT ADD_MONTHS(MAX(HIRE_DATE), - 12)
                             FROM EMPLOYEES)
                              AND (SELECT MAX(HIRE_DATE) 
                                     FROM EMPLOYEES)
;
-- 115. 도시명에 띄어쓰기 " " 가 포함된 도시에서 근무중인 사원들의 부서명, 도시명, 사원명을 조회한다.
SELECT D.DEPARTMENT_NAME 
     , L.CITY 
     , E.FIRST_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
 WHERE L.CITY LIKE '% %'
;
-- 116. MOD 함수를 통해 사원번호가 1 남자, 0 여자 로 구분해 조회한다. MOD(값, 나눌값)
SELECT EMPLOYEE_ID
     , MOD(EMPLOYEE_ID,2)
     , CASE MOD(EMPLOYEE_ID,2)
     	 WHEN 0 THEN '여자'
     	 ELSE '남자'
     END AS 성별
  FROM EMPLOYEES
;
-- 117. '20230222' 문자 데이터를 날짜로 변환해 조회한다.(DUAL)
SELECT TO_DATE('20230222', 'YYYYMMDD')
  FROM DUAL
;
-- 118. '20230222' 문자 데이터를 'YYYY-MM' 으로 변환해 조회한다.(DUAL)
SELECT TO_CHAR(TO_DATE('20230222', 'YYYYMMDD'),'YYYY-MM')
  FROM DUAL
;
-- 119. '20230222130140' 문자 데이터를 'YYYY-MM-DD HH24:MI:SS' 으로 변환해 조회한다. (DUAL)
SELECT TO_CHAR(TO_DATE('20230222130140', 'YYYYMMDDHH24MISS'),'YYYY-MM-DD HH24:MI:SS')
  FROM DUAL
;
-- 120. '20230222' 날짜의 열흘 후의 날짜를 'YYYY-MM-DD' 으로 변환해 조회한다. (DUAL)
SELECT TO_CHAR(TO_DATE('20230222', 'YYYYMMDD') + 10,'YYYY-MM-DD')
  FROM DUAL
;

-- 121. 사원 이름의 글자(LENGTH)수 별 사원의 수를 조회한다.
SELECT COUNT(NAME_LANG)
  FROM (SELECT LENGTH(FIRST_NAME) AS NAME_LANG
          FROM EMPLOYEES)
  GROUP BY NAME_LANG
  ORDER BY NAME_LANG ASC
  ;

-- 122. 사원 성의 글자수 별 사원의 수를 조회한다.
SELECT COUNT(NAME_LANG)
  FROM (SELECT LENGTH(LAST_NAME) AS NAME_LANG
          FROM EMPLOYEES)
  GROUP BY NAME_LANG
  ORDER BY NAME_LANG ASC
;


-- 123. 사원의 월급이 5000 이하이면 "사원", 7000 이하이면 "대리", 9000 이하이면 "과장", 그 외에는 임원 으로 조회한다.
SELECT EMPLOYEE_ID
     , SALARY
     , CASE 
     	 WHEN SALARY <= 5000 THEN '사원'
     	 WHEN SALARY <= 7000 THEN '대리'
     	 WHEN SALARY <= 9000 THEN '과장'
     	 ELSE '임원'
     END
  FROM EMPLOYEES
;
  
-- 124. 부서별 사원의 수를 조인을 이용해 다음과 같이 조회한다."부서명 (사원의 수)"
SELECT D.DEPARTMENT_NAME || '(' || COUNT(E.EMPLOYEE_ID) || ')'
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D 
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
 GROUP BY D.DEPARTMENT_NAME
;
-- 125. 부서별 사원의 수를 스칼라쿼리를 이용해 다음과 같이 조회한다. "부서명 (사원의 수)"
SELECT D.DEPARTMENT_NAME
     , D.DEPARTMENT_ID
     , D.DEPARTMENT_NAME || ' (' || (SELECT COUNT(EMPLOYEE_ID)
                                      FROM EMPLOYEES E
                                     WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID) || ') '
  FROM DEPARTMENTS D
;
-- 126. 사원의 정보를 다음과 같이 조회한다. "사원번호 번 사원의 이름은 성이름 입니다."
SELECT EMPLOYEE_ID || '번 사원의 이름은' || LAST_NAME || ' ' || FIRST_NAME || ' 입니다.'
  FROM EMPLOYEES;

-- 127. 사원의 정보를 스칼라쿼리를 이용해 다음과 같이 조회한다. "사원번호 번 사원의 상사명은 상사명 입니다."
SELECT E.EMPLOYEE_ID || '번 사원의 상사명은 ' || (SELECT M.FIRST_NAME
                                               FROM EMPLOYEES M
                                              WHERE E.MANAGER_ID = M.EMPLOYEE_ID) || ' 입니다.' 
  FROM EMPLOYEES E
;
-- 128. 사원의 정보를 조인을 이용해 다음고 같이 조회한다. "사원명 (직무명)"
SELECT E.LAST_NAME || ' (' || J.JOB_TITLE || ')'
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID
;
-- 129. 사원의 정보를 스칼라쿼리를 이용해 다음과 같이 조회한다. "사원명 (직무명)"
SELECT E.FIRST_NAME || '(' || (SELECT J.JOB_TITLE
                                 FROM JOBS J
                                WHERE J.JOB_ID = E.JOB_ID) || ')'
  FROM EMPLOYEES E
;
-- 130. 부서별 월급 차이(최고월급 - 최저월급)가 가장 큰 부서명을 조회한다.
-- 131. 부서별 월급 차이(최고월급 - 최저월급)가 가장 큰 부서에서 근무하는 사원들의 직무명을 중복없이 조회한다.
-- 132. 부서장이 없는 부서명 중 첫 글자가 'C' 로 시작하는 부서명을 조회한다.
SELECT DEPARTMENT_NAME
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NULL 
   AND DEPARTMENT_NAME LIKE 'C%'
;
-- 133. 부서장이 있는 부서명 중 첫 글자가 'S' 로 시작하는 부서에서 근무중인 사원의 이름과 직무명, 부서명을 조회한다.
SELECT E.FIRST_NAME 
     , J.JOB_TITLE 
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 WHERE D.DEPARTMENT_ID IS NOT NULL
   AND D.DEPARTMENT_NAME LIKE 'S%'
;   
-- 134. 지역변호가 1000 ~ 1999 사이인 지역내 부서의 모든 정보를 조회한다.
SELECT DEPARTMENT_ID
     , DEPARTMENT_NAME
     , MANAGER_ID
     , LOCATION_ID
  FROM DEPARTMENTS
  WHERE LOCATION_ID BETWEEN 1000 AND 1999
;
-- 135. 90 또는 60 또는 100번 부서에서 근무중인 사원의 이름, 성, 부서명을 조회한다.
SELECT E.FIRST_NAME 
     , E.LAST_NAME 
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID  = D.DEPARTMENT_ID 
 WHERE D.DEPARTMENT_ID IN (90, 60, 100)
;
-- 136. 부서명이 5글자 미만인 부서에서 근무중인 사원의 이름, 부서명을 조회한다.
SELECT E.FIRST_NAME
     , D.DEPARTMENT_NAME
  FROM EMPLOYEES E
 INNER JOIN (SELECT LENGTH(DEPARTMENT_NAME) AS LEN_NAME
  				  , DEPARTMENT_ID
  				  , DEPARTMENT_NAME
 			   FROM DEPARTMENTS) D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
 WHERE LEN_NAME < 5
;

-- 137. 국가 아이디가 'C'로 시작하는 국가의 지역을 모두 조회한다.
SELECT COUNTRY_ID
     , COUNTRY_NAME
     , REGION_ID
  FROM COUNTRIES
 WHERE COUNTRY_ID LIKE 'C%'
;

-- 국가명이 'a'로 끝나는 국가의 모든 정보를 조회한다
SELECT COUNTRY_ID
     , COUNTRY_NAME
     , REGION_ID
  FROM COUNTRIES
 WHERE COUNTRY_NAME LIKE '%a'
;

-- 국가명이 'a'로 끝나지 않는 국가의 모든 정보를 조회한다
SELECT COUNTRY_ID
     , COUNTRY_NAME
     , REGION_ID
  FROM COUNTRIES
 WHERE COUNTRY_NAME NOT LIKE '%a'
;

-- 138. 국가 아이디의 첫 글자와 국가명의 첫 글자가 다른 모든 국가를 조회한다.
SELECT COUNTRY_NAME
     , COUNTRY_ID
     , REGION_ID
  FROM (SELECT SUBSTR(COUNTRY_NAME,1,1) AS C_FIRST
             , SUBSTR(COUNTRY_ID,1,1) AS I_FIRST
             , COUNTRY_NAME
             , COUNTRY_ID
             , REGION_ID
          FROM COUNTRIES)
  WHERE C_FIRST != I_FIRST
;

-- 139. 사원 모든 정보 중 이메일만 모두 소문자로 변경하여 조회한다.
SELECT LOWER(EMAIL)
  FROM EMPLOYEES
;
-- 140. 사원의 월급을 TRUNC(소수점 버림) 함수를 사용해 100 단위는 버린채 다음과 같이 조회한다. 예> 3700 -> 3000, 12700 -> 12000
SELECT TRUNC(SALARY, -3)
  FROM EMPLOYEES
;

-- 141. 100 단위를 버린 사원의 월급 별 사원의 수를 조회한다.
SELECT CAL1 
     , COUNT(EMPLOYEE_ID )
  FROM (SELECT TRUNC(SALARY / 1000) * 1000 AS CAL1
             , EMPLOYEE_ID
  	      FROM EMPLOYEES)
 GROUP BY CAL1
;

-- 142. 현재 시간으로부터 20년 전 보다 일찍 입사한 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE HIRE_DATE < ADD_MONTHS(SYSDATE, -12 * 20)
;
-- 143. 부서번호별 현재 시간으로부터 15년 전 보다 일찍 입사한 사원의 수를 조회한다.
SELECT D.DEPARTMENT_ID 
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 WHERE E.HIRE_DATE < ADD_MONTHS(SYSDATE, - 12 * 15)
 GROUP BY D.DEPARTMENT_ID 
;
-- 144. 부서명, 직무명 별 평균 월급을 조회한다.
SELECT D.DEPARTMENT_NAME
     , J.JOB_TITLE 
     , AVG(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 GROUP BY D.DEPARTMENT_NAME 
     , J.JOB_TITLE 
;

-- 145. 도시명, 직무명 별 사원의 수를 조회한다.
SELECT L.CITY
     , J.JOB_TITLE 
     , COUNT(E.EMPLOYEE_ID)
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID     
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
 GROUP BY L.CITY  
     , J.JOB_TITLE 
;

-- 146. 부서명, 직무명 별 평균 월급 중 가장 작은 평균월급을 받는 부서명, 직무명을 조회한다.
-- 147. 102번 직원의 모든 부하직원의 수를 조회한다.
 SELECT COUNT(EMPLOYEE_ID)
   FROM EMPLOYEES
  WHERE LEVEL > 1
  START WITH EMPLOYEE_ID = 102
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
; 
   
-- 148. 113번 직원의 모든 부하직원의 수를 조회한다.
 SELECT COUNT(EMPLOYEE_ID)
   FROM EMPLOYEES
  WHERE LEVEL > 1
  START WITH EMPLOYEE_ID = 113
CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;
-- 149. 부하직원이 없는 사원의 모든 정보를 조회한다.
-- 부하직원이 없는 사원
SELECT DISTINCT NVL(MANAGER_ID,0) 
  FROM EMPLOYEES
;

SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID NOT IN (SELECT DISTINCT NVL(MANAGER_ID,0) 
                             FROM EMPLOYEES)
;

-- 150. 사원번호가 100번인 사원의 사원번호, 이름과 사원번호로 내림차순 정렬된 사원의 사원번호, 이름 조회한다.
/*조회 예
--------------------
100 Steven
206 William
205 Shelley
204 Hermann
203 Susan
202 Pat
201 Michael
200 Jennifer
199 Douglas
198 Donald
197 Kevin
196 Alana
...
*/
-- 100번 사원 조회
 SELECT EMPLOYEE_ID
      , FIRST_NAME
   FROM EMPLOYEES
  WHERE EMPLOYEE_ID = 100
  UNION ALL
-- 100번을 제외한 사원들을 사원번호 내림차순으로 조회
SELECT EMPLOYEE_ID
     , FIRST_NAME
  FROM (SELECT EMPLOYEE_ID
             , FIRST_NAME
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID != 100
         ORDER BY EMPLOYEE_ID DESC)
 ;

-- 151. 모든 사원들의 사원번호, 부서번호, 부서명을 조회한다. 근무중인 부서가 없다면 null로 표시한다.
SELECT E.EMPLOYEE_ID 
     , E.DEPARTMENT_ID 
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E 
  LEFT OUTER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 ORDER BY E.EMPLOYEE_ID ASC
;
-- 152. 부서에서 근무하는 모든 사원들의 이름, 부서번호, 부서명을 조회한다. 근무중인 사원이 없다면 null로 표시한다.
SELECT E.FIRST_NAME 
     , D.DEPARTMENT_ID 
     , D.DEPARTMENT_NAME 
  FROM DEPARTMENTS D
  LEFT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID 
;
-- 153. 모든 부서의 부서장들의 사원번호, 이름, 성, 부서명을 조회한다. 부서장이 없다면 null로 표시한다.
SELECT E.EMPLOYEE_ID 
     , E.FIRST_NAME 
     , E.LAST_NAME 
     , D.DEPARTMENT_NAME 
  FROM DEPARTMENTS D
  LEFT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID 
 ORDER BY E.EMPLOYEE_ID ASC
;
-- 154. 모든 지역에 존재하는 부서들의 도시명, 부서명을 조회한다. 부서가 없다면 null로 표시한다.
SELECT L.CITY 
     , D.DEPARTMENT_NAME 
  FROM LOCATIONS L
  LEFT OUTER JOIN DEPARTMENTS D
    ON L.LOCATION_ID = D.LOCATION_ID 
 ORDER BY L.CITY ASC

-- 155. 모든 국가에 존재하는 도시명, 부서명을 조회한다. 도시 또는 부서가 없다면 null로 표시한다.
 SELECT L.CITY 
      , D.DEPARTMENT_NAME 
   FROM COUNTRIES C
   LEFT OUTER JOIN LOCATIONS L
     ON C.COUNTRY_ID = L.COUNTRY_ID 
   LEFT OUTER JOIN DEPARTMENTS D
     ON L.LOCATION_ID  = D.LOCATION_ID 
  ORDER BY L.CITY ASC
;
-- 156. 모든 부서별로 근무하는 사원의 수를 조회한다. 근무중인 사원이 없다면 0으로 표시한다.
SELECT D.DEPARTMENT_NAME
     , COUNT(E.EMPLOYEE_ID)
  FROM DEPARTMENTS D 
  LEFT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
  GROUP BY D.DEPARTMENT_NAME
  ORDER BY D.DEPARTMENT_NAME ASC
;

-- 157. 모든 지역별로 근무하는 사원의 수를 조회한다. 근무중인 사원이 없다면 0으로 표시한다.
SELECT L.LOCATION_ID 
     , COUNT(E.EMPLOYEE_ID)
  FROM LOCATIONS L
  LEFT OUTER JOIN DEPARTMENTS D
    ON L.LOCATION_ID  = D.LOCATION_ID 
  LEFT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 GROUP BY L.LOCATION_ID 
 ORDER BY L.LOCATION_ID ASC
;

-- 158. 모든 국가에 존재하는 부서의 수를 조회한다. 부서가 없다면 0으로 표시한다.
SELECT C.COUNTRY_NAME
     , COUNT(D.DEPARTMENT_ID)
  FROM COUNTRIES C
  LEFT OUTER JOIN LOCATIONS L
    ON C.COUNTRY_ID = L.COUNTRY_ID 
  LEFT OUTER JOIN DEPARTMENTS D
    ON L.LOCATION_ID = D.LOCATION_ID
 GROUP BY C.COUNTRY_NAME
 ORDER BY C.COUNTRY_NAME ASC
;


---- 추가 문제

-- 1. (12건) 부서아이디별 사원의 평균연봉을 조회한다.
SELECT AVG(SALARY)
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID
;

-- 2. (19건) 직무아이디별 사원의 최고연봉을 조회한다.
SELECT MAX(SALARY)
  FROM EMPLOYEES
 GROUP BY JOB_ID
;

-- 3. (72건) 인센티브를 안받는 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NULL
;
-- 4. (2건) 인센티브를 받는 사원의 부서아이디를 중복없이 조회한다.
SELECT DISTINCT DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL
;

-- 5. (2건) 인센티브를 받는 사원의 직무아이디를 중복없이 조회한다.
SELECT DISTINCT JOB_ID
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL
;

-- 6. (7건) 사원이 있는 부서의 지역아이디를 조회한다. //DISTINCT 추가 해야함 중복값이 존재해서 11건 나옴
-- 사원이 있는 부서
SELECT DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID IS NOT NULL
;
-- 부서 지역아이디 조회
SELECT DISTINCT LOCATION_ID
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                           FROM EMPLOYEES
                          WHERE EMPLOYEE_ID IS NOT NULL)
;

-- 7. (21건) Seattle에 존재하는 부서번호를 조회한다.
-- 지역명이 Seattle인 지역번호 추출
SELECT LOCATION_ID
  FROM LOCATIONS
 WHERE CITY LIKE 'Seattle'
;

SELECT DEPARTMENT_ID
  FROM DEPARTMENTS
 WHERE LOCATION_ID IN (SELECT LOCATION_ID
                         FROM LOCATIONS
                        WHERE CITY LIKE 'Seattle')
;

-- 8. (16건) 사원이 한명도 없는 도시를 조회한다.
-- 사원이 없는 부서번호
SELECT DEPARTMENT_ID
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NULL
;
-- 도시 조회
SELECT LOCATION_ID
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                           FROM DEPARTMENTS
                          WHERE MANAGER_ID IS NULL)
;

-- 9. (7건) 사원이 한명이라도 있는 도시를 조회한다.
-- 사원이 있는 부서번호
SELECT DEPARTMENT_ID
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NOT NULL 
;
-- 도시 조회
SELECT DISTINCT LOCATION_ID
  FROM DEPARTMENTS
 WHERE DEPARTMENT_ID IN (SELECT DEPARTMENT_ID
                           FROM DEPARTMENTS
                          WHERE MANAGER_ID IS NOT NULL )
;

-- 10. (107건) 모든 사원의 정보를 연봉으로 오름차순 정렬하여 조회한다.
SELECT SALARY
  FROM EMPLOYEES
 ORDER BY SALARY ASC
;

-- 11. (107건) 모든 사원의 사원번호, 이름, 성, 연봉, 인센티브를 포함한 연봉 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , SALARY
     , COMMISSION_PCT
  FROM EMPLOYEES
;

-- 12. (6건) 2003년에 입사한 사원은 몇 명인지 조회한다.
SELECT COUNT(EMPLOYEE_ID)
  FROM EMPLOYEES
 WHERE TO_CHAR(HIRE_DATE, 'YYYY') LIKE '2003'
;
 
-- 13. (1건) 113번 사원의 상사의 모든 정보를 조회한다.
-- 113번 사원의 상사
SELECT MANAGER_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID = 113
;
-- 상사의 모든 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES 
 WHERE EMPLOYEE_ID = (SELECT MANAGER_ID
                       FROM EMPLOYEES 
                      WHERE EMPLOYEE_ID = 113)
;

-- 14. (11건) 모든 부서의 부서장의 모든 사원 정보를 조회한다.
-- 모든 부서, 부서장
SELECT MANAGER_ID
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NOT NULL 
;
-- 모든 사원 정보 출력
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID IN (SELECT MANAGER_ID
                         FROM DEPARTMENTS
                        WHERE MANAGER_ID IS NOT NULL )
;


-- 15. (23건) 사원의 이름이 7자리인 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE FIRST_NAME LIKE '_______'
;

-- 16. (25건) 사원의 이메일이 6자리인 사원의 모든 정보를 조회한다.
SELECT EMPLOYEE_ID
     , FIRST_NAME
     , LAST_NAME
     , EMAIL
     , PHONE_NUMBER
     , HIRE_DATE
     , JOB_ID
     , SALARY
     , COMMISSION_PCT
     , MANAGER_ID
     , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMAIL LIKE '______'
;

-- 1. 모든 부서들의 정보와 도시 정보를 조회한다.
SELECT D.DEPARTMENT_ID
     , D.DEPARTMENT_NAME
     , D.MANAGER_ID
     , D.LOCATION_ID
  FROM DEPARTMENTS D
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID 
;

-- 2. 모든 사원들의 정보와 부서명을 조회한다.
SELECT E.EMPLOYEE_ID
     , E.FIRST_NAME
     , E.LAST_NAME
     , E.EMAIL
     , E.PHONE_NUMBER
     , E.HIRE_DATE
     , E.JOB_ID
     , E.SALARY
     , E.COMMISSION_PCT
     , E.MANAGER_ID
     , E.DEPARTMENT_ID
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
;
-- 3. 111번 사원의 모든 정보와 부서명을 조회한다.
SELECT E.EMPLOYEE_ID
     , E.FIRST_NAME
     , E.LAST_NAME
     , E.EMAIL
     , E.PHONE_NUMBER
     , E.HIRE_DATE
     , E.JOB_ID
     , E.SALARY
     , E.COMMISSION_PCT
     , E.MANAGER_ID
     , E.DEPARTMENT_ID
     , D.DEPARTMENT_NAME 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 WHERE E.EMPLOYEE_ID = 111
;

-- 4. 115번의 사원의 모든 정보와 부서명, 직무명을 조회한다.
SELECT E.EMPLOYEE_ID
     , E.FIRST_NAME
     , E.LAST_NAME
     , E.EMAIL
     , E.PHONE_NUMBER
     , E.HIRE_DATE
     , E.JOB_ID
     , E.SALARY
     , E.COMMISSION_PCT
     , E.MANAGER_ID
     , E.DEPARTMENT_ID
     , D.DEPARTMENT_NAME 
     , J.JOB_ID 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 WHERE E.EMPLOYEE_ID = 115
;

-- 5. 100번 사원의 모든 정보와 부서명, 직무명, 도시명을 조회한다.
SELECT E.EMPLOYEE_ID
     , E.FIRST_NAME
     , E.LAST_NAME
     , E.EMAIL
     , E.PHONE_NUMBER
     , E.HIRE_DATE
     , E.JOB_ID
     , E.SALARY
     , E.COMMISSION_PCT
     , E.MANAGER_ID
     , E.DEPARTMENT_ID
     , D.DEPARTMENT_NAME 
     , J.JOB_ID 
     , L.CITY 
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
 WHERE E.EMPLOYEE_ID = 100
;

-- 6. 부서명별 사원의 수를 조회한다.
SELECT D.DEPARTMENT_NAME 
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
;
 
-- 7. 직무명별 사원의 평균월급을 조회한다.
SELECT J.JOB_TITLE 
     , AVG(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID
 GROUP BY J.JOB_TITLE 
;
 
-- 8. 부서명, 직무명별 사원의 수와 평균월급을 조회한다.
SELECT D.DEPARTMENT_NAME 
     , J.JOB_TITLE 
     , COUNT(E.EMPLOYEE_ID )
     , AVG(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN JOBS J
    ON E.JOB_ID = J.JOB_ID    
 GROUP BY D.DEPARTMENT_NAME 
     , J.JOB_TITLE 
;

-- 9. 부서명별 평균월급을 부서명으로 내림차순 정렬하여 조회한다.
SELECT D.DEPARTMENT_NAME 
     , AVG(E.SALARY ) AS AVG_SAL
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
 ORDER BY AVG_SAL DESC
;

-- 10. 부서명별 최고월급을 최고월급으로 오름차순 정렬하여 조회한다.
SELECT D.DEPARTMENT_NAME 
     , MAX(E.SALARY ) AS MAX_SAL
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_NAME 
 ORDER BY MAX_SAL ASC
;
-- 11. 도시명 별 사원의 수를 도시명으로 오름차순 정렬하여 조회한다.
SELECT L.CITY 
     , COUNT(E.EMPLOYEE_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
 GROUP BY L.CITY  
 ORDER BY L.CITY  ASC
;
-- 12. 도시별 부서의 수를 조회한다.
SELECT L.CITY 
     , COUNT(D.DEPARTMENT_ID )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
 GROUP BY L.CITY  
;
-- 13. 도시별 사원의 평균월급을 조회한다.
SELECT L.CITY 
     , AVG(E.SALARY )
  FROM EMPLOYEES E
 INNER JOIN DEPARTMENTS D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 INNER JOIN LOCATIONS L
    ON D.LOCATION_ID = L.LOCATION_ID
 GROUP BY L.CITY  
;

-- 1. 100번 사원의 모든 부하직원을 계층조회한다.
	SELECT LEVEL
	     , EMPLOYEE_ID
	     , FIRST_NAME
	     , MANAGER_ID
	     , FIRST_NAME
	  FROM EMPLOYEES
	 START WITH EMPLOYEE_ID = 100
   CONNECT BY PRIOR EMPLOYEE_ID = MANAGER_ID
;
-- 2. 113번 사원의 모든 상사를 계층조회한다.
	SELECT LEVEL
	     , EMPLOYEE_ID
	     , FIRST_NAME
	     , MANAGER_ID
	     , FIRST_NAME
	  FROM EMPLOYEES
	 START WITH EMPLOYEE_ID = 113
   CONNECT BY PRIOR MANAGER_ID = EMPLOYEE_ID
;
-- 3. IT 부서장의 모든 부하직원을 계층조회한다.
SELECT MANAGER_ID
  FROM DEPARTMENTS
 WHERE DEPARTMENT_NAME = 'IT'
;
-- 조인이 왜 필요 없었을까? == 인라인 뷰로 부서의 값만 조회하면 되니 불필요한 조인은 필요가 없다
 SELECT *
   FROM EMPLOYEES E
  START WITH E.EMPLOYEE_ID = (SELECT MANAGER_ID
                                FROM DEPARTMENTS
               				   WHERE DEPARTMENT_NAME = 'IT')
CONNECT BY PRIOR E.EMPLOYEE_ID = MANAGER_ID 
;
  
-- 4. 부서장들의 부하직원을 계층조회한다.
-- 부서장 조회
SELECT NVL(MANAGER_ID, 0) AS MN
  FROM DEPARTMENTS
;

-- 0을 거른다 IS NOT NULL ......
SELECT MANAGER_ID
  FROM (SELECT NVL(MANAGER_ID, 0) AS N_ID
          FROM DEPARTMENTS)
 WHERE N_ID > 0
;

 SELECT *
   FROM EMPLOYEES E
  START WITH E.EMPLOYEE_ID IN (SELECT MANAGER_ID
                                FROM DEPARTMENTS
                               WHERE MANAGER_ID IS NOT NULL)
CONNECT BY PRIOR E.EMPLOYEE_ID = MANAGER_ID
  ORDER SIBLINGS BY DEPARTMENT_ID ASC -- 계층 조회정렬 > DEPARTMENT_ID로 계층 정렬한다.
;

-- 5. 부서명이 가장 긴 부서에서 근무중인 사원의 모든 정보를 조회한다.
-- 부서명이 가장 긴 부서 아이디 
SELECT DEPARTMENT_ID
  FROM DEPARTMENTS
 ORDER BY LENGTH(DEPARTMENT_NAME) ASC
;

-- 1개만 자르기
SELECT DEPARTMENT_ID
  FROM (SELECT DEPARTMENT_ID
          FROM DEPARTMENTS
         ORDER BY LENGTH(DEPARTMENT_NAME) ASC)
  WHERE ROWNUM <= 1
;

-- 답안 수정
SELECT *
  FROM EMPLOYEES E
 INNER JOIN (SELECT DEPARTMENT_ID
               FROM (SELECT DEPARTMENT_ID
                          , LENGTH(DEPARTMENT_NAME) AS LEN
                       FROM DEPARTMENTS
                   ORDER BY LEN DESC)
               WHERE ROWNUM = 1       ) D
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID 
 WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
; 

-- 6. 2002년부터 2006년까지 입사한 사원은 몇 명인지 연도별로 조회한다.
-- 날짜 BETWEEN, COUNT(), GROUP_BY YYYY
SELECT EMPLOYEE_ID
     , HIRE_DATE
  FROM EMPLOYEES
 WHERE HIRE_DATE BETWEEN TO_DATE('2002', 'YYYY') AND TO_DATE('2006', 'YYYY')
; 

SELECT COUNT(EMPLOYEE_ID)
     , DATE_Y
  FROM (SELECT EMPLOYEE_ID
             , TO_CHAR(HIRE_DATE, 'YYYY') AS DATE_Y
          FROM EMPLOYEES
         WHERE HIRE_DATE BETWEEN TO_DATE('2002-01-01', 'YYYY-MM-DD') AND TO_DATE('2007-01-01', 'YYYY-MM-DD') -1)
 GROUP BY DATE_Y
 ORDER BY DATE_Y ASC
 ;

-- 7. 입사일이 가장 빠른 사원 5명의 이름과 입사일을 조회한다.
-- 입사일 정렬
SELECT EMPLOYEE_ID
     , HIRE_DATE
  FROM EMPLOYEES
 ORDER BY HIRE_DATE ASC
 ;

 SELECT FIRST_NAME
      , HIRE_DATE
   FROM (SELECT FIRST_NAME
			  , HIRE_DATE
		   FROM EMPLOYEES
	   ORDER BY HIRE_DATE ASC)
  WHERE ROWNUM < 6
 ;

-- 8. 커미션을 받는 사원들의 이름과 커미션을 조회한다. 단, 커미션이 가장 높은 사원 3명은 제외한다.
 -- 커미션 받는 사원
 SELECT FIRST_NAME
      , COMMISSION_PCT
   FROM EMPLOYEES
  WHERE COMMISSION_PCT IS NOT NULL
  ORDER BY COMMISSION_PCT DESC
;

SELECT FIRST_NAME
     , COMMISSION_PCT
     , ROWNUM AS RS
  FROM ( SELECT FIRST_NAME
		      , COMMISSION_PCT
		   FROM EMPLOYEES
		  WHERE COMMISSION_PCT IS NOT NULL
		  ORDER BY COMMISSION_PCT DESC)
;

SELECT  FIRST_NAME
     , COMMISSION_PCT 
  FROM (SELECT FIRST_NAME
		     , COMMISSION_PCT
		     , ROWNUM AS RS
		  FROM ( SELECT FIRST_NAME
				      , COMMISSION_PCT
				   FROM EMPLOYEES
				  WHERE COMMISSION_PCT IS NOT NULL
				  ORDER BY COMMISSION_PCT DESC))
 WHERE RS > 3
;

SELECT FIRST_NAME
     , COMMISSION_PCT 
  FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL
   AND COMMISSION_PCT NOT IN (SELECT COMMISSION_PCT 
                                FROM (SELECT DISTINCT COMMISSION_PCT
                                        FROM EMPLOYEES
                                       WHERE COMMISSION_PCT IS NOT NULL 
                                       ORDER BY COMMISSION_PCT DESC)
                                WHERE ROWNUM <= 3) 
 ORDER BY COMMISSION_PCT DESC
;

 

-- 1. 지역별 부서의 수를 조회한다. (부서가 없으면 부서의 수는 0으로 조회한다.)
SELECT L.CITY
     , COUNT(D.DEPARTMENT_ID)
  FROM LOCATIONS L
  LEFT OUTER JOIN DEPARTMENTS D
    ON D.LOCATION_ID = L.LOCATION_ID
 GROUP BY L.CITY
;
  
-- 2. 지역별 사원의 평균월급을 조회한다. (사원이 없으면 평균월급은 0으로 조회한다.)
SELECT L.CITY
     , NVL(AVG(E.SALARY),0)
  FROM LOCATIONS L
  LEFT OUTER JOIN DEPARTMENTS D
    ON D.LOCATION_ID = L.LOCATION_ID
  LEFT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 GROUP BY L.CITY
;

-- 3. 도시명 별 사원의 수를 도시명으로 오름차순 정렬하여 조회한다.(사원이 없으면 사원의 수는 0으로 조회한다.)
SELECT L.CITY
     , COUNT(E.EMPLOYEE_ID)
  FROM LOCATIONS L
  LEFT OUTER JOIN DEPARTMENTS D
    ON L.LOCATION_ID = D.LOCATION_ID
  LEFT OUTER JOIN EMPLOYEES E
    ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
  GROUP BY L.CITY
  ORDER BY L.CITY ASC
;

-- 4. 모든 사원들의 현재 직무명과 과거의 직무명을 조회한다. 만약 직무가 한번도 변경되지 않았다면, 과거의 직무명은 '없음' 으로 조회한다.
-- 모든 사원들의 현재 직무명과 과거의 직무명을 조회한다
SELECT A_J.JOB_TITLE
     , B_J.JOB_TITLE
     , CASE 
     	  WHEN B_J.JOB_TITLE IS NULL THEN '없음'
     	  ELSE B_J.JOB_TITLE
       END AS 결과
  FROM EMPLOYEES E
  LEFT OUTER JOIN JOBS A_J -- 현재 직무명
    ON E.JOB_ID = A_J.JOB_ID
  LEFT OUTER JOIN JOB_HISTORY JH
    ON E.EMPLOYEE_ID = JH.EMPLOYEE_ID
  LEFT OUTER JOIN JOBS B_J -- 과거 직무명
    ON JH.JOB_ID = B_J.JOB_ID
;