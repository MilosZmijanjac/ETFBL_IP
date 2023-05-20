package ip.webshop.service;

import java.time.Instant;
import ip.webshop.model.enumeration.LogType;

public interface LogService {
    void writeLog(Long user_id,String username, LogType type,String path, String message, Instant timestamp);
}
