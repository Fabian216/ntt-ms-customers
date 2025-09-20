package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.*;
import ntt.ntt_ms_customers.entity.BusinessCustomer;
import ntt.ntt_ms_customers.entity.Customer;
import ntt.ntt_ms_customers.entity.PersonalCustomer;
import ntt.ntt_ms_customers.enums.CustomerType;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerMapper {

    public Customer toEntity(CustomerRequestDto customerRequestDto) {
        switch (customerRequestDto.getType()) {
            case PERSONAL:
                PersonalCustomerRequestDto p = (PersonalCustomerRequestDto) customerRequestDto;
                return PersonalCustomer.builder()
                        .type(CustomerType.PERSONAL)
                        .subType(p.getSubType())
                        .email(p.getEmail())
                        .phone(p.getPhone())
                        .address(p.getAddress())
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .documentType(p.getDocumentType())
                        .documentNumber(p.getDocumentNumber())
                        .build();

            case BUSINESS:
                BusinessCustomerRequestDto b = (BusinessCustomerRequestDto) customerRequestDto;
                return BusinessCustomer.builder()
                        .type(b.getType())
                        .subType(b.getSubType())
                        .email(b.getEmail())
                        .phone(b.getPhone())
                        .address(b.getAddress())
                        .companyName(b.getCompanyName())
                        .ruc(b.getRuc())
                        .headlines(b.getHeadlines())
                        .authorizedSigners(b.getAuthorizedSigners())
                        .build();
            default:
                throw new IllegalArgumentException("Tipo de cliente no soportado: " + customerRequestDto.getType());
        }
    }

}
