package Notepad.Vlad.com;

import java.util.ArrayList;

public class CompareWork {
    String[] exitComands = new String[]{
            "выход",
            "exit"
    };
    String[] goBackComands = new String[]{
            "выход",
            "exit"
    };
    protected boolean include(String b, String[] list){
        boolean check = false;
        for (String s : list) {
            if (b.equalsIgnoreCase(s)) {
                check = true;
                break;
            }
        }
        return check;
    }
    protected boolean include(String b, String[] list, String[] list2){
        boolean check = false;
        boolean check2 = false;
        for (String s : list) {
            if (b.equalsIgnoreCase(s)) {
                check = true;
                break;
            }
        }
        for (String s : list2) {
            if (b.equalsIgnoreCase(s)) {
                check2 = true;
                break;
            }
        }
        return check && check2;
    }
}
