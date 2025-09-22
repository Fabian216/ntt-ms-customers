package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.PersonalCustomerResponseDto;
import ntt.ntt_ms_customers.entity.PersonalCustomer;

public class PersonalCustomerMapper {

    public static PersonalCustomerResponseDto toResponse(PersonalCustomer entity) {
        return PersonalCustomerResponseDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .subType(entity.getSubType())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .build();
    }

}
