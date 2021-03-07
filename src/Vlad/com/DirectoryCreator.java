package Vlad.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryCreator extends Creators{
    protected static String newDirPath;
    protected String oldDirPath = "C:\\Users\\vledb\\OneDrive\\Документы\\Директории_блокнота\\";
    protected Path endPath;
    DirectoryCreator(String path) {
        newDirPath = oldDirPath + path;
        endPath = Path.of(newDirPath);
    }
    DirectoryCreator(){}

    @Override
    protected boolean check(){
        var check = false;
        if (Files.exists(endPath) && Files.isDirectory(endPath)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    @Override
    protected void creat(String path) throws IOException{
        newDirPath = oldDirPath + path;
        endPath = Path.of(newDirPath);
        Files.createDirectory(endPath);
    }

    @Override
    protected void goBack() throws IOException{
        ClientWork C = new ClientWork();
        endPath = endPath.getParent();
        C.dirWork();
    }
}

