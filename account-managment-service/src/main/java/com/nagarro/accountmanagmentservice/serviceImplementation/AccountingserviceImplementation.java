package com.nagarro.accountmanagmentservice.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.common.net.HttpHeaders;
import com.nagarro.accountmanagmentservice.dto.AccountDetailsEntity;
import com.nagarro.accountmanagmentservice.dto.HeaderResponse;
import com.nagarro.accountmanagmentservice.dto.TransactionEntity;
import com.nagarro.accountmanagmentservice.model.AccountDetailsModel;
import com.nagarro.accountmanagmentservice.model.TransactionModel;
import com.nagarro.accountmanagmentservice.producer.RabbitMQProducer;
import com.nagarro.accountmanagmentservice.repositry.AccountingRepositry;
import com.nagarro.accountmanagmentservice.repositry.TransactionRepositry;
import com.nagarro.accountmanagmentservice.service.AccountingService;

@Service
public class AccountingserviceImplementation implements AccountingService {

	@Autowired
	private AccountingRepositry accountingRepositry;
	
	@Autowired
	private TransactionRepositry transactionRepositry;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private RabbitMQProducer producer;
	
	@Override
	public HeaderResponse addAccount(AccountDetailsEntity accountDetailsEntity, String token) {
		AccountDetailsModel accountDetailsModel = new AccountDetailsModel();
		accountDetailsModel.setAccountingDetailModel(accountDetailsEntity);
		
		AccountDetailsModel checkingAccountForSimilarUser = accountingRepositry.findByUserId(accountDetailsModel.getUserId());
		
		HeaderResponse headerResponse = new HeaderResponse();
		if(checkingAccountForSimilarUser == null) {
			
			try {
		
				HeaderResponse webClientHeaderResponse = this.checkUser(accountDetailsModel.getUserId(), token);
				
				if(webClientHeaderResponse.isStatus()) {
					AccountDetailsModel savedAccountingDetailModel = accountingRepositry.save(accountDetailsModel);
					
					headerResponse.setStatus(true);
					headerResponse.setMsg("Account Created SuccessFully!!!");
					headerResponse.setData(savedAccountingDetailModel);
					
				} else {
					headerResponse.setStatus(false);
					headerResponse.setMsg("User Credentials Doesnt Match!!!!");	
					headerResponse.setData(webClientHeaderResponse);
				}
						
			} catch(WebClientResponseException e) {
				headerResponse.setStatus(false);
				headerResponse.setMsg("User Credentials Doesnt Match!!!! " + e);
			}
			
		} else {
			headerResponse.setStatus(false);
			headerResponse.setMsg("User Already have an Existing Account!!!");
			headerResponse.setData(checkingAccountForSimilarUser);
		}
		
		return headerResponse;
	}

	@Override
	public HeaderResponse transferMoney(TransactionEntity transactionEntity) {
		
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setTransactionModel(transactionEntity);
		
		HeaderResponse headerResponse = new HeaderResponse();
		if(transactionModel.getTransactionType().equalsIgnoreCase("Cash")) {
			if(
				transactionModel.getSenderAccountId().equalsIgnoreCase("Self") &&
				transactionModel.getRecieverAccountId().equalsIgnoreCase("Self")
			) {
				headerResponse.setStatus(false);
				headerResponse.setMsg("Invalid transactions!!!");
			} else if(transactionModel.getSenderAccountId().equalsIgnoreCase("Self")) {
				AccountDetailsModel recieverAccountModel = accountingRepositry.findById(transactionModel.getRecieverAccountId()).orElse(null);
				
				if(recieverAccountModel == null) {
					headerResponse.setStatus(false);
					headerResponse.setMsg("Transacation Failed!!!");
				} else {
					TransactionModel savedTransactionModel = transactionRepositry.save(transactionModel);
					
					recieverAccountModel.setBalance(recieverAccountModel.getBalance().add(savedTransactionModel.getAmount()));
					accountingRepositry.save(recieverAccountModel);
					
					headerResponse.setStatus(true);
					headerResponse.setMsg("Transaction Successfull");
					headerResponse.setData(savedTransactionModel);
				}
 			} else {
 				AccountDetailsModel senderAccountDetailsModel = accountingRepositry.findById(transactionModel.getSenderAccountId()).orElse(null);
 				
 				if(senderAccountDetailsModel == null) {
 					headerResponse.setStatus(false);
 					headerResponse.setMsg("Transaction Failed!!!");
 				} else {
					TransactionModel savedTransactionModel = transactionRepositry.save(transactionModel);
					
					senderAccountDetailsModel.setBalance(senderAccountDetailsModel.getBalance().subtract(savedTransactionModel.getAmount()));
					accountingRepositry.save(senderAccountDetailsModel);
					
					headerResponse.setStatus(true);
					headerResponse.setMsg("Transaction Successfull");
					headerResponse.setData(savedTransactionModel);
 				}
 			}
				
		} else {
			AccountDetailsModel senderAccountDetailsModel = accountingRepositry.findById(transactionModel.getSenderAccountId()).orElse(null);
			AccountDetailsModel reciverAccountDetailsModel = accountingRepositry.findById(transactionModel.getRecieverAccountId()).orElse(null);
			
			if(senderAccountDetailsModel == null || reciverAccountDetailsModel == null) {
				headerResponse.setStatus(false);
				headerResponse.setMsg("Invalid transaction!!!");
			} else {
				TransactionModel savedTransactionModel = transactionRepositry.save(transactionModel);
				
				senderAccountDetailsModel.setBalance(senderAccountDetailsModel.getBalance().subtract(savedTransactionModel.getAmount()));
				reciverAccountDetailsModel.setBalance(reciverAccountDetailsModel.getBalance().add(savedTransactionModel.getAmount()));
				
				accountingRepositry.save(senderAccountDetailsModel);
				accountingRepositry.save(reciverAccountDetailsModel);
				
				headerResponse.setStatus(true);
				headerResponse.setMsg("Transaction Successfull");
				headerResponse.setData(savedTransactionModel);
				
			}
		}
		
		return headerResponse;
	}

	public HeaderResponse getAccountDetails(String accountId) {
		
		HeaderResponse headerResponse = new HeaderResponse();
		
		AccountDetailsModel accountDetailsModel = accountingRepositry.findById(accountId).orElse(null);
		
		if(accountDetailsModel == null) {
			headerResponse.setStatus(false);
			headerResponse.setMsg("Account With Given Id Doesnt Exists!!");
		} else {

			headerResponse.setStatus(true);
			headerResponse.setMsg("Account Found!!");
			
			AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();
			accountDetailsEntity.setAccountDetailsEntity(accountDetailsModel);
			
			System.out.println("Sending Message!!!!!");
			producer.sendMessage(accountDetailsEntity);
			
			headerResponse.setData(accountDetailsEntity);
		}
		
		return headerResponse;
	}
	
	public HeaderResponse deleteAccountDetails(String accountId) {
		HeaderResponse headerResponse = new HeaderResponse();
		
		AccountDetailsModel accountDetailsModel = accountingRepositry.findById(accountId).orElse(null);
		
		if(accountDetailsModel == null) {
			headerResponse.setStatus(false);
			headerResponse.setMsg("No Such Account Exists!!");
		} else {
			accountingRepositry.deleteById(accountId);
			
			headerResponse.setStatus(true);
			headerResponse.setMsg("Account Deleted Successfully!!");
			headerResponse.setData(accountDetailsModel);
		}
		
		return headerResponse;
	}

	@Override
	public HeaderResponse deleteAccountByEmailId(String id) {
		
		HeaderResponse headerResponse = new HeaderResponse();
		
		AccountDetailsModel accountDetailsModel = accountingRepositry.findByUserId(id);
		
		if(accountDetailsModel == null) {
			headerResponse.setStatus(true);
			headerResponse.setMsg("User dont have any Existing Account!!!");
		} else {
			try {				
				accountingRepositry.deleteById(accountDetailsModel.getAccountId());
				
				headerResponse.setStatus(true);
				headerResponse.setMsg("Account Deleted Successfully!!");
				headerResponse.setData(accountDetailsModel);
			} catch(Exception e) {
				headerResponse.setStatus(false);
				headerResponse.setMsg("Error While Deleting Account. Please Try Again!!! " + e);
			}
		}
		
		return headerResponse;
		
	}

	
	public HeaderResponse checkUser(String userId, String token) {
		
		System.out.println("token: " + token);
		
		HeaderResponse webClientHeaderResponse = webClientBuilder.build().get()
				.uri("http://apigateway:8080/api/customer/getCustomer?id=" + userId)
				.header(HttpHeaders.AUTHORIZATION, token)
				.retrieve()
				.bodyToMono(HeaderResponse.class)
				.block();
		
		return webClientHeaderResponse;
	}
	
	
}
