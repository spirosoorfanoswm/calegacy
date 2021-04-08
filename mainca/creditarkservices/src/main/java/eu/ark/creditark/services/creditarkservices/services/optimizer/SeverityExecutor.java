package eu.ark.creditark.services.creditarkservices.services.optimizer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeverityExecutor {
    ExecutorService executor = Executors.newWorkStealingPool();

    double accept(List<SeverityCallable> callables) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();
        double response = executor.invokeAll(callables).stream().mapToDouble(future -> {
            try {
                return future.get();
            }
            catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).sum();

        executor.shutdown();
        return response;
    }

}
