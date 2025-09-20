package ntt.ntt_ms_customers.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ntt.ntt_ms_customers.entity.AuthorizedSigner;
import ntt.ntt_ms_customers.entity.Headlines;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BusinessCustomerResponseDto extends CustomerResponseDto {
    private String companyName;
    private String ruc;
    private List<Headlines> headlines;
    private List<AuthorizedSigner> authorizedSigners;
}
