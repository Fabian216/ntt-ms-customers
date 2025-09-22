package ntt.ntt_ms_customers.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private String fullName;
    private String documentType;
    private String documentNumber;
}
