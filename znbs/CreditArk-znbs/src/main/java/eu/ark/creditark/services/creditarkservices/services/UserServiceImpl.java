package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean insertIfNotExists(String username) throws DatabaseConnectionException {
        logger.info("{}",username) ;
        return userRepository.insertIfNotExists(username);
    }
}
