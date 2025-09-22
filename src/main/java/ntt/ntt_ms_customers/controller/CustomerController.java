package ntt.ntt_ms_customers.controller;

import lombok.RequiredArgsConstructor;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import ntt.ntt_ms_customers.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Flux<CustomerResponseDto> getAll() {
        return customerService.findAllCustomers();
    }

    @GetMapping("{id}")
    public Mono<CustomerResponseDto> getCustomerById(@PathVariable String id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public Mono<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto requestDto) {
        return customerService.saveCustomer(requestDto);
    }

}
