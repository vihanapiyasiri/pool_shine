package lk.ijse.model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderTm {
    private String Code;
    private String Description;
    private int Qty;
    private double Unit_Price;
    private double Total;
    private JFXButton Action;

}

