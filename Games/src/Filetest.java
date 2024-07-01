package Games.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Filetest {
    private static StringBuilder log = new StringBuilder();

    public static void main(String[] args) throws IOException {

        String[] directories = {"Games", "Games/src", "Games/res", "Games/savegames"};
        for (String directory : directories) {
            createDirectory(directory);
        }

        String[] files = {"Games/src/Main.java", "Games/src/Utils.java", "Games/temp.txt"};
        for (String filePath : files) {
            createFile(filePath, log);
        }

        System.out.println(log);

        try (FileWriter writer = new FileWriter("Games/temp.txt")) {
            writer.write(log.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDirectory(String path) {
        File directory = new File(path);
        if (directory.mkdir()) {
            log.append("Каталог " + path + " создан\n");
        } else {
            log.append("Каталог " + path + " не создан\n");
        }

    }

    private static void createFile(String path, StringBuilder log) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            Filetest.log.append("Файл ").append(path).append(" создан.\n");
        } else {
            Filetest.log.append("Файл ").append(path).append(" не создан.\n");
        }
    }
}



