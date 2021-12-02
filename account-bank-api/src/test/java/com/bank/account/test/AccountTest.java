package com.bank.account.test;

import com.bank.account.business.account.AccountBusiness;
import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import com.bank.account.entity.Operations;
import com.bank.account.model.AccountModel;
import com.bank.account.model.ClientModel;
import com.bank.account.model.OperationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTest {
  @Autowired private TestRestTemplate testRestTemplate;
  @Autowired private static AccountBusiness accountBusiness;
  private static AccountModel mockAccount;

  @Test
  void testGetClientByid() {
    ResponseEntity<Client> response =
        testRestTemplate.getForEntity("/api/v1/client/1", Client.class);

    Assertions.assertEquals("12345678", response.getBody().getClientId());
    Assertions.assertEquals("+3305342536", response.getBody().getPhoneNumber());
  }

  @Test
  void test_Success_SaveClient() {

    ClientModel clientModel = new ClientModel();

    clientModel.setBirtDayDate(LocalDate.of(1986, 05, 20));
    clientModel.setFirstName("William");
    clientModel.setLastName("Roberto");
    clientModel.setPhoneNumber("+330534236");
    clientModel.setClientId("123456582");
    clientModel.setAccountNumber("987654396");

    HttpEntity<ClientModel> request = new HttpEntity<>(clientModel);
    ResponseEntity<Client> response =
        testRestTemplate.postForEntity("/api/v1/client/", request, Client.class);

    Assertions.assertNotNull(response.getBody().getId());
  }

  @Test
  void test_Success_SaveAccount() {
    mockAccount = new AccountModel();
    mockAccount.setAccountNumber("8888888888");
    mockAccount.setIdClient(2L);

    HttpEntity<AccountModel> request = new HttpEntity<>(mockAccount);

    ResponseEntity<Account> response =
        testRestTemplate.postForEntity("/api/v1/account/", request, Account.class);

    Assertions.assertEquals("8888888888", response.getBody().getAccountNumber());
    Assertions.assertEquals("+3305342537", response.getBody().getClients().getPhoneNumber());
    Assertions.assertEquals(Double.valueOf(0.0), response.getBody().getBalance());
    Assertions.assertNotNull(response.getBody().getIban());
    Assertions.assertNotNull(response.getBody().getRib());
  }

  @Test
  void test_SaveAccount_with_wrong_AccountNumber() {
    mockAccount = new AccountModel();
    mockAccount.setAccountNumber("8888888882");

    HttpEntity<AccountModel> request = new HttpEntity<>(mockAccount);

    ResponseEntity<Account> response =
        testRestTemplate.postForEntity("/api/v1/account/", request, Account.class);

    Assertions.assertEquals(null, response.getBody().getIdAccount());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void test_Success_Deposit_Account() {

    OperationModel operationModel = new OperationModel();
    operationModel.setAccountNumber("9876543210");
    operationModel.setAmountOperation(Double.valueOf(3500));
    operationModel.setDateOperation(LocalDate.now());
    operationModel.setDescription("virement salaire");
    operationModel.setType("CREDIT");

    HttpEntity<OperationModel> request = new HttpEntity<>(operationModel);

    ResponseEntity<Operations> response =
        testRestTemplate.postForEntity("/api/v1/operation/", request, Operations.class);

    Assertions.assertEquals(
        "FR7630004000031234567890143 43", response.getBody().getAccount().getRib());
    Assertions.assertEquals(
        "FR7612548029989876543210917.", response.getBody().getAccount().getIban());
  }

  @Test
  void test_Success_Deposit_Wrong_AccountNumber() {

    OperationModel operationModel = new OperationModel();
    operationModel.setAccountNumber("98765432196");
    operationModel.setAmountOperation(Double.valueOf(3500));
    operationModel.setDateOperation(LocalDate.now());
    operationModel.setDescription("virement salaire");
    operationModel.setType("CREDIT");

    HttpEntity<OperationModel> request = new HttpEntity<>(operationModel);

    ResponseEntity<Operations> response =
        testRestTemplate.postForEntity("/api/v1/operation/", request, Operations.class);

    Assertions.assertEquals(null, response.getBody().getIdOperation());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void test_Success_Withdrawal_Account() {

    OperationModel operationModel = new OperationModel();
    operationModel.setAccountNumber("9876543210");
    operationModel.setAmountOperation(Double.valueOf(1000));
    operationModel.setDateOperation(LocalDate.now());
    operationModel.setDescription("Facture EDF");
    operationModel.setType("DEBIT");

    HttpEntity<OperationModel> request = new HttpEntity<>(operationModel);

    ResponseEntity<Operations> response =
        testRestTemplate.postForEntity("/api/v1/operation/", request, Operations.class);

    Assertions.assertEquals(Double.valueOf(2300), response.getBody().getAccount().getBalance());
    Assertions.assertEquals(
        "FR7630004000031234567890143 43", response.getBody().getAccount().getRib());
    Assertions.assertEquals(
        "FR7612548029989876543210917.", response.getBody().getAccount().getIban());
  }

  @Test
  void test_Success_Wrong_Amount_Withdrawal() {

    OperationModel operationModel = new OperationModel();
    operationModel.setAccountNumber("9876543210");
    operationModel.setAmountOperation(Double.valueOf(10000));
    operationModel.setDateOperation(LocalDate.now());
    operationModel.setDescription("Facture EDF");
    operationModel.setType("DEBIT");

    HttpEntity<OperationModel> request = new HttpEntity<>(operationModel);

    ResponseEntity<Operations> response =
            testRestTemplate.postForEntity("/api/v1/operation/", request, Operations.class);

    Assertions.assertEquals(null, response.getBody().getIdOperation());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void test_GetAccount_By_IdClient() {

    ResponseEntity<Account> response =
        testRestTemplate.getForEntity("/api/v1/account/1", Account.class);

    Assertions.assertEquals("FR7630004000031234567890143 43", response.getBody().getRib());
    Assertions.assertEquals("9876543210", response.getBody().getAccountNumber());
  }
}
