package ntt.ntt_ms_customers.service.impl;

import lombok.RequiredArgsConstructor;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import ntt.ntt_ms_customers.mapper.CreateCustomerMapper;
import ntt.ntt_ms_customers.mapper.ListCustomerMapper;
import ntt.ntt_ms_customers.repository.CustomerRepository;
import ntt.ntt_ms_customers.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CreateCustomerMapper createCustomerMapper;

    public Flux<CustomerResponseDto> findAllCustomers() {
        return repository.findAll().map(ListCustomerMapper::listCustomers);
    }

    public Mono<CustomerResponseDto> findCustomerById(String id) {
        return repository.findById(id).map(ListCustomerMapper::listCustomers);
    }

    public Mono<CustomerResponseDto> saveCustomer(CustomerRequestDto customerRequestDto) {
        return repository.save(createCustomerMapper.toEntity(customerRequestDto))
                .map(ListCustomerMapper::listCustomers);
    }

    /*public Mono<Customer> saveCustomer(Customer customer){
        return repository.save(customer);
    }*/

    /*public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }*/

    /*public Mono<Customer> findById(String id) {
        return repository.findById(id);
    }



    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }*/
}
