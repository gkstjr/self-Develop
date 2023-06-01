package com.selfdev.board;

import com.selfdev.domain.Account;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class SelectForm {

    private Long id;

    private String type; //글 구분

    private String time; // 소요 시간

    private Account account;

    private String daily; // 일상 선택

    private String executionAt; //실행 일자

    private String title; //글 제목

    private String content; // 글 내용

    private LocalDateTime writeAt; // 작성 일자

    private LocalDateTime updateAt; // 수정 일자

    private String[] dailys; // 일상을 분리후 넣음

    private Long CommentCount; // 댓글 수

    public SelectForm(Long id, String type, String time, Account account, String daily, String executionAt, String title, String content, LocalDateTime writeAt, LocalDateTime updateAt) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.account = account;
        this.daily = daily;
        this.executionAt = executionAt;
        this.title = title;
        this.content = content;
        this.writeAt = writeAt;
        this.updateAt = updateAt;
    }

    // 일상을 ; 기준으로 분리 후 넣어주기
    public void splitDaily() {
      this.dailys = daily.split(";");
    }

}
