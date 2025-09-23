package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.entity.*;
import ntt.ntt_ms_customers.enums.CustomerType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ListCustomerMapperTest {

    @Test
    void shouldMapPersonalCustomerToResponseDto() {
        // given
        PersonalCustomer personal = PersonalCustomer.builder()
                .id("123")
                .type(CustomerType.PERSONAL)
                .email("test@mail.com")
                .phone("999999999")
                .address("Av. Siempre Viva")
                .firstName("Juan")
                .lastName("Pérez")
                .documentType("DNI")
                .documentNumber("12345678")
                .build();

        // when
        CustomerResponseDto dto = ListCustomerMapper.toResponseDto(personal);

        // then
        assertTrue(dto instanceof PersonalCustomerResponseDto);
        PersonalCustomerResponseDto response = (PersonalCustomerResponseDto) dto;

        assertEquals("123", response.getId());
        assertEquals(CustomerType.PERSONAL, response.getType());
        assertEquals("test@mail.com", response.getEmail());
        assertEquals("Juan", response.getFirstName());
        assertEquals("Pérez", response.getLastName());
        assertEquals("DNI", response.getDocumentType());
        assertEquals("12345678", response.getDocumentNumber());
    }

    @Test
    void shouldMapBusinessCustomerToResponseDto() {
        // given
        Headlines headline = new Headlines();
        headline.setFullName("Carlos Jefe");
        headline.setDocumentType("DNI");
        headline.setDocumentNumber("87654321");

        AuthorizedSigner signer = new AuthorizedSigner();
        signer.setFullName("Luis Firmante");
        signer.setDocumentType("CE");
        signer.setDocumentNumber("11223344");

        BusinessCustomer business = BusinessCustomer.builder()
                .id("456")
                .type(CustomerType.BUSINESS)
                .email("empresa@mail.com")
                .phone("123456789")
                .address("Calle Falsa 123")
                .companyName("Empresa SAC")
                .ruc("20123456789")
                .headlines(Collections.singletonList(headline))
                .authorizedSigners(Collections.singletonList(signer))
                .build();

        // when
        CustomerResponseDto dto = ListCustomerMapper.toResponseDto(business);

        // then
        assertTrue(dto instanceof BusinessCustomerResponseDto);
        BusinessCustomerResponseDto response = (BusinessCustomerResponseDto) dto;

        assertEquals("456", response.getId());
        assertEquals(CustomerType.BUSINESS, response.getType());
        assertEquals("empresa@mail.com", response.getEmail());
        assertEquals("Empresa SAC", response.getCompanyName());
        assertEquals("20123456789", response.getRuc());

        assertNotNull(response.getHeadlines());
        assertEquals(1, response.getHeadlines().size());
        assertEquals("Carlos Jefe", response.getHeadlines().get(0).getFullName());

        assertNotNull(response.getAuthorizedSigners());
        assertEquals(1, response.getAuthorizedSigners().size());
        assertEquals("Luis Firmante", response.getAuthorizedSigners().get(0).getFullName());
    }

}