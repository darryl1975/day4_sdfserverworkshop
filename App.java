package sg.edu.nus.iss;

import java.io.File;

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
     */
    public static void main(String[] args) {

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
    }
}
