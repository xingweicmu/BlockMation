/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockmationApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chris
 */
public class BlockmationSwing extends JFrame implements ActionListener{
    private JPanel buttonPanel;         //control panel
    private JButton sizeButton, keepButton, saveButton;
    private JTextField sizeField, outputFileField;
    private BlockmationCanvas canvas;
    
    public BlockmationSwing(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setTitle("Director of Blockmation");
         buttonPanel = new JPanel();
         sizeButton = new JButton("Size");
         keepButton = new JButton("Keep");
         saveButton = new JButton("Save");
         sizeField = new JTextField();
         outputFileField = new JTextField();
         buttonPanel.setLayout(new GridLayout(1,5));
         
         buttonPanel.add(sizeField);
         buttonPanel.add(sizeButton);
         buttonPanel.add(keepButton);
         buttonPanel.add(outputFileField);
         buttonPanel.add(saveButton);
         
         sizeButton.addActionListener(this);
         keepButton.addActionListener(this);
         saveButton.addActionListener(this);
         
         keepButton.setEnabled(false);
         saveButton.setEnabled(false);
         
         setLayout(new BorderLayout());
         add(buttonPanel,BorderLayout.NORTH);
         canvas = new BlockmationCanvas("Left click for black, right click for gray");
         
         add(canvas,BorderLayout.CENTER);
         setBounds(400, 150, 500, 500);
         setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==sizeButton){
            try {
                int initialSize = Integer.parseInt(sizeField.getText());
                if (initialSize>0) {
                    canvas.setModelSize(initialSize);
                    sizeButton.setEnabled(false);
                    keepButton.setEnabled(true);
                    saveButton.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please input a positive integer.", 
                                                "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please input an integer.", 
                                                "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        if(e.getSource()==keepButton){
            canvas.keep();
        }
        if(e.getSource()==saveButton){
            String fileName = canvas.save();
            if (fileName != null) {
                outputFileField.setText(fileName);
                sizeField.setText("");
                sizeButton.setEnabled(true);
                keepButton.setEnabled(false);
                saveButton.setEnabled(false);
                canvas.emptyCanvas();
            }
            else{
                JOptionPane.showMessageDialog(null, "The file is not saved.","Warning",
                                                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    
    
}
