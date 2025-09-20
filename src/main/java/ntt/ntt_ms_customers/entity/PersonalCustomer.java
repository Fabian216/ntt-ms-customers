package ntt.ntt_ms_customers.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PersonalCustomer extends Customer{
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
}
