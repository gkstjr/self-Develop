package com.selfdev.board;

import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Board findByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        return board;
    }

    public void update(WriteForm writeForm,Long boardId) {
        Board board = boardRepository.findById(boardId).get();
                        board.setType(writeForm.getType());
                        board.setTime(writeForm.getTime());
                        board.setDaily(writeForm.getDaily());
                        board.setExecutionAt(writeForm.getExecutionAt());
                        board.setTitle(writeForm.getTitle());
                        board.setContent(writeForm.getContent());
                        board.setUpdateAt(LocalDateTime.now());

        boardRepository.save(board);
    }

    public void remove(Long boardId) {
        boardRepository.deleteById(boardId);

    }
}
