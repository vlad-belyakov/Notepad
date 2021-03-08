package view;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainView extends JFrame {
    private JPanel Content;
    private JTextArea TextForm;
    private JButton SaveButton;
    private JButton OpenButton;

    public MainView() {
        this.getContentPane().add(Content);
        this.pack();
        this.setSize(400,400);
        this.setVisible(true);

        OpenButton.addMouseListener(getOpenButtonListener());
        SaveButton.addMouseListener(getSaveButtonListener());

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
    private MouseListener getOpenButtonListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JFileChooser dialog = new JFileChooser();
                dialog.showOpenDialog(Content);
                var file = dialog.getSelectedFile();
                if (file.isFile() && file.canRead()) {
                    File f = new File(file.getPath());
                    try {
                        TextForm.setText(f.Read());
                    } catch (IOException e) {
                        TextForm.setText(e.getMessage());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
    private MouseListener getSaveButtonListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JFileChooser dialog = new JFileChooser();
                dialog.showSaveDialog(Content);
                var file = dialog.getSelectedFile();
                File f = new File(file.getPath());
                try {
                    f.Write(TextForm.getText());
                } catch (IOException e) {
                    TextForm.setText(e.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
}
