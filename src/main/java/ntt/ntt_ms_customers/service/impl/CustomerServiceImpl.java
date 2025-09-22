package ntt.ntt_ms_customers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import ntt.ntt_ms_customers.exception.CustomerNotFoundException;
import ntt.ntt_ms_customers.mapper.CreateCustomerMapper;
import ntt.ntt_ms_customers.mapper.ListCustomerMapper;
import ntt.ntt_ms_customers.repository.CustomerRepository;
import ntt.ntt_ms_customers.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CreateCustomerMapper createCustomerMapper;

    public Flux<CustomerResponseDto> findAllCustomers() {
        log.info("Iniciando findAllCustomers.");
        return repository.findAll()
                .map(ListCustomerMapper::toResponseDto)
                .doOnComplete(() -> log.info("Clientes encontrados exitosamente."))
                .doOnError(e -> log.error("Error al buscar clientes.", e));
    }

    public Mono<CustomerResponseDto> findCustomerById(String id) {
        log.info("Iniciando findCustomerById id= {}", id);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .map(ListCustomerMapper::toResponseDto)
                .doOnSuccess(customer -> log.info("Cliente con id: {} encontrado con exito", customer.getId()));
    }

    public Mono<CustomerResponseDto> saveCustomer(CustomerRequestDto customerRequestDto) {
        log.info("Iniciando saveCustomer.");
        return repository.save(createCustomerMapper.toEntity(customerRequestDto))
                .map(ListCustomerMapper::toResponseDto)
                .doOnSuccess(customer -> log.info("Cliente creado exitosamente"))
                .doOnError(e -> log.error("Fallo al crear cliente.", e));
    }

}
