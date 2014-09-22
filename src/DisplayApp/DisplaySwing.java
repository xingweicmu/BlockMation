/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DisplayApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chris
 */
public class DisplaySwing extends JFrame implements ActionListener{
    
    private JPanel buttonPanel;
    private DisplayCanvas canvas;
    private JTextField textField;
    private JButton loadButton, runButton, stopButton;
    Thread runningThread;                 //handles animation of run   
    
    public DisplaySwing(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textField = new JTextField();
        
        loadButton = new JButton("Load");
        runButton = new JButton("Run");
        stopButton = new JButton("Stop");
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        
        buttonPanel.add(textField);
        buttonPanel.add(loadButton);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        
        setLayout(new BorderLayout());
        
        add(buttonPanel,BorderLayout.NORTH);
        
        canvas = new DisplayCanvas();
        
        add(canvas,BorderLayout.CENTER);
        
        loadButton.addActionListener(this);
        runButton.addActionListener(this);
        stopButton.addActionListener(this);
        
        runButton.setEnabled(false);
        stopButton.setEnabled(false);
        
        setBounds(400, 150, 500, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loadButton){
            
            if(canvas.loadModel()==1){
            
            loadButton.setEnabled(false);
            runButton.setEnabled(true);
            }
        }
        if(e.getSource()==runButton){
            //canvas.runModel();
             runningThread=new Thread(canvas);
              runningThread.start();         
            runButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
        if(e.getSource()==stopButton){
            //canvas.stopit(); 
            runningThread.interrupt();  
            runButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }
    
    
}
