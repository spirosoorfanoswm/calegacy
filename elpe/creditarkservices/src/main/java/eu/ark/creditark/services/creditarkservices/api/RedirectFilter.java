package eu.ark.creditark.services.creditarkservices.api;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RedirectFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    logger.info(" URI IS {}", httpServletRequest.getRequestURI());
    if (httpServletRequest.getRequestURI().contains("/ext/repository")) {
      if(httpServletRequest.getRequestURI().contains("configinfo")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/configinfo")
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("contextinfo")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/contextinfo")
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("clientelestatistics")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/clientelestatistics")
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("clientelestatisticsgraph")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/clientelestatisticsgraph")
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("clienteledistribution")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/clienteledistribution")
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("customers/search")) {

        httpServletRequest.getRequestDispatcher(httpServletRequest.getRequestURI())
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("customers/")) {

        httpServletRequest.getRequestDispatcher(httpServletRequest.getRequestURI())
            .forward(servletRequest,
                servletResponse);
      } else  if(httpServletRequest.getRequestURI().contains("customers")) {
        httpServletRequest.getRequestDispatcher("/ext/repository/customers")
            .forward(servletRequest,
                servletResponse);
      }


    } else {
      chain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {

  }


}