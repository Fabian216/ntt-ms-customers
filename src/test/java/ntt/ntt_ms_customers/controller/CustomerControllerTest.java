package ntt.ntt_ms_customers.controller;

import ntt.ntt_ms_customers.dto.CustomerRequestDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.enums.CustomerType;
import ntt.ntt_ms_customers.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CustomerService customerService;

    @Test
    void shouldGetAllCustomers() {
        PersonalCustomerResponseDto dto = PersonalCustomerResponseDto.builder()
                .id("1")
                .type(CustomerType.PERSONAL)
                .firstName("Juan")
                .lastName("Pérez")
                .documentType("DNI")
                .documentNumber("12345678")
                .build();

        Mockito.when(customerService.findAllCustomers()).thenReturn(Flux.just(dto));

        webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PersonalCustomerResponseDto.class)
                .hasSize(1)
                .consumeWith(result -> {
                    PersonalCustomerResponseDto response = result.getResponseBody().get(0);
                    assert response != null;
                    assert "Juan".equals(response.getFirstName());
                    assert "Pérez".equals(response.getLastName());
                    assert "DNI".equals(response.getDocumentType());
                    assert "12345678".equals(response.getDocumentNumber());
                });

    }

    @Test
    void shouldGetCustomerById() {
        PersonalCustomerResponseDto dto = PersonalCustomerResponseDto.builder()
                .id("2")
                .type(CustomerType.PERSONAL)
                .firstName("Ana")
                .lastName("López")
                .documentType("DNI")
                .documentNumber("87654321")
                .build();

        Mockito.when(customerService.findCustomerById("2")).thenReturn(Mono.just(dto));

        webTestClient.get()
                .uri("/customers/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonalCustomerResponseDto.class)
                .consumeWith(result -> {
                    PersonalCustomerResponseDto response = result.getResponseBody();
                    assert response != null;
                    assert "2".equals(response.getId());
                    assert "Ana".equals(response.getFirstName());
                    assert "López".equals(response.getLastName());
                    assert "DNI".equals(response.getDocumentType());
                    assert "87654321".equals(response.getDocumentNumber());
                });
    }

    @Test
    void shouldCreateCustomer() {
        CustomerRequestDto request = new CustomerRequestDto(CustomerType.PERSONAL, null, "mail@test.com", "999999999", "Calle Falsa 123");
        PersonalCustomerResponseDto response = PersonalCustomerResponseDto.builder()
                .id("3")
                .type(CustomerType.PERSONAL)
                .firstName("Luis")
                .lastName("Martínez")
                .documentType("DNI")
                .documentNumber("55555555")
                .build();

        Mockito.when(customerService.saveCustomer(any(CustomerRequestDto.class)))
                .thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/customers")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonalCustomerResponseDto.class)
                .consumeWith(result -> {
                    PersonalCustomerResponseDto body = result.getResponseBody();
                    assert body != null;
                    assert "3".equals(body.getId());
                    assert "Luis".equals(body.getFirstName());
                    assert "Martínez".equals(body.getLastName());
                    assert "DNI".equals(body.getDocumentType());
                    assert "55555555".equals(body.getDocumentNumber());
                });
    }

}