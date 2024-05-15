package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractOrder {

    private String Id;
    private String Date;
    private String Amount;
    private String CustomerId;
}