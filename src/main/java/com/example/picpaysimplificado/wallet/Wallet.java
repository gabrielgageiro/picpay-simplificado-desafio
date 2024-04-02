package com.example.picpaysimplificado.wallet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String email;

    @Column(name = "name")
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WalletType type;

    @Column(name = "balance")
    private BigDecimal balance;

}
