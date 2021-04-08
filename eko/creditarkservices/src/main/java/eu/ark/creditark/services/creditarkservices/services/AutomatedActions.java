package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface AutomatedActions {
    public void systemScenario(List<Short> contexts, List<Date> snapshotDates);

}
