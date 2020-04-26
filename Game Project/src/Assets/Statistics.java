package Assets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Panel.Game_Panel;

public class Statistics {
    private String way = "C:\\Users\\senya\\eclipse-workspace\\Game Project Maze";
    private File file = new File(way, "Statistics.txt");

    public String getText() throws IOException {
        try(FileReader reader = new FileReader(file))
        {
            String s = "";
            int c;
            while((c=reader.read())!=-1){
                s += Character.toString((char)c);
            }
            reader.close();
            updateInformation(s);
            return s;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return "asdasdasd";
        }
    }

    public void updateInformation(String s) {
        try(FileWriter writer = new FileWriter(file))
        {
            Integer k = -5;
            try {
                k = Integer.parseInt(s);
                k++;
            }catch(Exception e) {

            }
            String text = k.toString();
            writer.write(text);
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String getCountLevelsInThisSession() throws IOException {
        try(FileReader reader = new FileReader(file))
        {
            String s = "";
            int c;
            while((c=reader.read())!=-1){
                s += Character.toString((char)c);
            }
            reader.close();
            Integer k = -5;

            try {
                k = Integer.parseInt(s);
            }catch(Exception e) {

            }
            k = k - Game_Panel.countLevelsInThisSession - 2;
            s = k.toString();
            return s;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return "asdasdasd";
        }
    }
}
