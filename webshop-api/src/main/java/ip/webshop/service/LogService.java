package ip.webshop.service;

import java.time.Instant;
import ip.webshop.model.enumeration.LogType;

public interface LogService {
    void writeLog( LogType type,String path, String message, Instant timestamp);
}
