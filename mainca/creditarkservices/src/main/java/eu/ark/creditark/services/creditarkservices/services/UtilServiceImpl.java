package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UtilServiceImpl implements UtilService {
    @Autowired
    private CommonRepository commonRepository;
    @Autowired
    private AppPropertiesConfig appProperties;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void cache() throws DatabaseConnectionException {
        commonRepository.loadUsers();
    }
    @Override
    public void evict() {
        appProperties.getRegion().stream().forEach(cache -> {
            cacheManager.getCache(cache).clear();
        });
    }
    @Override
    @Cacheable(value = "applySearchPath", key = "#contextId")
    public String getSchema(short contextId) throws DatabaseConnectionException {
        return commonRepository.searchPath((short) contextId);
    }

    public CommonRepository getCommonRepository() {
        return commonRepository;
    }

    public void setCommonRepository(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    public AppPropertiesConfig getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppPropertiesConfig appProperties) {
        this.appProperties = appProperties;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
