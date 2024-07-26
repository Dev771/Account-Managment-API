package com.nagarro.customermanagmentservice.serviceImplementation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.common.net.HttpHeaders;
import com.nagarro.customermanagmentservice.Producer.RabbitMQProducer;
import com.nagarro.customermanagmentservice.dto.CustomerDTO;
import com.nagarro.customermanagmentservice.dto.HeaderResponse;
import com.nagarro.customermanagmentservice.dto.JwtRequest;
import com.nagarro.customermanagmentservice.dto.JwtResponse;
import com.nagarro.customermanagmentservice.model.CustomerModel;
import com.nagarro.customermanagmentservice.repositry.CustomerRepositry;
import com.nagarro.customermanagmentservice.service.CustomerService;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepositry customerRepositry;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private RabbitMQProducer producer;
	
	@Override
	public HeaderResponse addCustomer(CustomerDTO customerDto) throws IllegalArgumentException {
		if(customerDto.getEmail() == null) {
			throw new IllegalArgumentException("InValid user Details!!");
		}
		
		CustomerModel customerWithSimilarDetails = customerRepositry.findByEmail(customerDto.getEmail());

		HeaderResponse headerResponse = new HeaderResponse();
		
		if(customerWithSimilarDetails == null) {
			CustomerModel customerModel = new CustomerModel();
			customerModel.setCustomerModel(customerDto);
		
			JwtRequest jwtRequest = new JwtRequest();
			jwtRequest.setEmail(customerDto.getEmail());
			jwtRequest.setPassword(customerDto.getPassword());
			jwtRequest.setType("register");
			
			
			try {
				JwtResponse jwtReponse = webClientBuilder.build().post()
						.uri("http://apigateway:8080/auth/Customer/login")
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(jwtRequest))
						.retrieve()
						.bodyToMono(JwtResponse.class)
						.block();

//				System.out.println(jwtReponse.getJwtToken());
				producer.sendMessage(jwtReponse.getJwtToken());
				
				CustomerModel savedCustomerModel = customerRepositry.save(customerModel);
				
				CustomerDTO savedCustomerDto = new CustomerDTO();
				savedCustomerDto.setCustomerDTO(savedCustomerModel);
				
				headerResponse.setStatus(true);
				headerResponse.setMsg(jwtReponse.getJwtToken());
				
				headerResponse.setData(savedCustomerDto);
				
			} catch(Exception e) {
				System.out.println(e);
				headerResponse.setStatus(false);
				headerResponse.setMsg("Error While Processig Request!!!");
			}
			
			
		} else {
			headerResponse.setStatus(false);
			headerResponse.setMsg("Customer Already Exists with Given Credentials!!!");
		}
		
		return headerResponse;
	}
	
	public List<CustomerDTO> getAllCustomers() {
		List<CustomerModel> customerModelList = customerRepositry.findAll();
		
		List<CustomerDTO> customerDtoList = customerModelList.stream().map(customerModel -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerDTO(customerModel);
			
			return customerDTO;
		}).toList();
		
		return customerDtoList;
	}

	public HeaderResponse getCustomerDetails(String id) {
		CustomerModel customerModel = customerRepositry.findById(id).orElse(null);
		
		HeaderResponse headerResponse = new HeaderResponse();
		
		if(customerModel == null) {
			headerResponse.setStatus(false);
			headerResponse.setMsg("No Customer Found with Given Credentials!!!");
		} else {
			headerResponse.setStatus(true);
			headerResponse.setMsg("Customer Found SuccessFully!!!");
			
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerDTO(customerModel);
			
			headerResponse.setData(customerDTO);
		}
		
		return headerResponse;
	}
	
	@Override
	public HeaderResponse updateCustomer(CustomerDTO customerDTO) {
		
		HeaderResponse headerResponse = new HeaderResponse();
		
//		Boolean isAuthenticated = webClientBuilder.build().get()
//			.uri("http://localhost:8080/auth/Customer/Validate/"+ token)
//			.retrieve()
//			.bodyToMono(Boolean.class)
//			.block();
		
		CustomerModel customerModel = new CustomerModel();
		customerModel.setCustomerModel(customerDTO);
		
		CustomerModel foundCustomer = customerRepositry.findById(customerModel.getId()).orElse(null);
		
		if(foundCustomer == null) {
			headerResponse.setStatus(false);
			headerResponse.setMsg("No such User Exists!!!");
		} else {
			
			foundCustomer.updateCustomerModel(customerModel);
			if(customerModel.getOfficialDocumentID() != null) {
				foundCustomer.setAuthorized(true);
			}
			
			CustomerModel savedCustomerModel = customerRepositry.save(foundCustomer);
		
			headerResponse.setStatus(true);
			headerResponse.setMsg("User Saved Successfully!!!");
			headerResponse.setData(savedCustomerModel);
		}
	
		return headerResponse;
	}
	
	public HeaderResponse deleteCustomer(String id, String token) {
		
		CustomerModel customerModel = customerRepositry.findById(id).orElse(null);
		
		HeaderResponse headerResponse = new HeaderResponse();
		
		if(customerModel == null) {
			headerResponse.setStatus(false);
			headerResponse.setMsg("No Such Customer Avialble");
		} else {

			try {				
				HeaderResponse webClientHeaderResponse = webClientBuilder.build().delete()
						.uri("http://apigateway:8080/api/accounts/user/" + customerModel.getId())
						.header(HttpHeaders.AUTHORIZATION, token)
						.retrieve()
						.bodyToMono(HeaderResponse.class)
						.block();
				
				HeaderResponse webClientAuthHeaderResponse = webClientBuilder.build().delete()
						.uri("http://apigateway:8080/auth/Customer/"+customerModel.getEmail())
						.header(HttpHeaders.AUTHORIZATION, token)
						.retrieve()
						.bodyToMono(HeaderResponse.class)
						.block();
				
				if(webClientHeaderResponse.isStatus()) {				
					customerRepositry.deleteById(id);
					
					headerResponse.setStatus(true);
					headerResponse.setMsg("Customer Removed Successfully!!!");
					
					headerResponse.setData(customerModel);
				} else {
					headerResponse.setStatus(false);
					headerResponse.setMsg("Unable To Delete Customer. Please try Again!!");
				}
				
			} catch(WebClientResponseException e) {
				headerResponse.setStatus(false);
				headerResponse.setMsg("Unable To Delete Customer. Please try Again!!");
			}
		}
		
		return headerResponse;
		
	}
	
}
