package com.selfdev.account;

import com.selfdev.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByUserId(String userId);

    Account findByUserId(String userId);
}

