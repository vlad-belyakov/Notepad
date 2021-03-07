package Vlad.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreator extends Creators{
    String oldFileDir;
    static String newFileDir;
    Path endPath;
    public FileCreator(String path){
        oldFileDir = DirectoryCreator.newDirPath;
        newFileDir = path + ".txt";
        endPath = Path.of(newFileDir);
    }

    @Override
    protected boolean check(){
        var check = false;
        if(Files.exists(endPath) && Files.isRegularFile(endPath)){
            check = true;
        }else {
            check = false;
        }
        return check;
    }

    @Override
    protected void creat(String path) throws IOException {
        newFileDir = oldFileDir + "\\" + path + ".txt";
        endPath = Path.of(newFileDir);
        Files.createFile(endPath);
    }

    @Override
    protected void goBack() throws IOException{
        ClientWork C = new ClientWork();
        newFileDir = oldFileDir;
        C.fileWork();
    }
}
