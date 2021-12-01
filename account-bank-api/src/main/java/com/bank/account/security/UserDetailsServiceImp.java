package com.bank.account.security;

import com.bank.account.entity.Client;
import com.bank.account.entity.Role;
import com.bank.account.execption.FunctionnalException;
import com.bank.account.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
  @Autowired private ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Client user =
        clientRepository
            .findClientByAccountNumber(username)
            .orElseThrow(() -> new FunctionnalException("MISSING_CLIENT_FOR_THIS_ACCOUNT", "400"));
    if (user == null) {
      throw new FunctionnalException("MISSING USER", "400");
    }
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("USER"));

    CustomUser customUser =
        new CustomUser(
            user.getId(),
            user.getAccountNumber(),
            user.getPassword(),
            null,
            user.getLastName(),
            user.getFirstName(),
            user.getEmailAdress(),
            user.getPhoneNumber(),
            user.getClientId(),
            user.getBirtDayDate(),
            authorities,
            user.getEmailAdress());

    return customUser;
  }
}
