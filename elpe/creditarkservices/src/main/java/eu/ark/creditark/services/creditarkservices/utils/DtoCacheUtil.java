package eu.ark.creditark.services.creditarkservices.utils;

import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoCacheUtil {
    public static List<CustomersInputDto> createLoadCustomersRequests(List<Short> contexts,
                                                   List<Date> snapshotDates,
                                                   List<Short> portfolios) {
        List<CustomersInputDto> requests = new ArrayList<>();
        contexts.stream().forEach(context -> {
            portfolios.stream().forEach(portfolio -> {
                snapshotDates.stream().forEach(snapshotDate -> {
                    try {
                        requests.add(new CustomersInputDto(DateUtils.stringToDate(DateUtils.dateToString(snapshotDate,
                                GenerealConstants.DATE_FORMAT.getValue()),
                                GenerealConstants.DATE_FORMAT.getValue()),
                                context.shortValue(),
                                -1,
                                portfolio,
                                6,
                                true,
                                0,
                                100));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                });
            });
        });
        return requests;
    }
}
