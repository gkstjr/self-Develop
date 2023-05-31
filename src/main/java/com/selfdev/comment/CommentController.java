package com.selfdev.comment;

import com.selfdev.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/search")
    public ResponseEntity search(@ModelAttribute CommentForm commentForm) {
        List<CommentForm> commentList = commentService.findAll(commentForm.getBoardId());
        if(commentList == null) { // 게시글의 댓글이 없으면
            return new ResponseEntity<>("해당 게시글의 댓글디 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }else{ // 게시글으 댓글이 있으면 댓글 목록 반환
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentForm commentForm) {
        System.out.println("commentForm = " + commentForm);
        Long saveResult = commentService.save(commentForm);
        if(saveResult != null) {
            //댓글 작성 성공사 댯굴 목록을 가져와서 리턴
            List<CommentForm> commentList = commentService.findAll(commentForm.getBoardId());
            //반환 값을 ResponseEntity로 하면 보내고 싶은 객체와 결과값을 같이 보낼 수 있다.
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }else {
            //댓글 작성 실패
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
