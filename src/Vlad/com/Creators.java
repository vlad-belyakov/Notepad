package Vlad.com;

import java.io.IOException;

public abstract class Creators {

    protected abstract boolean check();

    protected abstract void creat(String path) throws IOException;

    protected abstract void goBack() throws IOException;
}

