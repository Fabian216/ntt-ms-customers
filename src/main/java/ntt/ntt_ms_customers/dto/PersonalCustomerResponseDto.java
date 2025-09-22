package ntt.ntt_ms_customers.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PersonalCustomerResponseDto extends CustomerResponseDto {
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
}
