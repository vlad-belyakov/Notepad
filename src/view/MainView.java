package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class MainView extends JFrame {
    private JPanel Content;
    private JTextArea TextForm;
    private JButton saveButton;
    private JButton openButton;
    private JButton decodeButton;
    private JButton jsonButton;

    private Convertor convert;

    public MainView() {
        convert = new Convertor();

        openButton.addMouseListener(getOpenButtonListener());
        saveButton.addMouseListener(getSaveButtonListener());
        decodeButton.addMouseListener(getDecodeButtonListener());
        jsonButton.addMouseListener(getJsonButtonListener());

        this.addWindowListener(getWindowListener());

        this.setContentPane(Content);
        this.setSize(400,400);
        this.pack();
        this.setVisible(true);
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
                if (file != null && file.isFile() && file.canRead()) {
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
                if (file == null) {
                    return;
                }
                File f = new File(file.getPath());
                try {
                    f.Write(TextForm.getText());
                } catch (IOException e) {
                    TextForm.setText(e.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
    private MouseListener getDecodeButtonListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                var text = TextForm.getText();
                var decText = convert.Decode(text);
                if (decText.equals("")) {
                    return;
                }
                TextForm.setText(decText);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
    private MouseListener getJsonButtonListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Gson json = new GsonBuilder().setPrettyPrinting().create();
                var obj = json.fromJson(TextForm.getText(), Object.class);
                TextForm.setText(json.toJson(obj));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
    }
}
