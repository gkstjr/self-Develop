package com.selfdev.main;

import com.selfdev.board.BoardService;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;
    @GetMapping("/")
    public String home(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber(); // 몇페이지가 요청 됐는지 값을 알 수 있음
         Page<Board> boardList = boardService.paging(pageable);

//        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);
        return "index";
    }
}
