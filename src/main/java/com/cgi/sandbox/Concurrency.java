package com.cgi.sandbox;

import org.apache.commons.io.IOUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by galloisg on 17/10/2016.
 */
public class Concurrency {

    private static AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

    // Some tests from http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
    // Doc : https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
    // Others good links : http://www.deadcoderising.com/java8-writing-asynchronous-code-with-completablefuture/
    // and http://blog.octo.com/java-8-est-reactif/
    // Async Http Client library purpose is to allow Java applications to easily execute HTTP requests and asynchronously process the HTTP responses.
    // AsyncHttpClient/async-http-client => https://github.com/AsyncHttpClient/async-http-client

    /*
        Consumer<T> : opération qui accepte un unique argument (type T) et ne retourne pas de résultat.
            void accept(T);
        Function<T,R> : opération qui accepte un argument (type T) et retourne un résultat (type R).
            R apply(T);
        Supplier<T> : opération qui ne prend pas d’argument et qui retourne un résultat (type T).
            T get();
     */

    public static CompletableFuture<Reader> getURLAsync(String url) {
        CompletableFuture<Reader> rc = new CompletableFuture<>();
        AsyncCompletionHandler handler = new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                rc.complete(new InputStreamReader(response.getResponseBodyAsStream(), "UTF-8"));
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
                rc.completeExceptionally(t);
            }
        };
        asyncHttpClient.prepareGet(url).execute(handler);
        return rc;
    }

    public static String longRunningTask(String text) {

        //...long running...
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e);
        }

        return "#################### OK " + text;
    }

    public static void longRunningTaskAsync(String text) {
        CompletableFuture.runAsync(() -> {
            //...long running...
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
            System.out.println(text);
        });
    }

    public static void main(String[] args) {
        System.out.println("#### CONCURRENCY TEST ####");

        longRunningTaskAsync("foo");
        System.out.println("#### TEST CompletableFuture ####");

        // TEST CompletableFuture

        // 4 secondes de chargement
        CompletableFuture<String> page = getURLAsync("http://httpbin.org/delay/4").thenApply((in) -> {
            try {
                return IOUtils.toString(in);
            } catch (Exception e) {
                return "Exception getURLAsync";
            }
        });

        System.out.println("#################### Non blocking 1 ...");
        // ... execution d'autres traitements
        // pendant le chargement de la page ...

        // supplyAsync takes a Supplier containing the code we want to execute asynchronously
        CompletableFuture.supplyAsync(() -> longRunningTask("YO PARAM")).thenAccept(System.out::println);
        System.out.println("#################### Non blocking 2 ...");

        // Lecture bloquante de la page
        System.out.println("#################### page.get() ...");
        try {
            // Comme la méthode get() est bloquante, la fonction println() n’est pas exécutée tous de suite.
            System.out.println(page.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // LOGS
        /*
        #### CONCURRENCY TEST ####
        16:12:25.905 [main] DEBUG io.netty.util.internal.ThreadLocalRandom - -Dio.netty.initialSeedUniquifier: 0x31a41242a2609605 (took 0 ms)
        16:12:25.925 [main] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.allocator.type: unpooled
        16:12:25.925 [main] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.threadLocalDirectBufferSize: 65536
        16:12:25.925 [main] DEBUG io.netty.buffer.ByteBufUtil - -Dio.netty.maxThreadLocalCharBufferSize: 16384
        #################### Non blocking 1 ...
        #################### Non blocking 2 ...
        #################### page.get() ...
        16:12:25.965 [AsyncHttpClient-2-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.bytebuf.checkAccessible: true
        16:12:25.965 [AsyncHttpClient-2-1] DEBUG io.netty.util.ResourceLeakDetectorFactory - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@41a0aa37
        16:12:25.971 [AsyncHttpClient-2-1] DEBUG io.netty.util.internal.JavassistTypeParameterMatcherGenerator - Generated: io.netty.util.internal.__matchers__.io.netty.handler.codec.http.HttpObjectMatcher
        16:12:25.984 [AsyncHttpClient-2-1] DEBUG org.asynchttpclient.netty.channel.NettyConnectListener - Using new Channel '[id: 0x573094f2, L:/0:0:0:0:0:0:0:1:58105 - R:httpbin.org/54.175.219.8:80]' for 'GET' to '/delay/4'
        16:12:25.988 [AsyncHttpClient-2-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacity.default: 32768
        16:12:25.988 [AsyncHttpClient-2-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxSharedCapacityFactor: 2
        16:12:25.988 [AsyncHttpClient-2-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.linkCapacity: 16
        16:12:25.988 [AsyncHttpClient-2-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
        16:12:26.738 [pool-1-thread-1] DEBUG org.asynchttpclient.netty.channel.DefaultChannelPool - Closed 0 connections out of 0 in 0 ms
        16:12:27.836 [pool-1-thread-1] DEBUG org.asynchttpclient.netty.channel.DefaultChannelPool - Closed 0 connections out of 0 in 0 ms
        #################### OK YO PARAM
        16:12:28.937 [pool-1-thread-1] DEBUG org.asynchttpclient.netty.channel.DefaultChannelPool - Closed 0 connections out of 0 in 0 ms
        16:12:30.038 [pool-1-thread-1] DEBUG org.asynchttpclient.netty.channel.DefaultChannelPool - Closed 0 connections out of 0 in 0 ms
        16:12:30.247 [AsyncHttpClient-2-1] DEBUG org.asynchttpclient.netty.handler.HttpHandler -

        Request DefaultFullHttpRequest(decodeResult: success, version: HTTP/1.1, content: EmptyByteBufBE)
        GET /delay/4 HTTP/1.1
            ... la suite du contenu de la page ...
        */

    }
}
