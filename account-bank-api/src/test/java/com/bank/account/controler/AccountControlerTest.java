package com.bank.account.controler;

import com.bank.account.business.account.AccountBusiness;
import com.bank.account.business.cleint.ClientBusiness;
import com.bank.account.controler.account.AccountControler;
import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import com.bank.account.model.AccountModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountControler.class)
public class AccountControlerTest {
  @MockBean private AccountBusiness accountBusiness;

  @MockBean private ClientBusiness clientBusiness;

  @Autowired private MockMvc mockMvc;
  private static Client mockClient;
  private static AccountModel mockAccount;
  private static Account account;

  @BeforeAll
  static void beforeAll() {
    mockClient = new Client();
    mockClient.setLastName("PETIT");
    mockClient.setFirstName("EMMANUEL");
    mockClient.setBirtDayDate(LocalDate.of(2018, 02, 02));

    mockAccount = new AccountModel();
    mockAccount.setAccountNumber("1234");
    mockAccount.setRib("56789");
    mockAccount.setIban("693");
    mockAccount.setIdClient(1L);

    account = new Account();
    account.setIdAccount(1L);
    account.setAccountNumber("1234");
    account.setRib("56789");
    account.setIban("693");
    account.setClients(mockClient);
  }
/*
  @Test
  void testGetAccount() throws Exception {

    // Mock service
    Mockito.when(clientBusiness.findClientByid(any())).thenReturn(Optional.ofNullable(mockClient));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/account/1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.rib").value("56789"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1234"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andDo(print());
  }

  @Test
  void testSaveAccount() throws Exception {

    // Mock service
    Mockito.when(accountBusiness.save(any())).thenReturn(account);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockAccount)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.rib").value("56789"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.iban").value("693"))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.idAccount").isNotEmpty())
        .andDo(print());
  }*/
}
