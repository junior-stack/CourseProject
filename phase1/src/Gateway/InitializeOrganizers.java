package Gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InitializeOrganizers {
    ArrayList<String> Organizers = new ArrayList<>();

    public List initialize() {

        File file = new File("data/InitializeOrganizers.csv");
        try {
            BufferedReader text = new BufferedReader(new FileReader(file));
            String Organizers;
            while ((Organizers = text.readLine()) != null) {
                this.Organizers.add(Organizers);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Organizers;
    }

}
