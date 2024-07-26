package com.nagarro.accountmanagmentservice.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.accountmanagmentservice.model.TransactionModel;


public interface TransactionRepositry extends JpaRepository<TransactionModel, String> {

}
