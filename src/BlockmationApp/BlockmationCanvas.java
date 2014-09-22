/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockmationApp;

import BlockMationModel.AnimationModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class BlockmationCanvas extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private String status;
    private AnimationModel animationModel;
    private int size = 0;
    private int pressedButton = 0;

    public BlockmationCanvas(String status) {
        this.status = status;
        setBackground(Color.WHITE);
    }

    public void setModelSize(int size) {
        this.size = size;
        animationModel = new AnimationModel(size);
        this.repaint();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawgrid(g);

        g.setColor(Color.BLACK);
        g.drawString(status, 70, 20);
    }

    public void drawgrid(Graphics g) {
        int i, j, starti = 50, startj = 50;
        if (size < 1) {
            return;
        } else {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    g.setColor(animationModel.getValue(i, j));
                    g.fillRect(startj + j * 15, starti + i * 15, 13, 13);
                }
            }
        }
    }

    public void keep() {
        animationModel.keep();
        this.repaint();
    }

    public String save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                animationModel.save(file);
                return file.getName();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Unable to save the file",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        return null;
    }

    public void emptyCanvas() {
        animationModel = null;
        this.size = 0;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int key = e.getButton();
        Color color = Color.BLUE;
        if (key == MouseEvent.BUTTON1) {
            color = Color.BLACK;
        } else if (key == MouseEvent.BUTTON3) {
            color = Color.GRAY;
        }
        int i, j, starti = 50, startj = 50;
        int x = e.getX(), y = e.getY();
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (x > starti + i * 15 && x < starti + i * 15 + 13
                        && y > startj + j * 15 && y < startj + j * 15 + 13) {
                    animationModel.setValue(j, i, color);
                }
            }
        }
        this.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedButton = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Color color = Color.BLUE;
        if (pressedButton == MouseEvent.BUTTON1) {
            color = Color.BLACK;
        } else if (pressedButton == MouseEvent.BUTTON3) {
            color = Color.GRAY;
        }
        int i, j, starti = 50, startj = 50;
        int x = e.getX(), y = e.getY();
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (x > starti + i * 15 && x < starti + i * 15 + 13
                        && y > startj + j * 15 && y < startj + j * 15 + 13) {
                    animationModel.setValue(j, i, color);
                }
            }
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
