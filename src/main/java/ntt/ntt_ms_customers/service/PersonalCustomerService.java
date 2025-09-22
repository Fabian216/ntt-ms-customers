package ntt.ntt_ms_customers.service;

import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalCustomerService {

    public Flux<PersonalCustomerResponseDto> finAllPersonalCustomers();

    public Mono<PersonalCustomerResponseDto> findPersonalCustomerById(String id);

}
