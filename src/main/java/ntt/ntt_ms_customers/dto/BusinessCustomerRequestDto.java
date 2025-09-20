package ntt.ntt_ms_customers.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ntt.ntt_ms_customers.entity.AuthorizedSigner;
import ntt.ntt_ms_customers.entity.Headlines;
import ntt.ntt_ms_customers.enums.CustomerSubType;
import ntt.ntt_ms_customers.enums.CustomerType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCustomerRequestDto extends CustomerRequestDto{
    private String companyName;
    private String ruc;
    private List<Headlines> headlines;
    private List<AuthorizedSigner> authorizedSigners;
}
