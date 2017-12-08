/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grapher.sys;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;
import org.aucommon.sound.Microphone;
import org.aucommon.sound.Speaker;
import static org.grapher.sys.SysInfo.BUFF_SIZE;

/**
 *
 * @author martin
 */
public class AudioAnalizer extends Thread{
    private Microphone micro;
    private Speaker speaker;
    
    private Grapher grapher;

    private LinkedList<byte[]> listBytes;
    
    public AudioAnalizer(JPanel audioPanel) throws LineUnavailableException {
        micro = new Microphone();
        speaker = new Speaker();
        grapher = new Grapher(audioPanel);
        setPriority(MIN_PRIORITY);
        listBytes = new LinkedList<>();
    }
    
    private byte[] getConvertedBytes(byte[] originalBytes){
        byte b;
        for (int i = 0; i < originalBytes.length; i++) {
            b = originalBytes[i];
            if (b != 0)
                if (b > 0)
                    originalBytes[i] = (byte) (b-1);
                else
                    originalBytes[i] = (byte) (b+1);
        }
        return originalBytes;
    }
    
    private byte[] getCompressedAudio(byte[] audioBytes){
        LinkedList<Byte> listSelectedBytes = new LinkedList<>();
        byte b;
        for (int i = 0; i < audioBytes.length; i++) {
            b = audioBytes[i];
            if (b <=20)
                listSelectedBytes.add(b);
        }
        byte[] selectedBytes = new byte[listSelectedBytes.size()];
        int listCounter = 0;
        for (byte selectedByte : listSelectedBytes) {
            selectedBytes[listCounter] = selectedByte;
            listCounter++;
        }
        System.out.println("Original: "+audioBytes.length+"\nCompresion: "+selectedBytes.length);
        listSelectedBytes = null;
        return selectedBytes;
    }
    
    
    @Override
    public void run(){
        try {
            micro.open();
            speaker.open();
            grapher.start();
            byte[] audioBytes;
            while (true) {
                audioBytes = micro.readAudio(BUFF_SIZE);
                listBytes.add(audioBytes);
                speaker.playAudio(audioBytes);
                grapher.addBytes(audioBytes);
                /*if (listBytes.size() >= 5000) {
                break;
                }*/
            }
            /*System.out.println("A escuchar");
            for (byte[] bytes : listBytes) {
            speaker.write(bytes);
            }
            System.exit(0);*/
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioAnalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
