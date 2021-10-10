package com.parsing;

import com.parsing.model.Client;
import com.parsing.model.Transaction;
import com.parsing.model.Transactions;
import com.parsing.repository.ClientRepository;
import com.parsing.repository.TransactionRepository;
import com.parsing.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void insertData() {
        Transactions transactions = new Transactions();

        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("LastName");
        client.setInn(101011001L);

        Transaction transaction0 = new Transaction();
        transaction0.setPlace("1");
        transaction0.setAmount(10.01);
        transaction0.setCurrency("USD");
        transaction0.setCard("1234567890");
        transaction0.setClient(client);

        Transaction transaction1 = new Transaction();
        transaction1.setPlace("2");
        transaction1.setAmount(20.02);
        transaction1.setCurrency("USD");
        transaction1.setCard("123457890");
        transaction1.setClient(client);

        Transaction transaction2 = new Transaction();
        transaction2.setPlace("3");
        transaction2.setAmount(30.03);
        transaction2.setCurrency("USD");
        transaction2.setCard("12347890");
        transaction2.setClient(client);

        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(transaction0);
        transactionSet.add(transaction1);
        transactionSet.add(transaction2);

        transactions.setTransaction(transactionSet);

        transactionService.saveTransactions(transactions);

        long countInTableClient = clientRepository.count();
        long countInTableTransaction = transactionRepository.count();

        assertEquals(countInTableClient, 1);
        assertEquals(countInTableTransaction, 3);
    }
}
