package view;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class NewTabb extends JFrame {
    public JPanel tab;
    protected JTextArea inputTextArea;
    private JPanel panel;
    private JButton openButton;
    private JButton saveButton;
    private String nameOfTab;


    public NewTabb(JTabbedPane tabz) throws IOException {


        this.add(tab);
        this.setSize(300, 300);
        this.pack();
        this.setVisible(false);

        saveButton.addActionListener(click -> {
            if (inputTextArea != null) {
                JFileChooser dialog = new JFileChooser();
                dialog.showSaveDialog(panel);
                var file = dialog.getSelectedFile();
                if((file != null)) {
                    File f = new File(file.getPath());
                    try {
                        f.Write(inputTextArea.getText());
                        int select = tabz.getSelectedIndex();
                        nameOfTab = file.getName();
                        tabz.setTitleAt(select, nameOfTab);
                    } catch (IOException e) {
                        inputTextArea.setText(e.getMessage());
                    }
                }else{
                    return;
                }
            }
        });
        openButton.addActionListener(click -> {
            JFileChooser dialog = new JFileChooser();
            dialog.showOpenDialog(panel);
            var file = dialog.getSelectedFile();
            if (!(file == null)) {
                if (file.isFile() && file.canRead()) {
                    File f = new File(file.getPath());
                    nameOfTab = file.getName();
                    try {
                        inputTextArea.setText(f.Read());
                        int select = tabz.getSelectedIndex();
                        tabz.setTitleAt(select, nameOfTab);
                    } catch (IOException e) {
                        inputTextArea.setText(e.getMessage());
                    }
                }
            } else {
                return;
            }
        });


        this.addWindowListener(getWindowListener());
    }

    private WindowListener getWindowListener() {
        return new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
            }
        };
    }


}
