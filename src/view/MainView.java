package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView extends JFrame {
    private JPanel panel;
    private JTextArea inputTextArea;
    private JButton saveButton;
    private JButton openButton;
    private JButton decodeB64;
    private JButton decodeJson;
    private JButton newTab;
    private JButton closeTab;
    protected JTabbedPane tabs;
    private JPanel tab;
    public String nameOfTab;
    protected JFrame window;

    private Convertor convert;

    public MainView() {
        window = new JFrame("Расшифровщик");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(panel);
        window.setSize(1550,800);
        //window.pack();
        // делает окно по размерам GUI
        window.setVisible(true);


        saveButton.addActionListener(click -> {
            if (inputTextArea != null) {
                int select = tabs.getSelectedIndex();
                JFileChooser dialog = new JFileChooser();
                dialog.showSaveDialog(panel);
                var file = dialog.getSelectedFile();
                if((file != null)) {
                    File f = new File(file.getPath());
                    try {
                        f.Write(inputTextArea.getText());
                        nameOfTab = file.getName();
                        tabs.setTitleAt(select, nameOfTab);
                    } catch (IOException e) {
                        inputTextArea.setText(e.getMessage());
                    }
                }else{
                    return;
                }
            }
        });
        openButton.addActionListener(click -> {
            int select = tabs.getSelectedIndex();
            JFileChooser dialog = new JFileChooser();
            dialog.showOpenDialog(panel);
            var file = dialog.getSelectedFile();
            if (!(file == null)) {
                if (file.isFile() && file.canRead()) {
                    File f = new File(file.getPath());
                    nameOfTab = file.getName();
                    try {
                        inputTextArea.setText(f.Read());
                        tabs.setTitleAt(select, nameOfTab);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } else {
                return;
            }
        });
        decodeB64.addActionListener(click -> {
            if (inputTextArea != null){
                BufferedReader i = new BufferedReader(new InputStreamReader(System.in));
                String c = inputTextArea.getText();
                inputTextArea.setText(null);
                Base_64 b = new Base_64();
                inputTextArea.append(b.codeToBase64(c));
            }
        });

        newTab.addActionListener(click -> {
            NewTabb ta = null;
            try {
                ta = new NewTabb(tabs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tabs.addTab("tab", ta.tab);
        });
        closeTab.addActionListener(click -> {
            int select = tabs.getSelectedIndex();
            if (select >= 0) {
                tabs.removeTabAt(select);
            }
        });
        decodeJson.addActionListener(click -> {
            if (inputTextArea != null){
                String c = inputTextArea.getText();
                inputTextArea.setText(null);
                Base_64 b = new Base_64();
                JSon_ j = new JSon_();
                inputTextArea.append(j.prettyView(b.encodeFromBase64(c)));
            }
        });

        this.addWindowListener(getWindowListener());





    }
    private WindowListener getWindowListener() {
        return new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {}

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {}

            @Override
            public void windowIconified(WindowEvent windowEvent) {}

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {}

            @Override
            public void windowActivated(WindowEvent windowEvent) {}

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {}
        };
    }

    /*themeColor = Launcher.themeColor;
        if(themeColor){
            //60,63,65
            panel.setBackground(new Color(60,63,65));
            panel.setForeground(Color.WHITE);
            tabs.setBackground(new Color(60,63,65));
            tabs.setForeground(Color.WHITE);
            window.setBackground(new Color(60,63,65));
            window.setForeground(Color.WHITE);
            inputTextArea.setBackground(new Color(60,63,65));
            inputTextArea.setForeground(Color.WHITE);
            decodeJson.setBackground(new Color(60,63,65));
            decodeJson.setForeground(Color.WHITE);
            decodeB64.setBackground(new Color(60,63,65));
            decodeB64.setForeground(Color.WHITE);
            save.setBackground(new Color(60,63,65));
            save.setForeground(Color.WHITE);
            open.setBackground(new Color(60,63,65));
            open.setForeground(Color.WHITE);
            copy.setBackground(new Color(60,63,65));
            copy.setForeground(Color.WHITE);
            newTab.setBackground(new Color(60,63,65));
            newTab.setForeground(Color.WHITE);
            closeTab.setBackground(new Color(60,63,65));
            closeTab.setForeground(Color.WHITE);
            settings.setBackground(new Color(60,63,65));
            settings.setForeground(Color.WHITE);
        } else {
            panel.setBackground(Color.WHITE);
            panel.setForeground(Color.BLACK);
            tabs.setBackground(Color.WHITE);
            tabs.setForeground(Color.BLACK);
            window.setBackground(Color.WHITE);
            window.setForeground(Color.BLACK);
            inputTextArea.setBackground(Color.WHITE);
            inputTextArea.setForeground(Color.BLACK);
            decodeJson.setBackground(Color.WHITE);
            decodeJson.setForeground(Color.BLACK);
            decodeB64.setBackground(Color.WHITE);
            decodeB64.setForeground(Color.BLACK);
            save.setBackground(Color.WHITE);
            save.setForeground(Color.BLACK);
            open.setBackground(Color.WHITE);
            open.setForeground(Color.BLACK);
            copy.setBackground(Color.WHITE);
            copy.setForeground(Color.BLACK);
            newTab.setBackground(Color.WHITE);
            newTab.setForeground(Color.BLACK);
            closeTab.setBackground(Color.WHITE);
            closeTab.setForeground(Color.BLACK);
            settings.setBackground(Color.WHITE);
            settings.setForeground(Color.BLACK);
        }*/
}
