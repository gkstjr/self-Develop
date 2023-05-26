package com.selfdev.board;

import com.selfdev.domain.Account;
import com.selfdev.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {



    Page<Board> findAllByAccount(Account account, PageRequest id);
}
