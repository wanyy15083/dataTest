package com.data.test.concurrent.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by songyigui on 2017/6/1.
 */
public class AioEchoServer {
    public final static int PORT = 8000;
    private AsynchronousServerSocketChannel server;

    public AioEchoServer() throws IOException {
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("localhost", PORT));
    }

    public void start() {
        System.out.println("Server listen on " + PORT);
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println(Thread.currentThread().getName());
                Future<Integer> writeResult = null;
                try {
                    buffer.clear();
                    result.read(buffer).get(100, TimeUnit.SECONDS);
                    buffer.flip();
                    writeResult = result.write(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        server.accept(null, this);
                        Integer i = writeResult.get();
                        System.out.println(i);
                        result.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed: " + exc);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new AioEchoServer().start();
        while (true) {
            Thread.sleep(1000);
        }
    }
}
