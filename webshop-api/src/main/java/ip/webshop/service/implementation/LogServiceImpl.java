package ip.webshop.service.implementation;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ip.webshop.model.entity.Log;
import ip.webshop.model.enumeration.LogType;
import ip.webshop.repository.LogRepository;
import ip.webshop.service.LogService;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private  LogRepository logRepository;

    @Override
    public void writeLog( LogType type, String path, String message, Instant timestamp) {
        logRepository.save(new Log(  type, path, message, timestamp));
    }

}
