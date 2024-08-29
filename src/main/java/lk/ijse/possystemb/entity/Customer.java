package lk.ijse.possystemb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String id;
    private String name;
    private String gender;
    private String gmail;
    private int phoneNo;
}
