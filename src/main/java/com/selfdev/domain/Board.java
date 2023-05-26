package com.selfdev.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue @Column(name = "board_id")
    private Long id;

    private String type; //글 구분

    private String time; // 소요 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private String daily; // 일상 선택

    private String executionAt; //실행 일자

    private String title; //글 제목

    private String content; // 글 내용

    private LocalDateTime writeAt; // 작성 일자

    private LocalDateTime updateAt; // 수정 일자

}
