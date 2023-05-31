package com.selfdev.comment;

import com.selfdev.account.AccountRepository;
import com.selfdev.board.BoardRepository;
import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import com.selfdev.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

        private final CommentRepository commentRepository;
        private final BoardRepository boardRepository;
        private final AccountRepository accountRepository;

        public Long save(CommentForm commentForm) {
                //account의 userId로 account 객체 가져오기
                Account account =  accountRepository.findByUserId(commentForm.getCommentWriter());
                //글번호가 잘못 넘어 올 수 도있으므로 null 처리 해줌
                Optional<Board> optionalBoard = boardRepository.findById(commentForm.getBoardId());
                if(optionalBoard.isPresent()) {
                        Board board = optionalBoard.get();
                        Comment comment = Comment.builder()
                                .commentWriter(commentForm.getCommentWriter())
                                .commentNickname(account.getName())
                                .commentContents(commentForm.getCommentContents())
                                .board(board)
                                .writeAt(LocalDateTime.now())
                                .build();
                        //댓글 저장후 id 반환
                        return commentRepository.save(comment).getId();
                }else {
                        return null;
                }

        }
        //해당 게시물 번호에 맞는 댓글 목록 조회
        public List<CommentForm> findAll(Long boardId) {
               Board board =  boardRepository.findById(boardId).get();
               List<Comment> commentList = commentRepository.findAllByBoardOrderByIdDesc(board);
               if(commentList == null) {
                   return null;
               }
               List<CommentForm> commentFormsList = new ArrayList<>();
               for(Comment comment : commentList) {
                       CommentForm commentForm = CommentForm.builder()
                               .id(comment.getId())
                               .commentWriter(comment.getCommentWriter())
                               .commentNickname(comment.getCommentNickname())
                               .commentContents(comment.getCommentContents())
                               .writeAt(comment.getWriteAt())
                               .boardId(comment.getBoard().getId())
                               .build();
                       commentFormsList.add(commentForm);
               }
               return commentFormsList;
        }
}
