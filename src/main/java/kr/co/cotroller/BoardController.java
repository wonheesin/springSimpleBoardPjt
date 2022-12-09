package kr.co.cotroller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.service.BoardService;
import kr.co.vo.BoardVO;
import kr.co.vo.Criteria;
import kr.co.vo.PageMaker;
import kr.co.vo.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;
	
	//게시글 작성 화면
	@RequestMapping(value="/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("writeView");
	}
	
	//게시판 글 작성
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception {
		logger.info("write");
		
		service.write(boardVO);
		
		return "redirect:/board/list";
	}
	
	//게시글 목록 조회
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		
		model.addAttribute("list", service.list(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
	}
	
	//게시글 조회
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO boardVO, Model model) throws Exception {
		logger.info("read");
		
		model.addAttribute("read", service.read(boardVO.getBno()));
		
		return "board/readView";
	}
	
	//게시글 수정 뷰
	@GetMapping("/updateView")
	public String updateView(BoardVO boardVO, Model model) throws Exception {
		logger.info("updateView");
		
		model.addAttribute("update", service.read(boardVO.getBno()));
		
		return "board/updateView";
	}
	
	//게시글 수정
	@PostMapping("/update")
	public String update(BoardVO boardVO) throws Exception {
		logger.info("update");
		
		service.update(boardVO);
		
		return "redirect:/board/list";
	}
	
	//게시판 삭제
	@PostMapping("/delete")
	public String delete(BoardVO boardVO) throws Exception {
		service.delete(boardVO.getBno());
		
		return "redirect:/board/list";
	}
}
