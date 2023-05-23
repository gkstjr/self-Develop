package com.selfdev.board;

import com.selfdev.account.CurrentUser;
import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String writePost(@CurrentUser Account account, WriteForm writeForm,Model model) {
        Board newBoard = boardService.save(writeForm,account);

        boolean myBoard = newBoard.getAccount().getId() == account.getId();
        model.addAttribute("myBoard",myBoard);

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
}
