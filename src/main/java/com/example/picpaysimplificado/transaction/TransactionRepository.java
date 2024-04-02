package com.example.picpaysimplificado.transaction;

import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface TransactionRepository extends ListCrudRepository<Transaction, UUID> {

}
