package com.example.picpaysimplificado.transaction;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    private UUID id;

    private UUID payerId;

    private UUID payeeId;

    private BigDecimal value;

    @CreatedDate
    private LocalDateTime createdAt;
}
