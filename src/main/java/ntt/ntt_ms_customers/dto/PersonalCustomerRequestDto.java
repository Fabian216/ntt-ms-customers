package ntt.ntt_ms_customers.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalCustomerRequestDto extends CustomerRequestDto{
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
}
