package com.selfdev.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
// 댓글 데이터 전달 객체
@Data
public class CommentForm {

    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime writeAt;
}
