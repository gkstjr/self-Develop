package com.selfdev.board;

import com.selfdev.account.AccountRepository;
import com.selfdev.account.AccountService;
import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

//게시판 관련 테스트하기
@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("글 더미 데이터 넣기")
    public void insertTest() {
            Account account = accountRepository.findByUserId("gkstjr");

        for(int i = 21 ; i < 40; i++) {
            Board board = new Board();
            if(i % 2 == 0) board.setType("자기계발");
            else board.setType("모임");
            board.setTime("1시간");
            board.setDaily("영화;독서;기타");
            board.setExecutionAt("2023-05-25");
            board.setTitle("더미 글 " + i);
            board.setContent("더미 내용 " + i);
            board.setWriteAt(LocalDateTime.now());
            board.setAccount(account);

            Board newBoard =  boardRepository.save(board);
            assertThat(board).isEqualTo(newBoard);
        }

    }
}