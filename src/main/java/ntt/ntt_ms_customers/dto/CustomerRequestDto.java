package ntt.ntt_ms_customers.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntt.ntt_ms_customers.enums.CustomerSubType;
import ntt.ntt_ms_customers.enums.CustomerType;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalCustomerRequestDto.class, name = "PERSONAL"),
        @JsonSubTypes.Type(value = BusinessCustomerRequestDto.class, name = "BUSINESS")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotNull
    private CustomerType type;
    private CustomerSubType subType;
    private String email;
    private String phone;
    private String address;
}
