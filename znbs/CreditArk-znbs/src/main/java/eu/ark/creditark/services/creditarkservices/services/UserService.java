package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;

public interface UserService {

    public boolean insertIfNotExists(String username) throws DatabaseConnectionException;
}
