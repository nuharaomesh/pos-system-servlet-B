package lk.ijse.possystemb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private String orderID;
    private String itemID;
    private float price;
    private int count;
}
