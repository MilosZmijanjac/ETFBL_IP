package ip.webshop.service;

import ip.webshop.model.entity.Pin;

public interface PinService {
    Pin createPin(Long id); 
    void sendPin(Pin pin,String email);
    Boolean validatePin(Long id,Integer pinCode);
}
