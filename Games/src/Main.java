package Games.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(15000, 2400, 50, 110.4);
        GameProgress game2 = new GameProgress(15000, 2400, 50, 110.4);
        GameProgress game3 = new GameProgress(15000, 2400, 50, 110.4);
        saveGame("D:\\Games\\Games\\savegames", game1);
        saveGame("D:\\Games\\Games\\savegames", game2);
        saveGame("D:\\Games\\Games\\savegames", game3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("D:\\Games\\Games\\savegames\\game1.dat");
        arrayList.add("D:\\Games\\Games\\savegames\\game2.dat");
        arrayList.add("D:\\Games\\Games\\savegames\\game3.dat");
        zipFiles("D:\\Games\\Games\\savegames\\zip.zip", arrayList);

        deleteFiles(arrayList);


    }

    private static void deleteFiles(ArrayList<String> arrayList) {

        for (String filePath : arrayList) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("File \"" + file.getName() + "\" был удален");
            } else {
                System.out.println("File \"" + file.getName() + "\" не найден или удален");
            }
        }
    }

    private static void saveGame(String path, GameProgress game) {
        File savegamesDir = new File(path);
        if (!savegamesDir.exists()) {
            savegamesDir.mkdirs();
        }

        String[] saveFiles = {"game1.dat", "game2.dat", "game3.dat"};
        for (String saveFile : saveFiles) {
            try (FileOutputStream fos = new FileOutputStream(path + "\\" + saveFile);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(game);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    private static void zipFiles(String path, List<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String filePath : files) {
                File file = new File(filePath);
                String fileName = file.getName();
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(fileName);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                } catch (IOException ex) {
                    System.out.println("Ошибка при добавлении файла в архив: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Ошибка при создании архива: " + ex.getMessage());
        }
    }
}