package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String dirPath = "data2";
        
        // instantiate a file/directory object
        File newDirectory = new File(dirPath);

        // if directory exists, print to console 'directory exists' message
        // else create the directory
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        Cookie cookie = new Cookie();
        cookie.readCookieFile();
        cookie.showCookies();

        ServerSocket ss = new ServerSocket(7000);
        Socket s = ss.accept(); // establish connection and wait for client to connect

        try (InputStream is = s.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String msgReceived = "";

            while(!msgReceived.equals("close")) {
                msgReceived = dis.readUTF();

                if (msgReceived.equalsIgnoreCase("get-cookie")) {
                    String cookieValue = cookie.returnCookie();
                    System.out.println(cookieValue);
                }
            }
        } catch (EOFException ex) {
            s.close();
            ss.close();
        }

        
        
    }
}
