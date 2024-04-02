package com.example.picpaysimplificado.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "WALLETS")
public class Wallet {

    @Id
    private UUID id;

    private String name;

    private String cpf;

    private String email;

    private String password;

    private WalletType type;

    private BigDecimal balance;

    public void debit(BigDecimal value){
        this.balance = balance.subtract(value);
    }

}
