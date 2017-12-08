/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grapher.sys;

import java.awt.Color;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import static org.grapher.sys.SysInfo.BUFF_SIZE;
import static org.grapher.sys.SysInfo.GRAPHICS_DIVISOR;
import static org.grapher.sys.SysInfo.GRAPH_HEIGHT;

/**
 *
 * @author martin
 */
public class Grapher extends Thread{
    private JPanel audioPanel;
    private JButton[][] audioButtons;
    
    private Deque<byte[]> queueBytes;
    
    public Grapher(JPanel audioPanel) {
        this.audioPanel = audioPanel;
        this.audioButtons = new JButton[GRAPH_HEIGHT][BUFF_SIZE];
        queueBytes = new LinkedList<>();
        addButtons();
        setPriority(MAX_PRIORITY);
        audioPanel.setBackground(Color.BLACK);
    }
    
    private void addButtons(){
        JButton btn;
        for (int i = 0; i < audioButtons.length; i++) {
            for (int j = 0; j < audioButtons[i].length; j++) {
                btn = new JButton();
                btn.setVisible(true);
                btn.setEnabled(false);
                btn.setText(null);
                btn.setBackground(Color.BLACK);
                btn.setOpaque(true);
                btn.setBorder(null);
                audioButtons[i][j] = btn;
                audioPanel.add(btn);
            }
        }
        audioPanel.update(audioPanel.getGraphics());
    }
    
    private void restartPanel(){
        for (int i = 0; i < audioButtons.length; i++)
            for (int j = 0; j < audioButtons[0].length; j++)
                audioButtons[i][j].setBackground(Color.BLACK);
        audioPanel.update(audioPanel.getGraphics());
    }
    
    public void addBytes(byte[] bytes){
        queueBytes.add(bytes);
    }

    public void addBytes(byte[] audioBytes, short BUFF_SIZE) {
        int buffLen = audioBytes.length;
        if (BUFF_SIZE > buffLen)
            return;
        else if (BUFF_SIZE == buffLen)
            queueBytes.add(audioBytes);
        else{
            byte[] newBuff = new byte[BUFF_SIZE];
            System.arraycopy(audioBytes, 0, newBuff, 0, BUFF_SIZE);
            queueBytes.add(newBuff);
        }
    }
    
    public void drawSound(){
        while (!queueBytes.isEmpty())
            drawSound(queueBytes.pollLast());
    }
    
    public void drawSound(byte[] audioBytes){
        restartPanel();
        int value;
        for (int j = 0; j < audioBytes.length; j++) {
            value = audioBytes[j];
            if (value > GRAPHICS_DIVISOR || value < -GRAPHICS_DIVISOR)
                if (value > GRAPHICS_DIVISOR)
                    audioButtons[0][j].setBackground(Color.GREEN);
                else
                    audioButtons[GRAPHICS_DIVISOR*2][j].setBackground(Color.GREEN);
            else{
                //System.out.println(value+"-"+(GRAPHICS_DIVISOR-value));
                audioButtons[GRAPHICS_DIVISOR-value][j].setBackground(Color.GREEN);
            }
        }
        audioPanel.update(audioPanel.getGraphics());
    }

    @Override
    public void run() {
        while (true)
            drawSound();
    }

}
