package com.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {

        Random random = new Random();
        random.nextInt(1,7);
        UUID uuid = UUID.randomUUID();
        for (int i = 1; i <= 5; i++) {
            System.out.println("test = " +i);
        }

        CompletableFuture<Object> async = CompletableFuture.supplyAsync(()-> {
            return new Object();
        }).whenComplete((result, error)-> {
            if(error!=null) {
                throw new RuntimeException("das");
            } else{
                System.out.println("done");
            }
        });
//        async.get(5, TimeUnit.SECONDS);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenAccept(result -> {
            System.out.println("Result: " + result);
        }).thenApplyAsync(value -> {
            return value + " World";
            })
            .exceptionally(ex -> {
            System.out.println("Error: " + ex.getMessage());
            return null;
        });

        CompletableFuture<String> futureAccpt = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> voidFuture = future.thenAccept(s -> System.out.println(s + " World"));

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        CompletableFuture<Void> f = CompletableFuture.runAsync(() -> {
            System.out.println("Hello");
        }).thenRun(() -> {
            System.out.println("World");
        }).thenRun(() -> {
            System.out.println("!");
        });

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> ff = CompletableFuture.supplyAsync(()-> {
            return "tes";
        }).exceptionally(ex-> {
            return "ex";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> "as");
        CompletableFuture<String> other = CompletableFuture.supplyAsync(() -> "sds");
        CompletableFuture<String> stringCompletableFuture = cf.thenCombine(other, (s1, s2) -> s1 + s2);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            System.out.println("Hello");
        });
        Future<Integer> world = executorService.submit(() -> {
            System.out.println("World");
            return 1;
        });
        try {
            Integer i = world.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Thread t =new Thread(() -> {
            System.out.println("Hello");
        });
        t.start();

        String join = future.join();
        boolean s = future.complete("s");

    }

    private Map<String, Boolean> process() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10); // Adjust the pool size as needed
        List<CompletableFuture<Map<String, Boolean>>> futures = new ArrayList<>();

        List<Integer> listIDGroup = List.of(1,2,3);
        listIDGroup.forEach(listID -> {
            CompletableFuture<Map<String, Boolean>> future = CompletableFuture.supplyAsync(() -> getData(listID), threadPool)
                .exceptionally(ex-> {
                    return null;
                });
            futures.add(future);
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        CompletableFuture<Map<String, Boolean>> combinedFuture = allOf.thenApply(v -> futures.stream()
            .map(CompletableFuture::join)
            .filter(Objects::nonNull)
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (v1, v2) -> v2)));

        try {
            return combinedFuture.get(60, TimeUnit.SECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            combinedFuture.cancel(true);
            throw new RuntimeException("Timeout occurred: Failed to complete within 60 seconds", e);
        } finally {
            threadPool.shutdown();
        }
    }

    public Map<String, Boolean> getData(int IDS) {
        // Simulate data fetching logic
        return Map.of("exampleKey", true); // Placeholder return value
    }

//     for (var f : List.of(f1, f2, f3)) {
//        f.exceptionally(t -> {
//            combFuture.completeExceptionally(t);
//            return null;
//        });
//    }

}
