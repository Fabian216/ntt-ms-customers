package ntt.ntt_ms_customers.controller;

import lombok.RequiredArgsConstructor;
import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import ntt.ntt_ms_customers.service.BusinessCustomerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers/business")
@RequiredArgsConstructor
public class BusinessCustomerController {

    private final BusinessCustomerService businessService;

    @GetMapping
    public Flux<BusinessCustomerResponseDto> getAllBusinessCustomers() {
        return businessService.findAllBusinessCustomers();
    }

    @GetMapping("/{id}")
    public Mono<BusinessCustomerResponseDto> getBusinessCustomerById(@PathVariable String id) {
        return businessService.findBusinessCustomerById(id);
    }

}
