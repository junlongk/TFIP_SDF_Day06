package day06;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedListServer {
    public static void main(String[] args) throws Exception {
        // Get the port
        Integer port = Integer.parseInt(args[0]);

        // Create executor thread pool
        ExecutorService threadpool = Executors.newFixedThreadPool(3);

        // Create a ServerSocket and bind to the port
        ServerSocket server = new ServerSocket(port);
        System.out.printf("Listening on port %d\n", port);

        // Server loop
        while(true) {
            // Wait for a connection
            System.out.printf("Waiting for connections...\n");
            Socket socket = server.accept();

            // Creates a HandleThread to handle the client socket
            HandleThread thr = new HandleThread(socket);
            // Do not do this. THIS IS NOT A THREAD
            // thr.run();

            // Submit the Runnable to the threadpool
            threadpool.submit(thr);
        }
    }
}
