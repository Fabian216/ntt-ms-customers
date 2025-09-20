package ntt.ntt_ms_customers.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class BusinessCustomer extends Customer{
    private String companyName;
    private String ruc;
    @Size(min = 1, message = "se requiere al menos un titular")
    private List<Headlines> headlines;
    private List<AuthorizedSigner> authorizedSigners;
}
