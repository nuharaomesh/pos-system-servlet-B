package lk.ijse.possystemb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String id;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    private String img;
}
