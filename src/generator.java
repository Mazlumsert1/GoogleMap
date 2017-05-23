import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mazlumsert on 22/05/2017.
 */
public class generator {

    private String folder;

    private String header;
    private String footer;

    public generator() {
        this.folder = new File("src/").toURI().getPath();
        this.header = reader("header.html");
        this.footer = reader("footer.html");
    }

    public void generateFile(String filename, String content) {
        writer(filename,content);
    }

    private void writer(String filename, String content) {
        File file = new File(folder + generateFileName(filename));

        FileWriter fileWriter;
        try {
         fileWriter = new FileWriter(file);

        fileWriter.write(header);
        fileWriter.write(content);
        fileWriter.write(footer);

        fileWriter.flush();
        fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String reader(String filename) {

        File index = new File(folder  + filename);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(index));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        String line;
        try {
                while ((line = reader.readLine()) != null)
                {
                    //line = reader.readLine();
                    builder.append(line);

                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private String generateFileName(String prefix)
    {
        return prefix + "_" + new Date().getTime() + ".html";
    }


    public static void main(String[] args) throws IOException {

        generator g = new generator();


        List<Double[]> locations = new ArrayList<>();

        locations.add(new Double[]{41.015137, 28.979530});
        locations.add(new Double[]{37.87135, 32.48464});


        StringBuilder builder = new StringBuilder();


        for (Double[] location : locations) {
               builder.append("[").append(location[0]).append(",").append(location[1]).append("], ");
        }


        g.generateFile("mazhha", builder.toString());


    }
}

