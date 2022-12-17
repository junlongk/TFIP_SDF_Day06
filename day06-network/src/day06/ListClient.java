package day06;

import java.net.Socket;

// import static day06.IOUtils.*;

public class ListClient {
    public static void main(String[] args) throws Exception{

        // Get the list size
        Integer nums = Integer.parseInt(args[0]);

        // Get the number range
        Integer range = Integer.parseInt(args[1]);

        // Get the host
        String host = args[2];

        // Get the port
        Integer port = Integer.parseInt(args[3]);

        // Create the socket to the server
        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s:%d\n", host, port);

        IOUtils.write(socket, "%d %d".formatted(nums, range));

        String response = IOUtils.read(socket);

        String[] randomNums = response.split(":");

        

        socket.close();

        // **** My half-done solution: ****

        // OutputStream os = socket.getOutputStream();
        // BufferedOutputStream bos = new BufferedOutputStream(os);
        // DataOutputStream dos = new DataOutputStream(bos);

        // String output = String.format("%d %d", nums, limit);
        // System.out.printf("Params to server: %s", output);
        // dos.writeUTF(output);
        // dos.flush();

        // dos.close();
        // bos.close();
        // os.close();

        // InputStream is = socket.getInputStream();
        // BufferedInputStream bis = new BufferedInputStream(is);
        // DataInputStream dis = new DataInputStream(bis);

        // String fromServer = dis.readUTF();
        // System.out.printf("Response from server: %s", fromServer);

        // dis.close();
        // bis.close();
        // is.close();
    }
}
