package com.selfdev.domain;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter @EqualsAndHashCode(of ="id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userId;

    private String name;

    private String password;

    private LocalDateTime joinedAt;


}
