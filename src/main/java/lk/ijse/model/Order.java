package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String Id;
    private String Date;
    private String Description;
    private String CustomerId;
    private String PaymentId;
}

