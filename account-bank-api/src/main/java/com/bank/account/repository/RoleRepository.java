package com.bank.account.repository;

import com.bank.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role,Long> {
}
