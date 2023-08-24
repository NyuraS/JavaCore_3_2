import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(25, 30, 2, 25.5);
        GameProgress game2 = new GameProgress(50, 63, 5, 48.8);
        GameProgress game3 = new GameProgress(86, 125, 8, 97.23);
        saveGame("E:/Games/savegames/game1.dat", game1);
        saveGame("E:/Games/savegames/game2.dat", game2);
        saveGame("E:/Games/savegames/game3.dat", game3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("E:/Games/savegames/game1.dat");
        arrayList.add("E:/Games/savegames/game2.dat");
        arrayList.add("E:/Games/savegames/game3.dat");
        zipFiles("E:/Games/savegames/zip.zip", arrayList);

        File game1Dat = new File("E:/Games/savegames/game1.dat");
        File game2Dat = new File("E:/Games/savegames/game2.dat");
        File game3Dat = new File("E:/Games/savegames/game3.dat");

        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zos.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zos.write(fis.read());
                    }
                    zos.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}