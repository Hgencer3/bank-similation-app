package com.cydeo.entity;

import com.cydeo.dto.AccountDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;

    private BigDecimal amount;

    private String message;

    @Column(columnDefinition = "TIMESTAMP")
    private Date creationDate;


}
