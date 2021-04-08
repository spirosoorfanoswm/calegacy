package eu.ark.creditark.services.creditarkservices.api;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.UtilService;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

@RestController
@RequestMapping(path="/util")
@CrossOrigin(origins = "*")
public class UtilityController {
    @Autowired
    private UtilService utilService;

    @Autowired
    private AppPropertiesConfig appProperties;

   @GetMapping("/logout")
   @CrossOrigin(origins = "*")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        request.logout();
        auth.eraseCredentials();
        request.getRequestURL();
        auth.setDetails(null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        response.sendRedirect(appProperties
                .getLogoutUrl()
                .concat(appProperties.getRedirectUrl().concat(request.getScheme().concat("://").concat(request.getServerName()).concat(":").concat(DtoGenUtils.numberToString(request.getServerPort())).concat("/")))
                .concat(appProperties.getResponseType())
                .concat(appProperties.getScopeType()));
       request.logout();


    }

    @GetMapping("/cache")
    @PreAuthorize("hasAnyAuthority('ROLE_CACHE')")
    @CrossOrigin(origins = "*")
    public void cache() throws Exception {
        utilService.evict();
        utilService.cache();
    }


    @GetMapping("/clearcache")
    @PreAuthorize("hasAnyAuthority('ROLE_CACHE')")
    @CrossOrigin(origins = "*")
    public void clearcache() throws Exception {
        utilService.evict();
    }

    public UtilService getUtilService() {
        return utilService;
    }

    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }

    public AppPropertiesConfig getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppPropertiesConfig appProperties) {
        this.appProperties = appProperties;
    }

}

