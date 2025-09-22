package ntt.ntt_ms_customers.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ntt.ntt_ms_customers.enums.CustomerSubType;
import ntt.ntt_ms_customers.enums.CustomerType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalCustomer.class, name = "PERSONAL"),
        @JsonSubTypes.Type(value = BusinessCustomer.class, name = "BUSINESS")
})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private CustomerType type;
    private CustomerSubType subType;
    private String email;
    private String phone;
    private String address;
}
