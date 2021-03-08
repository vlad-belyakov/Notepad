package Vlad.com;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClientWork {
    public ClientWork(){}
    boolean shutDown = true;
    protected void clientWorking() throws IOException{
        while (shutDown)
            dirWork();
    }

    protected void dirWork() throws IOException {
        System.out.println("Введите название директория, который вы хотите использовать");
        BufferedReader init = new BufferedReader(new InputStreamReader(System.in));
        String path = init.readLine();

        DirectoryCreator directory = new DirectoryCreator(path);
        if (directory.check()) {
            fileWork();
        } else {
            System.out.print("Такой директорий не был найден, для создания нового напишите \"Создать директорий\", ");
            System.out.println("или выберите другой директорий");
            String newDirName = init.readLine();
            toDirLoop:
            if (newDirName.equalsIgnoreCase("создать директорий")) {
                System.out.println("Введите название директория, который вы хотите создать");
                String newPath = init.readLine();
                directory.creat(newPath);
                System.out.println("Директорий создан");
                break toDirLoop;
            } else {
                System.out.println("Введите название директория, который вы хотите использовать");
                break toDirLoop;
            }
        }
    }

    protected void fileWork() throws IOException {
        System.out.println("Введите название файла, который вы хотите использовать для записи");
        BufferedReader initil = new BufferedReader(new InputStreamReader(System.in));
        CompareWork compare = new CompareWork();
        String fileName = initil.readLine();
        if (!compare.include(fileName, compare.exitComands, compare.goBackComands)) {
            String filePath = DirectoryCreator.newDirPath + "\\" + fileName;
            FileCreator file = new FileCreator(filePath);
            toFileMark1:
            if (file.check()) {
                System.out.println("Блокнот открыт, можно записывать");
                String pen = initil.readLine();
                if (!compare.include(pen, compare.exitComands, compare.goBackComands)) {
                    String fileWriter = "";
                    while (!compare.include(fileWriter, compare.exitComands, compare.goBackComands)) {
                        fileWriter = initil.readLine() + "\n";
                        byte[] bytes = fileWriter.getBytes();
                        //Files.writeString(Path.of(FileCreator.newFileDir), fileWriter);
                        //pen = fileWriter;
                        FileOutputStream fos = new FileOutputStream(FileCreator.newFileDir, true);
                        fos.write(bytes);
                    }
                }
                else if (compare.include(pen, compare.exitComands)) {
                    shutDown = false;
                } else if ((compare.include(pen, compare.goBackComands))) {
                    file.goBack();
                }
            } else {
                System.out.print("Такой файл не был найден, для создания нового напишите \"Создать файл\", ");
                System.out.println("или выберите другой файл");
                String nameFile = initil.readLine();
                if (nameFile.equalsIgnoreCase("создать файл")) {
                    System.out.println("Введите название файла, который хотите создать");
                    String newFileName = initil.readLine();
                    file.creat(newFileName);
                    System.out.println("Файл был успешно создан");
                    break toFileMark1;
                } else {
                    fileWork();
                }
            }
        } else if (compare.include(fileName, compare.exitComands)) {
            shutDown = false;
        } else if (compare.include(fileName, compare.goBackComands)) {
            DirectoryCreator dirCr = new DirectoryCreator();
        }
    }

    protected void commandsNotice(){
        CompareWork compare = new CompareWork();
        System.out.print("Команды для шага назад по файловой ветке: ");
        for (int i = 0; i < compare.goBackComands.length; i++) {
            if (i < compare.goBackComands.length - 1) {
                System.out.print(compare.goBackComands[i] + "  |  ");
            } else {
                System.out.println(compare.goBackComands[i] + "  |  \n");
            }
        }
        System.out.print("Команы для выхода из программы: ");
        for (int i = 0; i < compare.exitComands.length; i++) {
            if (i < compare.exitComands.length - 1) {
                System.out.print(compare.exitComands[i] + "  |  ");
            } else {
                System.out.println(compare.exitComands[i] + "  |  \n");
            }
        }
    }
}