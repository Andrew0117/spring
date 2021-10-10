package com.parsing.service;

import com.parsing.model.Client;
import com.parsing.model.Transaction;
import com.parsing.model.Transactions;
import com.parsing.repository.ClientRepository;
import com.parsing.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "transactionService")
@Scope(value = "singleton")
public class TransactionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void saveTransactions(Transactions transactions) {
        for (Transaction transaction : transactions.getTransaction()) {
            Client client = clientRepository.findClientByInn(transaction.getClient().getInn());
            if (client == null) {
                clientRepository.save(transaction.getClient());
            } else {
                transaction.setClient(client);
            }
            transactionRepository.save(transaction);
        }
    }
}
