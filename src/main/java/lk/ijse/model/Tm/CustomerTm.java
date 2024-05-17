package lk.ijse.model.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerTm extends PaymentTm {
    private String Id;
    private String Name;
    private String Address;
    private String Contact;
}
