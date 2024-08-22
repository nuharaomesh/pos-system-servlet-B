package lk.ijse.possystemb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements Serializable {
    private String id;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    private String img;
}
