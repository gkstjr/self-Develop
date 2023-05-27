package com.selfdev.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute CommentForm commentForm) {
        System.out.println("한석");
        System.out.println("commentForm = " + commentForm);
        return "요청 성공";
    }
}
