package ip.webshop.service.implementation;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.SupportMessage;
import ip.webshop.model.request.SupportMessageRequest;
import ip.webshop.repository.SupportMessageRepository;
import ip.webshop.service.SupportMessageService;
@Service
public class SupportMessageServiceImpl implements SupportMessageService{

    @Autowired
    SupportMessageRepository supportMessageRepository;
    @Override
    public void addMessage(SupportMessageRequest message) {
        SupportMessage supportMessage=new SupportMessage();
        supportMessage.setUserMail(message.getUserMail());
        supportMessage.setUsername(message.getUsername());
        supportMessage.setText(message.getText());
        supportMessage.setSentTime(Instant.now());
        supportMessage.setRead(false);
        supportMessageRepository.save(supportMessage);
    }
    
}
