package com.selfdev.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("/write")
    public String writeGet() {
        return "board/write";
    }

}
