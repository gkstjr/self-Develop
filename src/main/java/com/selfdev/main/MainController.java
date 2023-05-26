package com.selfdev.main;

import com.selfdev.account.CurrentUser;
import com.selfdev.board.BoardService;
import com.selfdev.board.SelectForm;
import com.selfdev.domain.Account;
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
    public String home(@PageableDefault(page = 1) Pageable pageable, @CurrentUser Account account , String myBoard , Model model) {
        Page<SelectForm> boardList;

        if(myBoard == null) { // 전체 글 목록
            boardList = boardService.paging(pageable);
        }else { // 내 글만 목록
            if(account == null) { //아이디 필요하므로 로그인 안한 상태면 로그인 화면으로 이동
                return "redirect:/login";
            }
            boardList = boardService.pagingMy(pageable,account);
        }

        int blockLimit = 5; // 보여지는 페이지 갯수
        int startPage =  (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage =  ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        for(SelectForm board : boardList) {
            board.splitDaily();
        }

        model.addAttribute("boardList",boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "index";
    }
}
