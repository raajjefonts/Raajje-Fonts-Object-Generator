import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    private static String output = "";

    public static void main(String[] args) {
        System.out.println("+---------------------------------+");
        System.out.println("| Raajje Fonts - Object Generator |");
        System.out.println("|           by saaiqSAS           |");
        System.out.println("+---------------------------------+");
        start();
    }

    private static void start() {
        Scanner grab = new Scanner(System.in);
        System.out.println("Enter The Path Of Directory Containing Fonts:");
        System.out.print("> ");
        String path = grab.next() + grab.nextLine();

        File directory = new File(path);
        if (!directory.exists()) {
            System.out.println("Error!! Directory Not Found.");
        } else {
            File[] fontFiles = directory.listFiles();

            for (File eFile : fontFiles) {

                output = "{"+"\n";

                String t_name = "";
                t_name = eFile.getName().replace('_',' ');
                boolean extensionReached = false;
                String name = "";
                for (char echar : t_name.toCharArray()) {
                    if (echar != '.' && !extensionReached) {
                        name += echar;
                    } else {
                        extensionReached = true;
                    }
                }

                output += "name: \"" + name+"\",\n";

                String file_name = "";
                file_name = eFile.getName();

                output += "file_name: \"" + file_name+"\",\n";

                String web_file_location = "";
                web_file_location = "fonts/"+file_name;

                output += "web_file_location: \"" + web_file_location+"\",\n";

                String download_file_location = "";
                download_file_location = "fonts/"+file_name;

                output += "download_file_location: \"" + download_file_location+"\",\n";

                try {
                    long download_font_size = 0;
                    download_font_size = Files.size(eFile.toPath())/1000;

                    output += "download_font_size: \"" + download_font_size +" KB"+"\"\n";
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                output += "},";

                System.out.println(output);
            }

        }
    }
}