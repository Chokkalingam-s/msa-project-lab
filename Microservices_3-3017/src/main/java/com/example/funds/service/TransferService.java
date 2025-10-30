package com.example.funds.service;

import com.example.funds.model.Account;
import com.example.funds.model.Transaction;
import com.example.funds.repo.AccountRepository;
import com.example.funds.repo.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
    private final AccountRepository accountRepo;
    private final TransactionRepository txnRepo;

    public TransferService(AccountRepository accountRepo, TransactionRepository txnRepo) {
        this.accountRepo = accountRepo;
        this.txnRepo = txnRepo;
    }

    @Transactional
    public Transaction transfer(String fromAccNo, String toAccNo, BigDecimal amount, String type) {
        Account from = accountRepo.findByNumber(fromAccNo)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account to = accountRepo.findByNumber(toAccNo)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (from.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient balance");

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepo.save(from);
        accountRepo.save(to);

        Transaction txn = new Transaction();
        txn.setType(type);
        txn.setAmount(amount);
        txn.setFromAcc(fromAccNo);
        txn.setToAcc(toAccNo);

        return txnRepo.save(txn);
    }
}
