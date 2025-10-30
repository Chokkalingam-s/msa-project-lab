package com.example.funds.controller;

import com.example.funds.model.Transaction;
import com.example.funds.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody Map<String, String> body) {
        String from = body.get("fromAcc");
        String to = body.get("toAcc");
        BigDecimal amount = new BigDecimal(body.get("amount"));
        String type = body.get("type");

        Transaction txn = service.transfer(from, to, amount, type);
        return ResponseEntity.ok(txn);
    }
}
