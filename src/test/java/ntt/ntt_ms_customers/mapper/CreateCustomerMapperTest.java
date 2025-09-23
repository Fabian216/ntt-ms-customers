package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.BusinessCustomerRequestDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerRequestDto;
import ntt.ntt_ms_customers.entity.*;
import ntt.ntt_ms_customers.enums.CustomerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerMapperTest {

    private CreateCustomerMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CreateCustomerMapper();
    }

    @Test
    void shouldMapPersonalCustomerRequestDtoToEntity() {
        // given
        PersonalCustomerRequestDto request = new PersonalCustomerRequestDto();
        request.setType(CustomerType.PERSONAL);
        request.setEmail("juan@mail.com");
        request.setPhone("999999999");
        request.setAddress("Av. Siempre Viva");
        request.setFirstName("Juan");
        request.setLastName("Pérez");
        request.setDocumentType("DNI");
        request.setDocumentNumber("12345678");

        // when
        Customer entity = mapper.toEntity(request);

        // then
        assertTrue(entity instanceof PersonalCustomer);
        PersonalCustomer personal = (PersonalCustomer) entity;

        assertEquals(CustomerType.PERSONAL, personal.getType());
        assertEquals("juan@mail.com", personal.getEmail());
        assertEquals("999999999", personal.getPhone());
        assertEquals("Av. Siempre Viva", personal.getAddress());
        assertEquals("Juan", personal.getFirstName());
        assertEquals("Pérez", personal.getLastName());
        assertEquals("DNI", personal.getDocumentType());
        assertEquals("12345678", personal.getDocumentNumber());
    }

    @Test
    void shouldMapBusinessCustomerRequestDtoToEntity() {
        // given
        Headlines headline = new Headlines();
        headline.setFullName("Carlos Roque");
        headline.setDocumentType("DNI");
        headline.setDocumentNumber("87654321");

        AuthorizedSigner signer = new AuthorizedSigner();
        signer.setFullName("Luis Loayza");
        signer.setDocumentType("CE");
        signer.setDocumentNumber("11223344");

        BusinessCustomerRequestDto request = new BusinessCustomerRequestDto();
        request.setType(CustomerType.BUSINESS);
        request.setEmail("empresa@mail.com");
        request.setPhone("123456789");
        request.setAddress("Calle Flores 123");
        request.setCompanyName("Empresa SAC");
        request.setRuc("20123456789");
        request.setHeadlines(Collections.singletonList(headline));
        request.setAuthorizedSigners(Collections.singletonList(signer));

        // when
        Customer entity = mapper.toEntity(request);

        // then
        assertTrue(entity instanceof BusinessCustomer);
        BusinessCustomer business = (BusinessCustomer) entity;

        assertEquals(CustomerType.BUSINESS, business.getType());
        assertEquals("empresa@mail.com", business.getEmail());
        assertEquals("123456789", business.getPhone());
        assertEquals("Calle Flores 123", business.getAddress());
        assertEquals("Empresa SAC", business.getCompanyName());
        assertEquals("20123456789", business.getRuc());

        assertNotNull(business.getHeadlines());
        assertEquals(1, business.getHeadlines().size());
        assertEquals("Carlos Roque", business.getHeadlines().get(0).getFullName());

        assertNotNull(business.getAuthorizedSigners());
        assertEquals(1, business.getAuthorizedSigners().size());
        assertEquals("Luis Loayza", business.getAuthorizedSigners().get(0).getFullName());
    }

}