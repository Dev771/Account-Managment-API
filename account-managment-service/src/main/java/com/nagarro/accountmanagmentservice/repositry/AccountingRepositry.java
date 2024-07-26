package com.nagarro.accountmanagmentservice.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.accountmanagmentservice.model.AccountDetailsModel;

@Repository
public interface AccountingRepositry extends JpaRepository<AccountDetailsModel, String> {
	public AccountDetailsModel findByUserId(String userId);
}
