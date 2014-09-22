/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DisplayApp;

import BlockMationModel.AnimationModel;
import BlockMationModel.BoardModel;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class DisplayCanvas extends JPanel implements Runnable {

    private AnimationModel animationModel;
    private int size;
    private boolean keepGoing;
    private boolean flag;

    public DisplayCanvas() {
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawgrid(g);

        g.setColor(Color.BLUE);
    }

    public void drawgrid(Graphics g) {
        int i, j, starti = 50, startj = 50;
        if (size < 1) {
            return;
        } else {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    g.setColor(animationModel.getValue(i, j));
                    g.fillRect(startj + j * 10, starti + i * 10, 8, 8);
                }
            }
        }
    }

    public int loadModel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                animationModel = new AnimationModel();
                size = animationModel.open(file);
                this.repaint();
                return 1;
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Unable to open.",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (DataFormatException dfe) {
                JOptionPane.showMessageDialog(null, "The file is broken.",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "sth is wrong",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        return 0;
    }

    public void runModel() {
    }

    public void stopit() {
        keepGoing = false;
        //flag=false;

        repaint();
    }

    @Override
    public void run() {
        keepGoing = true;
        try {
            while (keepGoing) {
                for (int i = animationModel.getIndex(); i < animationModel.getNumberOfModel(); i++) {
                    animationModel.showCurrentModel(i);
                    repaint();
                    Thread.sleep(200);
                }
                animationModel.setIndex();
            }
        } catch (InterruptedException ex) {
        }
    }
}
