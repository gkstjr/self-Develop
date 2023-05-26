package com.selfdev.board;

import com.selfdev.account.CurrentUser;
import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write")
    public String writeGet(Model model) {
        model.addAttribute("writeForm",new WriteForm());
        return "board/write";
    }

    @RequestMapping("/write")
    public String writePost(@CurrentUser Account account, WriteForm writeForm, Model model , RedirectAttributes re) {
          Board newBoard = boardService.save(writeForm,account);
          Long boardId = newBoard.getId();
//        boolean myBoard = newBoard.getAccount().getId() == account.getId();
//        model.addAttribute("myBoard",myBoard);
//
//        //작성일 String으로 변환 코드
//        String executionAt = String.format(newBoard.getExecutionAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        model.addAttribute("executionAt",executionAt);
//
//        //일상 선택 ;기준으로 분류
//        String dailyAll = newBoard.getDaily();
//        String[] daily = dailyAll.split(";");
//        model.addAttribute("daily",daily);

        re.addAttribute("boardId",boardId);
        return "redirect:/detail";
    }
    @GetMapping("/detail")
    public String detailGet(@CurrentUser Account account, @RequestParam("boardId") Long boardId, Model model) {
        Board newBoard = boardService.findByBoardId(boardId);

        if(account != null) { // account가 null일 때 오류 나올 수 있으니 검증해주기
        boolean myBoard = newBoard.getAccount().getId() == account.getId();
        model.addAttribute("myBoard",myBoard);
        }

        //작성일 String으로 변환 코드
        String executionAt = String.format(newBoard.getExecutionAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("executionAt",executionAt);

        //일상 선택 ;기준으로 분류
        String dailyAll = newBoard.getDaily();
        String[] daily = dailyAll.split(";");
        model.addAttribute("daily",daily);

        model.addAttribute(newBoard);

        return "board/detail";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam("id") Long boardId , Model model) {
        Board board = boardService.findByBoardId(boardId);
        model.addAttribute(board);
        return "board/update";
    }

    @PostMapping("/update")
    public String updateForm(WriteForm writeForm,Model model,@RequestParam Long boardId,RedirectAttributes rd) {
        boardService.update(writeForm,boardId);
        rd.addAttribute("boardId",boardId);
        return "redirect:/detail";
    }

    @PostMapping("/delete")
    public String deleteGet(@RequestParam("id") Long boardId , Model model) {
        boardService.remove(boardId);
        return "redirect:/";
    }
}
