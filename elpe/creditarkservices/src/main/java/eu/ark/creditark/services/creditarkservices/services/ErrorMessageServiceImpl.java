package eu.ark.creditark.services.creditarkservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id, String[] val) {
        return MessageFormat.format(messageSource.getMessage(id, null, null), val);
    }

}
