package ip.webshop.model.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ip.webshop.model.enumeration.LogType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Enumerated(EnumType.STRING)
    private LogType type;
    private String path;
    private String message;
    private Instant timestamp;

    public Log(LogType type,String path, String message, Instant timestamp) {
        this.type = type;
        this.path=path;
        this.message = message;
        this.timestamp = timestamp;
    }

}
