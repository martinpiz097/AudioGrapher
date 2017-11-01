/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grapher.sys;

import javax.sound.sampled.AudioFormat;

/**
 *
 * @author martin
 */
public class SysInfo {
     public static final AudioFormat AUDIO_FORMAT =
             new AudioFormat(32000.0f, 8, 1, true, true);
     public static final short BUFF_SIZE = 512;
     public static final short GRAPH_HEIGHT = 131;
     public static final short GRAPHICS_DIVISOR = (GRAPH_HEIGHT-1)/2; 
}
