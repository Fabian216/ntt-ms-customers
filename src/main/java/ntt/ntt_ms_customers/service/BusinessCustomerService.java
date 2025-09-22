package ntt.ntt_ms_customers.service;

import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessCustomerService {

    Flux<BusinessCustomerResponseDto> findAllBusinessCustomers();

    Mono<BusinessCustomerResponseDto> findBusinessCustomerById(String id);

}
