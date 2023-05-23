package com.selfdev.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter @EqualsAndHashCode(of ="id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue @Column(name = "account_id")
    private Long id;

    @Column(unique = true)
    private String userId;

    @OneToMany(mappedBy = "account")
    private List<Board> boards = new ArrayList<>();

    private String name;

    private String password;

    private LocalDateTime joinedAt;


}
