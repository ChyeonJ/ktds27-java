package com.ktdsuniversity.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.exceptions.handlers.AuthorizationDeniedExceptionHandler;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.security.authenticate.filters.JsonWebTokenAuthenticationFilter;
import com.ktdsuniversity.edu.security.authenticate.handlers.LoginFailureHandler;
import com.ktdsuniversity.edu.security.authenticate.handlers.LoginSuccessHandler;
import com.ktdsuniversity.edu.security.authenticate.oauth.HelloSpringOAuthService;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityUserDetailService;
import com.ktdsuniversity.edu.security.providers.JsonWebTokenAuthenticationProvider;
import com.ktdsuniversity.edu.security.providers.UserNameAndPasswordAuthenticationProvider;

// application.yml에서 작성할 수 없는 설정들을 적용하기 위한 Annotation
// @Component 의 자식 Annotation
@Configuration
// spring-boot-starter-validation 동작 활성화 시키기
// @EnableWebMvc가 추가되면 application.yml의 mvc 관련 설정들이 모두 무시된다.
//   1. spring.mvc.view.prefix, spring.mvc.view.suffix
//   2. src/main/resources/static 경로 사용 불가능.
@EnableWebMvc
//생략가능
// SpringSecurity 라이브러리를 활성화 시킨다.
// SpringSecurity의 필터 목록을 확인하기 위해서 작성한다.
@EnableWebSecurity(debug=true)
// 컨트롤러 혹은 서비스 코드에서 권한 검사를 수행하기 위한 애노테이션 추가
@EnableMethodSecurity
public class HelloSpringConfiguration implements 
		// WebMvc 설정을 위한 Configuration
		// @EnableWebMvc Annotation 에서 적용하는 기본 설정들을 변경하기 위함.
		WebMvcConfigurer {
	
	//autowirde가 있으면 반드시 빈 주입해라 인데, required false해도 클래스가 없어도 넣으라는 소리
	@Autowired(required=false)
	@Lazy //필요할 때 그떄 불러오겠다
	private MembersDao membersDao;
	
	// application.yml에서 관련된 정보를 가져옴
	@Value("${app.jwt.secret-key}") //환경설정 정보를 Bean으로 가져오는 방법. 괄호에 환경설정 경로를 작성
	private String jwtSecretKey;
	
	// @Componant가 적용된 클래스에서만 동작한다 /자식 관계도 됨
	@Value("${app.jwt.issuer}")
	private String jwtIssuer;
	
	//jwt
	@Bean
	JsonWebTokenAuthenticationProvider createJwtAuthenticationProvider() {
		return new JsonWebTokenAuthenticationProvider(this.jwtSecretKey, this.jwtIssuer);
	}
	
	
	// SecurityPasswordEncoder의 Bean을 생성
	// 객체를 반환시킨다
	@Bean // 메소드가 실행되어서 반환되는 객체를 Bean Container에 적재한다.
	PasswordEncoder createPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}
	
	// SecurityUserDetailService의 Bean을 생성한다.
	@Bean // @Bean으로 생성하는 객체(Bean)들은 필요한 의존 객체를 생성자로 주입해 주어야 한다.
	UserDetailsService createUserDetailService() {
		return new SecurityUserDetailService(this.membersDao);
	}
	
	// UserNameAndPasswordAuthenticationProvider의 Bean을 생성한다.   
	@Bean
	AuthenticationProvider createAuthenticationProvider(){
		
		// 무조건 첫번째 만들어진 객체만 돌려주기 때문에, 몇번이 돌아도 상관이 없다
		UserDetailsService userDetailsService = this.createUserDetailService();
		PasswordEncoder passwordEncoder = this.createPasswordEncoder();
		
		return new UserNameAndPasswordAuthenticationProvider(userDetailsService, passwordEncoder);
	}
	
	@Bean
	AuthenticationSuccessHandler createLoginSuccessHandler() {
		return new LoginSuccessHandler(this.membersDao);
	}
	
	@Bean
	AuthenticationFailureHandler createLoginFailureHandler() {
		return new LoginFailureHandler(this.membersDao);
	}
	
	/**
	 * 특정 URL에 대해서 Spring Security가 개입하지 않도록 설정
	 * /WEB-INF/views/ 아래의 모든 jsp 파일들은 Spring Security의 간섭을 받지 않는다
	 * 
	 * Controller에서 해당 페이지를 노출하려 할 떄 "/WEB-INF/views/.../*.jsp 경로 사용시
	 * 인증이 된 사용자에게만 노출 시키려 하는 경우가 존재 ==> Spring Security가 개입하지 않도록 설정
	 * @return
	 */
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
				.requestMatchers("/WEB-INF/views/**");
	}
	
	//JWT 필터
	@Bean
	OncePerRequestFilter createJwtAuthFilter() {
		return new JsonWebTokenAuthenticationFilter(
				this.createJwtAuthenticationProvider(),
				this.createUserDetailService());
	}
	
	@Bean
	OAuth2UserService<OAuth2UserRequest, OAuth2User> createOAuth2UserService(){
		return new HelloSpringOAuthService(this.membersDao);
	}
	
	// TODO Spring Login Filter(BasicAuthenticationFilter) 등록
	// Spring Security의 기본 로그인 절차를 수정하는 작업
	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) {
		
		httpSecurity.oauth2Login(oauth2 -> oauth2.loginPage("/login")
												 .defaultSuccessUrl("/")
												 .userInfoEndpoint(endpoint -> 
												 	endpoint.userService(this.createOAuth2UserService())));
		
		// 상대방이 내 서버로 접속할 수 있도록 허용하기
		// => 내 서버로 접속 가능한 안전한 URL 등록하기
		httpSecurity.cors(corsConfigurer -> {
			
			CorsConfigurationSource source = (HttpServletRequest) -> {
				// 허용할 타 사이트의 도메인을 작성.
				CorsConfiguration config = new CorsConfiguration();
				
				// 허용할 타 사이트의 URL
				// http://192.168.211.15:8080/ 에서 요청하는 모든 접근(API)들을 허용하겠다
				config.addAllowedOrigin("http://192.168.211.15:8080/");
				// 허용할 타 사이트의 Method
				// http://192.168.211.15:8080/ 에서 POST로 GET으로 요청되는 접근들만 하용하겠다.
				config.addAllowedMethod("POST");
				config.addAllowedMethod("GET");
				// PUT, DELETE <-- 허용X : react 배우면 하게됨
				
				// 허용할 타 사이트의 요청 HttpHeader
				// 모든 요청인 HttpHeader를 허용하겠다!
				// 예) Naver에서 요청 HttpHeader에 "X-HTTP-Request-Naver-Client-ID"를 추가할 것을 요구
				// *를 붙여주면 모든 Header를 허용하겠다 라는 소리다. csrf의 어떤종류든? 그런내용인듯
				config.addAllowedHeader("*");
				
				return config;
			};
			corsConfigurer.configurationSource(source);
		});
		
		
		// CSRF 수정, 댓글 등록불가 (Invalid CSRF token found for ...)
		// CSRF를 체크하는 SecurityFilter ==> (CsrfFilter)를 무효화
//		httpSecurity.csrf(csrf -> csrf.disable());
		
		// API 통신에서는 CSRF를 체크하지 않도록 수정
		httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
		
		//jwt
		// Custom Filter(JsonWebTokenAuthenticationFilter) 추가.
		httpSecurity.addFilterAfter(this.createJwtAuthFilter()
								, UsernamePasswordAuthenticationFilter.class);
		
		//jwt
		// AuthorizationDeniedExceptionHandler를 추가한다
		// Controller 코드 이하에서 @PreAuthorized() 검증에 실패하면 아래 설정에 등록한 Handler가 동작
		httpSecurity.exceptionHandling(exceptionHandling -> 
				exceptionHandling.accessDeniedHandler(new AuthorizationDeniedExceptionHandler()));
		
		//UsernamePasswordAuthenticationFilter 수정
		httpSecurity.formLogin(formLogin -> 
					//login 페이지 uRL
					formLogin.loginPage("/login")
					// login 인증 처리 URL 지정 
					//(UsernameAnpPasswordAuthenticationProvider가 실행될 EndPoint)
							 .loginProcessingUrl("/login-provider")
					// 로그인에 필요한 아이디 파라미터 이름을 "email"로 변경한다
							 .usernameParameter("email")
					// 로그인에 성공하면 뭐할까?
					// this.membersDao.updateSuccessLogin(loginVO); 실행
							 .successHandler(this.createLoginSuccessHandler())
					// 로그인에 실패하면 뭐할까?
					// this.memberDao.updateIncreaseLoginFailCOunt(loginVO.getEmail());
					// this.memberDao.updateBlock(loginVO.getEmail());
							 .failureHandler(this.createLoginFailureHandler())
					
					);
		
		return httpSecurity.build(); //filter chain목록을 새롭게 바꾸는 명령어
	}
	
	// configureViewResolvers 설정
	//  spring.mvc.view.prefix, spring.mvc.view.suffix 재설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// addResourceHandlers
	//  src/main/resources/static 경로의 endpoint 재설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /static/css/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/css/**") // /static/css/ 의 엔드포인트
				.addResourceLocations("classpath:/static/css/"); // /static/css/ 의 물리적인 위치
		
		// /static/image/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/image/**") // /static/image/ 의 엔드포인트
				.addResourceLocations("classpath:/static/image/"); // /static/image/ 의 물리적인 위치
		
		// /static/js/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/js/**") // /static/js/ 의 엔드포인트
				.addResourceLocations("classpath:/static/js/"); // /static/js/ 의 물리적인 위치
	}
}
