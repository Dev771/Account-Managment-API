package com.nagarro.customermanagmentservice.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.customermanagmentservice.model.CustomerModel;

@Repository
public interface CustomerRepositry extends JpaRepository<CustomerModel, String> {
	CustomerModel findByEmail(String email);
	List<CustomerModel> findByEmailOrOfficialDocumentID(String email, String officialDocumentId);
}
