package com.rmit.sept.bk_transactionservices.web;

import com.rmit.sept.bk_transactionservices.Repositories.TransactionRepository;
import com.rmit.sept.bk_transactionservices.TestUtil;
import com.rmit.sept.bk_transactionservices.model.Transaction;
import com.rmit.sept.bk_transactionservices.model.TransactionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransactionControllerTest {

    private final String REGISTER_TRANSACTION_API = "/api/transactions/registertransaction";
    private final String GET_ALL_TRANSACTIONS_API = "/api/transactions/all";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void cleanup() {
        transactionRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    public void registerTransaction_whenInvalidTransaction_receiveBadRequest() {
        Transaction transaction = TestUtil.createValidTransaction();
        transaction.setUsername(null);
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void registerTransaction_whenInvalidTransaction_receiveUserNameRequired() {
        Transaction transaction = TestUtil.createValidTransaction();
        transaction.setUsername(null);
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(response.getBody().getUsername()).isEqualTo("username is required");
    }

    @Test
    public void registerTransaction_whenInvalidTransaction_receiveMultipleErrors() {
        Transaction transaction = TestUtil.createValidTransaction();
        transaction.setUsername(null);
        transaction.setBuyerUsername(null);
        transaction.setIsbn(null);
        transaction.setTotalPrice(0f);
        transaction.setNumOfOldBook(-1);
        transaction.setNumOfNewBook(-1);
        ResponseEntity<Object> response = registerTransaction(transaction, Object.class);
        Map<String, String> errors = (Map<String, String>) response.getBody();
        assertThat(errors.size()).isEqualTo(6);
    }

    @Test
    public void registerTransaction_whenValidTransaction_receiveCreated() {
        Transaction transaction = TestUtil.createValidTransaction();
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void registerTransaction_whenValidTransaction_shouldBeInDB() {
        Transaction transaction = TestUtil.createValidTransaction();
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(transactionRepository.count()).isEqualTo(1);
    }

    @Test
    public void registerTransaction_whenValidTransaction_transactionStateShouldBeApproved() {
        Transaction transaction = TestUtil.createValidTransaction();
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(response.getBody().getTransactionState()).isEqualTo(TransactionState.APPROVED);
    }

    @Test
    public void registerTransaction_whenValidTransaction_transactionDateIsAdded() {
        Transaction transaction = TestUtil.createValidTransaction();
        ResponseEntity<Transaction> response = registerTransaction(transaction, Transaction.class);
        assertThat(response.getBody().getTransactionDate()).isNotEqualTo(null);
    }

    @Test
    public void getAllTransactions_whenZeroTransactions_receiveZeroTransactions() {
        List<Transaction> transactions = (List<Transaction>) getAllTransactions(Object.class).getBody();
        assertThat(transactions.size()).isEqualTo(0);
    }

    @Test
    public void getAllTransactions_whenOneTransactions_receiveOneTransactions() {
        transactionRepository.save(TestUtil.createValidTransaction());
        List<Transaction> transactions = (List<Transaction>) getAllTransactions(Object.class).getBody();
        assertThat(transactions.size()).isEqualTo(1);
    }

    @Test
    public void getAllTransactions_whenMultipleTransactions_receiveMultipleTransactions() {
        transactionRepository.save(TestUtil.createValidTransaction());
        transactionRepository.save(TestUtil.createValidTransaction());
        transactionRepository.save(TestUtil.createValidTransaction());
        transactionRepository.save(TestUtil.createValidTransaction());
        transactionRepository.save(TestUtil.createValidTransaction());
        List<Transaction> transactions = (List<Transaction>) getAllTransactions(Object.class).getBody();
        assertThat(transactions.size()).isEqualTo(5);
    }

    private <T> ResponseEntity<T> registerTransaction(Transaction request, Class<T> response) {
        return testRestTemplate.postForEntity(REGISTER_TRANSACTION_API, request, response);
    }

    private <T> ResponseEntity<T> getAllTransactions(Class<T> response) {
        return testRestTemplate.getForEntity(GET_ALL_TRANSACTIONS_API, response);
    }

}
