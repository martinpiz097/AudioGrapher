///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.grapher.sys;
//
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//import static org.grapher.sys.SysInfo.AUDIO_FORMAT;
//
///**
// *
// * @author martin
// */
//public class Speaker {
//    private SourceDataLine.Info speakerInfo;
//    private SourceDataLine speaker;
//    
//    public Speaker() throws LineUnavailableException {
//        speakerInfo = new DataLine.Info(SourceDataLine.class, AUDIO_FORMAT);
//        speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
//    }
//
//    public void open() throws LineUnavailableException {
//        speaker.open(AUDIO_FORMAT);
//    }
//
//    public void start() {
//        speaker.start();
//    }
//    
//    public int write(byte[] bytes){
//        speaker.write(bytes, 0, bytes.length);
//        return bytes.length;
//    }
//    
//    public int write(byte[] bytes, int bytesCount){
//        int buffLen = bytes.length;
//        return speaker.write(bytes, 0, buffLen < bytesCount ? buffLen : bytesCount);
//    }
//    
//}
