package ntt.ntt_ms_customers.service.impl;

import lombok.RequiredArgsConstructor;
import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import ntt.ntt_ms_customers.mapper.BusinessCustomerMapper;
import ntt.ntt_ms_customers.repository.BusinessCustomerRepository;
import ntt.ntt_ms_customers.service.BusinessCustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BusinessCustomerServiceImpl implements BusinessCustomerService {

    private final BusinessCustomerRepository repository;

    public Flux<BusinessCustomerResponseDto> findAllBusinessCustomers() {
        return repository.findByType("BUSINESS")
                .map(BusinessCustomerMapper::toResponse);
    }

    public Mono<BusinessCustomerResponseDto> findBusinessCustomerById(String id) {
        return repository.findById(id)
                .map(BusinessCustomerMapper::toResponse);
    }

}
