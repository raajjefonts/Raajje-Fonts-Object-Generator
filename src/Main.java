import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String output = "";

    public static void main(String[] args) {
        System.out.println("+---------------------------------+");
        System.out.println("| Raajje Fonts - Object Generator |");
        System.out.println("|           by saaiqSAS           |");
        System.out.println("|             v2.0.1              |");
        System.out.println("+---------------------------------+");

        //objGen();
        fileLinesInAlphabetic();
    }

    private static void objGen() {
        try {
            Scanner grab = new Scanner(System.in);
            System.out.println("Enter The Path Of Directory Containing Fonts:");
            System.out.print("> ");
            String path = grab.next() + grab.nextLine();

            System.out.println("Enter The Source Of Fonts in Directory:");
            System.out.print("> ");
            String src = grab.next() + grab.nextLine();


            File directory = new File(path);
            if (!directory.exists()) {
                System.out.println("Error!! Directory Not Found.");
            } else {
                FileWriter write = new FileWriter(path+"/raajjefonts_obj_gen_output.txt");
                File[] fontFiles = directory.listFiles();

                for (File eFile : fontFiles) {

                    output = "{";

                    String t_name = "";
                    t_name = eFile.getName().replace('_', ' ').replace('-', ' ').replace("mv", "MV");
                    boolean extensionReached = false;
                    String name = "";
                    for (char echar : t_name.toCharArray()) {
                        if (echar != '.' && !extensionReached) {
                            name += echar;
                        } else {
                            extensionReached = true;
                        }
                    }

                    output += "name: \"" + name + "\",";

                    String file_name = "";
                    file_name = eFile.getName();

                    String web_file_location = "";
                    web_file_location = "fonts/" + file_name;

                    output += "web_file_location: \"" + web_file_location + "\",";

                    String download_file_location = "";
                    download_file_location = "fonts/" + file_name;

                    output += "Source: \"" +  src + "\",";

                    output += "download_link: \"" + " " + "\"";

                    output += "},";

                    System.out.println(output);
                    write.write(output);
                    write.write("\n");
                }
                write.close();
                System.out.println("Saved output to file");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    private static void fileLinesInAlphabetic() {
        try {
            System.out.println("File Lines in Alphabetic Order Tool");
            Scanner grab = new Scanner(System.in);
            System.out.println("Enter The Path Of The File Containing Lines:");
            System.out.print("> ");
            String path = grab.next() + grab.nextLine();

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Error!! File Not Found.");
            } else {

                // Read all lines from the input file
                List<String> lines = Files.readAllLines(Paths.get(path));

                // Sort the lines alphabetically
                Collections.sort(lines);

                // Specify the directory and output file path
                Path outputFilePath = Paths.get(path).getParent().resolve("sorted_output.txt");

                // Create necessary directories if they do not exist
                Path outputDir = outputFilePath.getParent();
                if (!Files.exists(outputDir)) {
                    Files.createDirectories(outputDir); // Create directories
                }

                // Create the output file if it doesn't exist
                if (!Files.exists(outputFilePath)) {
                    Files.createFile(outputFilePath); // Create the file
                }

                // Write the sorted lines to the output file
                Files.write(outputFilePath, lines);

                System.out.println("File sorted and saved as " + outputFilePath.toString());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}