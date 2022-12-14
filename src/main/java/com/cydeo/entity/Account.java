package com.cydeo.entity;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Where(clause = "is_deleted=false")
public class Account {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(columnDefinition = "TIMESTAMP")
    private Date creationDate;

    private Long userId;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}
