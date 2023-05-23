package com.selfdev.board;

import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(WriteForm writeForm, Account account) {
        Board board = Board.builder()
                .type(writeForm.getType())
                .time(writeForm.getTime())
                .daily(writeForm.getDaily())
                .executionAt(writeForm.getExecutionAt())
                .title(writeForm.getTitle())
                .content(writeForm.getContent())
                .writeAt(LocalDateTime.now())
                .account(account)
                .build();

        Board newBoard = boardRepository.save(board);
        return newBoard;
    }
}
