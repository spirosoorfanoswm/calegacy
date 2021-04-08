package eu.ark.creditark.services.creditarkservices.jobs;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.ConcurrentService;
import eu.ark.creditark.services.creditarkservices.services.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
//@ConditionalOnProperty(value = "recur", havingValue = "true", matchIfMissing = false)
public class CacheJob {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UtilService utilService;

    @Autowired
    private Environment env;

    @Value("${app.enableScheduling}")
    private boolean enableScheduling;


    @Scheduled(cron = "${recur}")
    public void cache() {
        if(enableScheduling) {
            logger.info("Caching triggerred and started");
            try {
                utilService.evict();
                utilService.cache();
            } catch (DatabaseConnectionException e) {
                logger.error("Error during caching");
            }
        } else {
            logger.info("Caching triggerred but not started");
        }

    }
}
