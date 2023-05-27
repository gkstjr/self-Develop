package com.selfdev.board;

import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    //글 목록 전체 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
//    패아자별 글 목록 조회
    public Page<SelectForm> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; //몇페이지인지
        int pageLimit = 20;  //한 페이지의 보여줄 글 개수

        // 한 페이지당 20개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        Page<Board> boardEntities
                = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<SelectForm> boardList = boardEntities.map(board -> new SelectForm(board.getId(),board.getType(),board.getTime(),board.getAccount(),board.getDaily(),board.getExecutionAt(),board.getTitle(),board.getContent(),board.getWriteAt(),board.getUpdateAt()));
        /*
        System.out.println("요청 페이지에 해당하는 글 = " + boardList.getContent());
        System.out.println("전체 글 갯수 = " + boardList.getTotalElements());
        System.out.println("DB로 요청한 페이지 번호 = " + boardList.getNumber());
        System.out.println("전체 페이지 갯수 =" + boardList.getTotalPages());
        System.out.println("한 페이지에 보여지는 글 갯수 = " + boardList.getSize());
        System.out.println("이전 페이지 존재 여부 = " + boardList.hasPrevious());
        System.out.println("첫 페이지 여부 = " + boardList.isFirst());
        System.out.println("마지막 페이지 여부 = " + boardList.isLast());
        */

        return boardList;
    }

    public Page<SelectForm> pagingMy(Pageable pageable,Account account) {
        int page = pageable.getPageNumber() - 1; //몇페이지인지
        int pageLimit = 20;  //한 페이지의 보여줄 글 개수

        // 한 페이지당 20개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        Page<Board> boardEntities
                = boardRepository.findAllByAccount(account,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<SelectForm> boardList = boardEntities.map(board -> new SelectForm(board.getId(),board.getType(),board.getTime(),board.getAccount(),board.getDaily(),board.getExecutionAt(),board.getTitle(),board.getContent(),board.getWriteAt(),board.getUpdateAt()));

        return boardList;
    }
}
