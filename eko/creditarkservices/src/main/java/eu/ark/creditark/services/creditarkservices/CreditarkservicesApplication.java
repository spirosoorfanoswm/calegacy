package eu.ark.creditark.services.creditarkservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextListener;

import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAsync
public class CreditarkservicesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CreditarkservicesApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean(name = "concurrentTaskExecutor")
	public TaskExecutor taskExecutor () {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2000);
		executor.initialize();
		return executor;
	}

	@Bean(name = "concurrentTaskExecutorDb")
	public TaskExecutor taskExecutorDb () {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2000);
		executor.initialize();
		return executor;
	}


	@Bean(name = "deleteScenarioTaskExecutorDb")
	public TaskExecutor deleteScenarioTaskExecutorDb () {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2000);
		executor.initialize();
		return executor;
	}


	@Bean(name = "createCustomersReportTestExecutor")
	public TaskExecutor createCustomersReport () {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2000);
		executor.initialize();
		return executor;
	}


}
