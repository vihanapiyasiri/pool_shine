package lk.ijse.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String id;
    private String Name;
    private String Address;
    private String Contact;
    private String paymentTerms;
}
