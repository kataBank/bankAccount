package com.bank.account.repository;

import com.bank.account.entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OperationRepository extends JpaRepository<Operations, Long> {
}
