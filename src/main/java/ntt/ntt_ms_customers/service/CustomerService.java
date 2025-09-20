package ntt.ntt_ms_customers.service;

import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<CustomerResponseDto> findAllCustomers();
    Mono<CustomerResponseDto> findCustomerById(String id);
    Mono<CustomerResponseDto> saveCustomer(CustomerRequestDto customer);

}
