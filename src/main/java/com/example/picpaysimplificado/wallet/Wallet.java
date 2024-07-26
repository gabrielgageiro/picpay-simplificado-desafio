package com.example.picpaysimplificado.wallet;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;


@Table(name = "WALLETS")
public record Wallet (
    @Id
    Long id,
    String fullName,
    String cpf,
    String email,
    String password,
    @Enumerated(EnumType.STRING)
    WalletType type,
    BigDecimal balance){

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.add(value));
    }
}
