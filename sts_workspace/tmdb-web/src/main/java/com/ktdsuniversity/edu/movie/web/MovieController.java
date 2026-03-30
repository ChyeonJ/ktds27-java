package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchMovieVO;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/list")
	public String viewListPage(Model model){
		
		SearchMovieVO searchMovieVO = this.movieService.findMovieList();
		
		List<MovieVO> movieList = searchMovieVO.getResult();
		int searchCount = searchMovieVO.getCount();
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("searchCount", searchCount);
		
		return "movie-list";
	}

}
