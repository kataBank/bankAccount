package com.bank.account.data;

import com.bank.account.business.operations.OperationsBusiness;
import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import com.bank.account.entity.Operations;
import com.bank.account.entity.Role;
import com.bank.account.model.OperationModel;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.ClientRepository;
import com.bank.account.repository.OperationRepository;
import com.bank.account.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Configuration
public class initData {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationsBusiness operationsBusiness;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void initdata() {

        Client client1 = new Client();
        client1.setBirtDayDate(LocalDate.of(1986, 05, 20));
        client1.setFirstName("Emilie");
        client1.setLastName("Pages");
        client1.setPhoneNumber("+3305342536");
        client1.setClientId("12345678");
        client1.setPassword(bCryptPasswordEncoder.encode(client1.getClientId()));
        client1.setAccountNumber("9876543210");

        List<Role> roles = new ArrayList<Role>();

        Role user = new Role();
        user.setRoleName("USER");
        user.setDescription("role utilisateur");
        roles.add(user);

        client1.setRoles(roles);
        clientRepository.save(client1);

        // Account

        Account account = new Account();

        account.setClients(client1);
        account.setIban("FR7612548029989876543210917.");
        account.setAccountNumber(client1.getAccountNumber());
        account.setAccountType("EPARGNE");
        account.setBalance(Double.valueOf(0.0));
        account.setDomiciliation("SG MELUN");
        account.setRib("FR7630004000031234567890143 43");
        accountRepository.save(account);

        //Operation
        OperationModel operationCredit = new OperationModel();

        operationCredit.setAccountNumber(account.getAccountNumber());
        operationCredit.setAmountOperation(Double.valueOf(3500));
        operationCredit.setDateOperation(LocalDate.now());
        operationCredit.setDescription("virement salaire");
        operationCredit.setType("CREDIT");

        operationsBusiness.transaction(operationCredit);

        OperationModel operationDebit = new OperationModel();

        operationDebit.setAccountNumber(account.getAccountNumber());
        operationDebit.setAmountOperation(Double.valueOf(200));
        operationDebit.setDateOperation(LocalDate.now());
        operationDebit.setDescription("Paiement EDF");
        operationDebit.setType("DEBIT");

        operationsBusiness.transaction(operationDebit);





    }
}
