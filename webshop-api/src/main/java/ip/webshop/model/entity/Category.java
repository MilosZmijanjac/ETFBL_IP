package ip.webshop.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
@TypeDefs(@TypeDef(name = "json", typeClass = JsonBinaryType.class))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
        
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Attribute> specialAttributes;
}
