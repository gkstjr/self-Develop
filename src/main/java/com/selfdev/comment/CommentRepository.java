package com.selfdev.comment;

import com.selfdev.domain.Board;
import com.selfdev.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    //select * from comment where board_id = ? order by id desc;
    List<Comment> findAllByBoardOrderByIdDesc(Board board);

}
