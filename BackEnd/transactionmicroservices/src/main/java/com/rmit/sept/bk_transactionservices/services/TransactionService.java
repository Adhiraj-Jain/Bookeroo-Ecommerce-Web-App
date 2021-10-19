package com.rmit.sept.bk_transactionservices.services;

import com.rmit.sept.bk_transactionservices.Repositories.TransactionRepository;
import com.rmit.sept.bk_transactionservices.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction (Transaction newTransaction) {
        try {
            return transactionRepository.save(newTransaction);
        } catch (Exception e) {
            return null;
        }
    }

    // Used if the user is an admin (admin can see all transactions)
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactionRepository.findAll()) {
            transactions.add(transaction);
        }
        return transactions;
    }

    // Returns transactions for a particular user
    private List<Transaction> getOnlyUserTransactions(List<Transaction> allTransactions, String username) {
        List<Transaction> userTransactions = new ArrayList<Transaction>();

        for (Transaction eachTransaction : allTransactions) {
            if (eachTransaction.getUsername().equals(username) || eachTransaction.getBuyerUsername().equals(username)) {
                userTransactions.add(eachTransaction);
            }
        }

        return userTransactions;
    }

    // Used for all other users (other users can see only their transactions)
    public List<Transaction> getTransactionsFor(String username) {
        List<Transaction> allTransactions;
        List<Transaction> userTransactions;

        allTransactions = getAllTransactions();
        userTransactions = getOnlyUserTransactions(allTransactions, username);

//        for (Transaction transaction : transactionRepository.findAll()) {
//            if (transaction.getUsername().equals(username) || transaction.getBuyerUsername().equals(username)) {
//                userTransactions.add(transaction);
//            }
//        }
        return userTransactions;
    }

    public List<Transaction> getLatestTransactionsFirst(String username, boolean isUserAdmin) {
        List<Transaction> allLatestTransactions = new ArrayList<Transaction>();
        List<Transaction> userLatestTransactions;

        for (Transaction transaction : transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "transactionDate"))) {
            allLatestTransactions.add(transaction);
        }

        userLatestTransactions = getOnlyUserTransactions(allLatestTransactions,username);

        if (isUserAdmin) {
            return allLatestTransactions;
        }
        else {
            return userLatestTransactions;
        }
    }

    public List<Transaction> getOldestTransactionsFirst(String username, boolean isUserAdmin) {
        List<Transaction> allOldesttransactions = new ArrayList<Transaction>();
        List<Transaction> userOldesttransactions;

        for (Transaction transaction : transactionRepository.findAll(Sort.by(Sort.Direction.ASC, "transactionDate"))) {
            allOldesttransactions.add(transaction);
        }

        userOldesttransactions = getOnlyUserTransactions(allOldesttransactions, username);

        if (isUserAdmin) {
            return allOldesttransactions;
        }
        else {
            return userOldesttransactions;
        }
    }

    public List<Transaction> getAllSold() {
        List<Transaction> soldBooks = new ArrayList<Transaction>();
        for (Transaction item : transactionRepository.findAll()) {
            soldBooks.add(item);
        }
        return soldBooks;
    }
}
