/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockMationModel;

import java.awt.Color;


/**
 *
 * @author chris
 */
public interface BlockMationModel {
    abstract public void setValue(int xPo,int yPo,Color color);
    abstract public Color getValue(int xPo,int yPo);
    
}
