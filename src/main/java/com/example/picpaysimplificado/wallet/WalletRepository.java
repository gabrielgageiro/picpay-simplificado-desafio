package com.example.picpaysimplificado.wallet;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WalletRepository  extends CrudRepository<Wallet, UUID> {
}
