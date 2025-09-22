package ntt.ntt_ms_customers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.mapper.PersonalCustomerMapper;
import ntt.ntt_ms_customers.repository.PersonalCustomerRepository;
import ntt.ntt_ms_customers.service.PersonalCustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonalCustomersServiceImpl implements PersonalCustomerService {

    private final PersonalCustomerRepository repository;

    public Flux<PersonalCustomerResponseDto> finAllPersonalCustomers() {
        return repository.findByType("PERSONAL")
                .map(PersonalCustomerMapper::toResponse);
    }

    public Mono<PersonalCustomerResponseDto> findPersonalCustomerById(String id) {
        return repository.findById(id).map(PersonalCustomerMapper::toResponse);
    }

}
