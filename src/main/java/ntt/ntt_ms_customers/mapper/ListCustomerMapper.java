package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import ntt.ntt_ms_customers.dto.CustomerResponseDto;
import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.entity.BusinessCustomer;
import ntt.ntt_ms_customers.entity.Customer;
import ntt.ntt_ms_customers.entity.PersonalCustomer;
import ntt.ntt_ms_customers.exception.UnsupportedCustomerTypeException;

public class ListCustomerMapper {

    public static CustomerResponseDto toResponseDto(Customer customer) {
        switch (customer.getType()) {
            case PERSONAL:
                PersonalCustomer p = (PersonalCustomer) customer;
                return PersonalCustomerResponseDto.builder()
                        .id(p.getId())
                        .type(p.getType())
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
                BusinessCustomer b = (BusinessCustomer) customer;
                return BusinessCustomerResponseDto.builder()
                        .id(b.getId())
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
                throw new UnsupportedCustomerTypeException("Tipo de cliente no soportado: " + customer.getType());
        }
    }

}
