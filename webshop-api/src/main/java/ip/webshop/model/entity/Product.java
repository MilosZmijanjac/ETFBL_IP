package ip.webshop.model.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import ip.webshop.model.enumeration.ProductState;
import ip.webshop.model.enumeration.ProductStatus;
import lombok.Data;

@Data
@Entity
@Table(name="products")
@TypeDefs(@TypeDef(name = "json", typeClass = JsonBinaryType.class))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String imagesPath;
    private String address;
    private String city;
    private String country;
    
    private Instant creationTimestamp;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Enumerated(EnumType.STRING)
    private ProductState state;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Attribute> extendedAttributes;
    private Long userId;
    private Long categoryId;
}
