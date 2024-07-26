package com.example.picpaysimplificado.transaction;

import com.example.picpaysimplificado.authorization.AuthorizationService;
import com.example.picpaysimplificado.exception.InvalidTransactionException;
import com.example.picpaysimplificado.notification.NotificationService;
import com.example.picpaysimplificado.wallet.Wallet;
import com.example.picpaysimplificado.wallet.WalletRepository;
import com.example.picpaysimplificado.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;


    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, AuthorizationService authorizationService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {

        transactionValidation(transaction);
        Transaction newTransaction = transactionRepository.save(transaction);

        Wallet walletPayer = walletRepository.findById(transaction.payer()).get();
        Wallet walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizationService.authorize(newTransaction);

        notificationService.sendNotification(newTransaction);

        return newTransaction;
    }

    private void transactionValidation(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payeeId -> walletRepository.findById(transaction.payer())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException("Error transaction - %s".formatted(transaction))))
                .orElseThrow(() -> new InvalidTransactionException("Error transaction - %s".formatted(transaction)));
    }

    private static boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM &&
                payer.balance().compareTo(transaction.value()) > -1 &&
                !payer.id().equals(transaction.id());
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
