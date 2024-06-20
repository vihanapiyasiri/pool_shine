package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Item {
    String itemId;
    String name;
    String desc;
    String price;

    public Item(String itemId, String name, String description, String price) {
    }
}
