import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var result = CompletableFuture.supplyAsync(Main::getFirst).
                thenAcceptBoth(CompletableFuture.supplyAsync(Main::getSecond),
                        (first, second) -> System.out.println("First result: " + first + ". Second result: " + second));

        System.out.println("Wait for asynchronous function...");
        while (true) {
            if(result.isDone()) {
                break;
            }
        }
    }

    private static String getFirst() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "One";
    }

    private static String getSecond() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Two";
    }
}

