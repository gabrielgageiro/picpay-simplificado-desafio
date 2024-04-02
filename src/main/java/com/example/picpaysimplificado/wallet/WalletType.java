package com.example.picpaysimplificado.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WalletType {

    COMUM("Comum"),
    LOJISTA("Lojista");

    private final String descricao;

}
