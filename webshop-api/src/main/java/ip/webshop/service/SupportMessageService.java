package ip.webshop.service;

import ip.webshop.model.request.SupportMessageRequest;

public interface SupportMessageService {
    void addMessage(SupportMessageRequest message);
}
