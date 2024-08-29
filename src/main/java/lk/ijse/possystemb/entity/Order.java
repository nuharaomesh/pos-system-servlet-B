package lk.ijse.possystemb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String id;
    private float price;
    private String time;
    private int qty;
    private String cusID;
}
