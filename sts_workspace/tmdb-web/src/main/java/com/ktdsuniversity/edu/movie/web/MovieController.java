package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.InsertMovieVO;
import com.ktdsuniversity.edu.movie.vo.request.UpdateMovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;

import jakarta.validation.Valid;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String viewMain() {
		return "redirect:list";
	}
	
	//전체 조회
	@GetMapping("/list")
	public String viewListPage(Model model){
		
		SearchMovieVO searchMovieVO = this.movieService.findMovieList();
		
		List<MovieVO> movieList = searchMovieVO.getResult();
		int searchCount = searchMovieVO.getCount();
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("searchCount", searchCount);
		
		return "movie-list";
	}
	
	//단적 조회
	@GetMapping("/view/{movieId}")
	public String viewMovieByIdPage(@PathVariable String movieId, Model model) {
		MovieVO movieVO = this.movieService.findOneMovie(movieId);
		model.addAttribute("movie",movieVO);
		return "/movie-view";
	}
	
	//영화 추가
	@GetMapping("/write")
	public String viewInsertPage() {
		return "movie-write";
	}
	
	@PostMapping("/write")
	public String doInsertMovieAction(@Valid @ModelAttribute InsertMovieVO insertMovieVO,
									  BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//error 확인하는 방법
//			System.out.println(bindingResult.getAllErrors());
			model.addAttribute("errorData",insertMovieVO);
			return "movie-write";
		}
		
		String posterUrl = insertMovieVO.getPosterUrl();
		posterUrl = posterUrl.replace("<", "&lt")
							 .replace(">", "&gt");
		insertMovieVO.setPosterUrl(posterUrl);
		
		String title = insertMovieVO.getTitle();
		title = title.replace("<", "&lt")
							 .replace(">", "&gt");
		insertMovieVO.setPosterUrl(title);
		
		boolean result = this.movieService.createMovieData(insertMovieVO);
		return "redirect:/list";
	}
	
	// 영화 업데이트
	@GetMapping("/update/{movieId}")
	public String doUpdateMovie(@PathVariable String movieId, Model model) {
		MovieVO movieVO = this.movieService.findOneMovie(movieId);
		model.addAttribute("movie",movieVO);
		return "/movie-update";
	}
	
	@PostMapping("/update/{movieId}")
	public String doRealUpdate(@PathVariable String movieId, UpdateMovieVO updateMovieVO) {
		updateMovieVO.setMovieId(movieId);
		boolean result = this.movieService.updateMovieById(updateMovieVO);
		return "redirect:/list";
	}
	
	@GetMapping("/delete")
	public String doDeleteAction(@RequestParam String id) {
		boolean result = this.movieService.deleteMovie(id);
		return "redirect:/list";
	}
	

}
