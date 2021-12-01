package com.bank.account.integration;

import com.bank.account.business.account.AccountBusiness;
import com.bank.account.entity.Account;
import com.bank.account.model.AccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {
  @Autowired private TestRestTemplate testRestTemplate;
  @Autowired private static AccountBusiness accountBusiness;
  private static AccountModel mockAccount;

  @Test
  // @Sql("/data.sql")
  void testGetUserByid() {
    ResponseEntity<Account> response =
        testRestTemplate.getForEntity("/api/v1/account/1", Account.class);

    // Assert.assertEquals(java.util.Optional.of(1L).get(), response.getBody().getId());
    // Assert.assertEquals("h@ya.fr", response.getBody().getEmail());
    // Assert.assertEquals("20/02/12", response.getBody().getBirthdate());
  }

  @Test
  void testSaveAccount() {
    mockAccount = new AccountModel();
    mockAccount.setAccountNumber("1234");
    mockAccount.setRib("56789");
    mockAccount.setIban("693");
    mockAccount.setIdClient(1L);

    HttpEntity<AccountModel> request = new HttpEntity<>(mockAccount);

    ResponseEntity<Account> response =
        testRestTemplate.postForEntity("/api/v1/account/", request, Account.class);

    // Assert.assertEquals(java.util.Optional.of(1L).get(), response.getBody().getId());
    // Assert.assertEquals("r@yahoo.fr", response.getBody().getEmail());
    // Assert.assertEquals("Raoul", response.getBody().getName());
    // Assert.assertEquals("20/02/21", response.getBody().getBirthdate());
  }
}
