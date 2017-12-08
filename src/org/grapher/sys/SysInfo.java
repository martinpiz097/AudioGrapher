/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grapher.sys;

import javax.sound.sampled.AudioFormat;
import org.aucommon.sound.AudioQuality;

/**
 *
 * @author martin
 */
public class SysInfo {
//     public static final AudioFormat AUDIO_FORMAT =
//             new AudioFormat(24000.0f, 8, 1, true, true);
//    public static final AudioFormat AUDIO_FORMAT = new AudioFormat(
//            AudioFormat.Encoding.PCM_SIGNED, 16000.0f, 8, 1, 1, 16000.0f, true);
     public static final AudioFormat AUDIO_FORMAT = AudioQuality.LOW;
     public static final short BUFF_SIZE = 64;
     public static final short GRAPH_HEIGHT = 256;
     public static final short GRAPHICS_DIVISOR = (GRAPH_HEIGHT-1)/2; 
}
