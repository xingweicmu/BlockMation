/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockMationModel;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.zip.DataFormatException;

/**
 *
 * @author chris
 */
public class AnimationModel {

    private ArrayList<BoardModel> keeper;
    private BoardModel currentModel;
    private int xSize;
    private int ySize;
    private int index=0;
    private boolean isChanged = false;

    public AnimationModel(int xSize, int ySize) {
        this.keeper = new ArrayList<BoardModel>();
        currentModel = new BoardModel(xSize, ySize);
        currentModel.clear();
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public AnimationModel(int size) {
        this(size, size);
    }

    public AnimationModel() {
        this.keeper = new ArrayList<BoardModel>();
    }

    public void setValue(int xPo, int yPo, Color color) {
        currentModel.setValue(xPo, yPo, color);
        this.isChanged = true;
    }
    
    public int getIndex(){
        return index;
    }

    public Color getValue(int xPo, int yPo) {
        return currentModel.getValue(xPo, yPo);
    }
    
    public int getNumberOfModel(){
        return keeper.size();
    }
    
    public void setIndex(){
        index=0;
    }

    public void keep() {
        keeper.add(currentModel.copy());
        currentModel.clear();
        this.isChanged = false;
    }

    public void save(File file) throws IOException {
        if (isChanged) {
            keep();
        }
        PrintWriter fileOut = new PrintWriter(new FileWriter(file));
        fileOut.println(keeper.size() + "");
        fileOut.println(xSize + " " + ySize);
        for (BoardModel model : keeper) {
            fileOut.print(model.toString());
        }
        fileOut.close();

    }

    public int open(File file) throws IOException, DataFormatException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = Integer.parseInt(line);

            line = reader.readLine();
            StringTokenizer tkn = new StringTokenizer(line, " ");

            if (tkn.countTokens() == 2) {
                xSize = Integer.parseInt(tkn.nextToken());
                ySize = Integer.parseInt(tkn.nextToken());
            } else {
                throw new DataFormatException(line);
            }

            for (int k = 0; k < counter; k++) {
                line = "";
                for (int i = 0; i < xSize; i++) {
                    line = line + reader.readLine();
                }
                keeper.add(convertToModel(line));
            }
           currentModel = new BoardModel(xSize, ySize);
           currentModel = keeper.get(0).copy();
            return xSize;

        } catch (NumberFormatException nfe) {
            throw new DataFormatException();
        } catch (StringIndexOutOfBoundsException sioobe) {
            throw new DataFormatException();
        }

    }

    private BoardModel convertToModel(String line) throws StringIndexOutOfBoundsException {
        BoardModel newModel = new BoardModel(xSize, ySize);
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                char[] colorChar = {line.charAt((i * ySize) + j)};
                String colorCode = new String(colorChar);       //get a single letter from line
                Color color = newModel.stringToColor(colorCode);
                newModel.setValue(i, j, color);
            }
        }
        return newModel;
    }
    
    public void showCurrentModel(int i){
         currentModel = new BoardModel(xSize, ySize);
         currentModel = keeper.get(i).copy();
         index++;
             
        
    }
        
    
}
