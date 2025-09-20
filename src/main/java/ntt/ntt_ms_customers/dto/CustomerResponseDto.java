package ntt.ntt_ms_customers.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ntt.ntt_ms_customers.enums.CustomerSubType;
import ntt.ntt_ms_customers.enums.CustomerType;

import javax.validation.constraints.NotBlank;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalCustomerResponseDto.class, name = "PERSONAL"),
        @JsonSubTypes.Type(value = BusinessCustomerResponseDto.class, name = "BUSINESS")
})
@Getter
@Setter
@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomerResponseDto {
    private String id;
    @NotBlank
    private CustomerType type;
    private CustomerSubType subType;
    private String email;
    private String phone;
    private String address;
}
