package ntt.ntt_ms_customers.mapper;

import ntt.ntt_ms_customers.dto.BusinessCustomerResponseDto;
import ntt.ntt_ms_customers.entity.BusinessCustomer;

public class BusinessCustomerMapper {

    public static BusinessCustomerResponseDto toResponse(BusinessCustomer entity) {
        return BusinessCustomerResponseDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .subType(entity.getSubType())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .companyName(entity.getCompanyName())
                .ruc(entity.getRuc())
                .headlines(entity.getHeadlines())
                .authorizedSigners(entity.getAuthorizedSigners())
                .build();
    }

}
