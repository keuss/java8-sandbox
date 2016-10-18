package com.cgi.sandbox;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/**
 * Created by galloisg on 18/10/2016.
 */
public class Promises {

    /*
        Good Examples from : http://www.codebulb.ch/2015/07/completablefuture-clean-callbacks-with-java-8s-promises-part-1.html
     */


    public static CompletableFuture<String> findName(String param) {
        return CompletableFuture.supplyAsync(() -> {
            //...long running...
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
            if (param == null)
                throw new RuntimeException("Promise rejected !");
            return "Promise" + param;
        });
    }

    public static void main(String[] args) {
        System.out.println("#### PROMISES TEST ####");


        final CompletableFuture<String> retrieveName = CompletableFuture.supplyAsync(() -> {
            //...long running...
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
            return "Promise";
        });

        retrieveName.thenAccept(System.out::println);

        findName("keuss").thenAccept(System.out::println);

        // exceptionally gives us a chance to recover by taking an alternative function
        // that will be executed if preceding calculation fails with an exception.
        // This way succeeding callbacks can continue with the alternative result as input.
        findName(null)
                .exceptionally((e) -> {
                    System.out.println("Error " + e.getMessage());
                    return "empty";
                })
                .thenAccept(System.out::println);

        // whenComplete returns a new CompletionStage with the same result or exception as this stage,
        // that executes the given action when this stage completes.
        findName("yo")
                .whenComplete((it, err) -> {
                    if (it != null) {
                        System.out.println("whenComplete " + it);
                    } else {
                        System.out.println("whenComplete " + err);
                    }
                })
                .thenAccept(System.out::println);

        System.out.println("Non blocking ...");


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
