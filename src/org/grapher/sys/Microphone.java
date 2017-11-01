/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grapher.sys;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import static org.grapher.sys.SysInfo.AUDIO_FORMAT;
import static org.grapher.sys.SysInfo.BUFF_SIZE;

/**
 *
 * @author martin
 */
public class Microphone {

    private TargetDataLine.Info microInfo;
    private TargetDataLine micro;
    
    public Microphone() throws LineUnavailableException {
        microInfo = new DataLine.Info(TargetDataLine.class, AUDIO_FORMAT);
        micro = (TargetDataLine) AudioSystem.getLine(microInfo);
    }

    public void open() throws LineUnavailableException {
        micro.open(AUDIO_FORMAT);
    }

    public void start() {
        micro.start();
    }
    
    public byte[] read(){
        byte[] audioBuffer = new byte[BUFF_SIZE];
        micro.read(audioBuffer, 0, audioBuffer.length);
        return audioBuffer;
    }
    
    public byte[] read(int bytesCount){
        byte[] audioBuffer;
        int available = micro.available();
        audioBuffer = new byte[(available < bytesCount) ? available : bytesCount];
        micro.read(audioBuffer, 0, audioBuffer.length);
        return audioBuffer;
    }

}
