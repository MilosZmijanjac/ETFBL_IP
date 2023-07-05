package ip.webshop.model.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attribute implements Serializable{
    private String name;
    private String value;
    private String unit;
    private String possibleValues;    
    private Long categoryId;
}
