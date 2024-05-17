package lk.ijse.model.Tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTm {
    private String  PaymentId;
    private String Date;
    private String Amount;
    private String Method;

}
