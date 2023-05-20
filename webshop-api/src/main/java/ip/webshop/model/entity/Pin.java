package ip.webshop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pins")
public class Pin {
    @Id
    private Long id;
    private Integer pinCode;
}
