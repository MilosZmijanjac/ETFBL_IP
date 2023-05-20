package ip.webshop.service.implementation;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ip.webshop.model.entity.Pin;
import ip.webshop.repository.PinRepository;
import ip.webshop.service.PinService;

@Service
public class PinServiceImpl implements PinService {

    @Autowired
    PinRepository pinRepository;
    @Autowired
    JavaMailSender mailSender;

    @Override
    public Pin createPin(Long id) {
        Pin pin =new Pin();
        pin.setId(id);
        pin.setPinCode(generatePin());
        pinRepository.save(pin);

        return pin;
    }

    @Override
    public void sendPin(Pin pin, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Webshop pin code");
        message.setText("This is your random generated pin code: " + pin.getPinCode());

        mailSender.send(message);
    }

    @Override
    public Boolean validatePin(Long id, Integer pinCode) {
        Pin pin = pinRepository.findById(id).get();
        System.out.println(pin.getId());
        System.out.println(pin.getPinCode());

        return pin.getPinCode().equals(pinCode);
    }

    private static int generatePin() {

        int pin = new Random().nextInt(10000);

        if (pin < 1000) {
            pin += 1000;
        }

        return pin;
    }

}
