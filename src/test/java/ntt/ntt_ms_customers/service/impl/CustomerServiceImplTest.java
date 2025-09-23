package ntt.ntt_ms_customers.service.impl;

import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerRequestDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.entity.PersonalCustomer;
import ntt.ntt_ms_customers.enums.CustomerType;
import ntt.ntt_ms_customers.exception.CustomerNotFoundException;
import ntt.ntt_ms_customers.mapper.CreateCustomerMapper;
import ntt.ntt_ms_customers.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class CustomerServiceImplTest {

    private CustomerRepository repository;
    private CreateCustomerMapper createCustomerMapper;
    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        createCustomerMapper = mock(CreateCustomerMapper.class);
        service = new CustomerServiceImpl(repository, createCustomerMapper);
    }

    @Test
    void findAllCustomers_shouldReturnFluxOfCustomers() {
        // given
        PersonalCustomer entity = PersonalCustomer.builder()
                .id("1")
                .type(CustomerType.PERSONAL)
                .firstName("Juan")
                .lastName("Pérez")
                .documentType("DNI")
                .documentNumber("12345678")
                .build();

        when(repository.findAll()).thenReturn(Flux.just(entity));

        // when
        Flux<CustomerResponseDto> result = service.findAllCustomers();

        // then
        StepVerifier.create(result)
                .expectNextMatches(dto -> dto instanceof PersonalCustomerResponseDto &&
                        "Juan".equals(((PersonalCustomerResponseDto) dto).getFirstName()))
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
    void findCustomerById_shouldReturnCustomerWhenExists() {
        // given
        PersonalCustomer entity = PersonalCustomer.builder()
                .id("2")
                .type(CustomerType.PERSONAL)
                .firstName("Ana")
                .lastName("López")
                .documentType("DNI")
                .documentNumber("87654321")
                .build();

        when(repository.findById("2")).thenReturn(Mono.just(entity));

        // when
        Mono<CustomerResponseDto> result = service.findCustomerById("2");

        // then
        StepVerifier.create(result)
                .expectNextMatches(dto -> dto instanceof PersonalCustomerResponseDto &&
                        "Ana".equals(((PersonalCustomerResponseDto) dto).getFirstName()))
                .verifyComplete();

        verify(repository, times(1)).findById("2");
    }

    @Test
    void findCustomerById_shouldThrowWhenNotFound() {
        // given
        when(repository.findById("99")).thenReturn(Mono.empty());

        // when
        Mono<CustomerResponseDto> result = service.findCustomerById("99");

        // then
        StepVerifier.create(result)
                .expectErrorMatches(e -> e instanceof CustomerNotFoundException &&
                        e.getMessage().contains("99"))
                .verify();

        verify(repository, times(1)).findById("99");
    }

    @Test
    void saveCustomer_shouldMapAndSaveCorrectly() {
        // given
        PersonalCustomerRequestDto request = new PersonalCustomerRequestDto();
        request.setType(CustomerType.PERSONAL);
        request.setFirstName("Luis");
        request.setLastName("Martínez");
        request.setDocumentType("DNI");
        request.setDocumentNumber("55555555");

        PersonalCustomer entity = PersonalCustomer.builder()
                .id("3")
                .type(CustomerType.PERSONAL)
                .firstName("Luis")
                .lastName("Martínez")
                .documentType("DNI")
                .documentNumber("55555555")
                .build();

        when(createCustomerMapper.toEntity(any(CustomerRequestDto.class))).thenReturn(entity);
        when(repository.save(any(PersonalCustomer.class))).thenReturn(Mono.just(entity));

        // when
        Mono<CustomerResponseDto> result = service.saveCustomer(request);

        // then
        StepVerifier.create(result)
                .expectNextMatches(dto -> dto instanceof PersonalCustomerResponseDto &&
                        "Luis".equals(((PersonalCustomerResponseDto) dto).getFirstName()))
                .verifyComplete();

        verify(createCustomerMapper, times(1)).toEntity(request);
        verify(repository, times(1)).save(entity);
    }

}