package day06;

import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HandleThread implements Runnable {
    private Socket socket;

    // Random number generator
    Random rnd = new SecureRandom();

    public HandleThread (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();

            System.out.printf("New connection on port %d on thread %s\n", socket.getPort(), threadName);

            Thread.sleep(2000);

            String payload = IOUtils.read(socket);
            String[] values = payload.split(" ");
    
            Integer count = Integer.parseInt(values[0]);
            Integer range = Integer.parseInt(values[1]);
    
            List<Integer> randNums = new LinkedList<>();
            for (Integer i = 0; i < count; i++)
                randNums.add(rnd.nextInt(range));
    
            String response = randNums.stream()
                .map(v -> v.toString())
                .collect(Collectors.joining(":"));
    
            IOUtils.write(socket, response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
