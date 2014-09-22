/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockMationModel;

import java.awt.Color;

/**
 *
 * @author Peter 
 */
public class BoardModel implements BlockMationModel{
    
    /*model of the grid*/
    private Color grid[][];
    /*row of the BoardModel*/
    private int row;
    /*column of the BoardModel*/
    private int col;
    
    /**
     * Creates a BoardModel with specific rows and columns 
     * @param row the specific row 
     * @param col the specific columns 
     */
    public BoardModel(int row,int col){
        if(row<1 ||col<1){
            throw new IllegalArgumentException("Rows & columns must be +ve");
        }
        grid = new Color[row][col];
        this.row = row;
        this.col = col;
    }
    
    /**
     * Creates a BoardModel with the specific size 
     * @param size the size for rows and columns 
     */
    public BoardModel(int size){
        this(size,size);
    }
    
    /**
     * Creates a default BoardModel with 20 rows and 20 columns. 
     */
    public BoardModel(){
        this(20,20);
    }
    
       
    /**
     * This method sets the value of the specific position with the specific color 
     * @param xPo the x position of the position 
     * @param yPo the y position of the position 
     * @param color the color to be changed to 
     */
    @Override
    public void setValue(int xPo,int yPo,Color color){
        grid[xPo][yPo] = color;
    }
    
    /**
     * This method returns the color of a specific position (x,y) 
     * @param xPo the x position for the color 
     * @param yPo the y position for the color 
     * @return the color of a specific position (x,y) 
     */
    @Override
    public Color getValue(int xPo,int yPo){
        return grid[xPo][yPo];
    }
    
    /**
     * This method clears the grid and set to the default color blue. 
     */
    public void clear(){
        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
                grid[i][j] = Color.BLUE;
            }
        }
    }
    
    public BoardModel copy(){
        BoardModel model = new BoardModel(this.row,this.col);
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                model.setValue(i, j, this.getValue(i, j));
            }
        }
        return model;
    }
    
    private String colorToString(Color color){
        if(color == Color.BLUE){
            return "b";
        }
        if(color == Color.GRAY){
            return "l";
        }
        if(color == Color.BLACK){
            return "r";
        }
        
        return "";
    }
    
    public Color stringToColor(String colorCode){
        if(colorCode.equals("b")){
            return Color.BLUE;
        }
        if(colorCode.equals("l")){
            return Color.GRAY;
        }
        if(colorCode.equals("r")){
            return Color.BLACK;
        }
        return null;
    }
    
    
    @Override
    public String toString(){
        String data = "";
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                data += "" + colorToString(grid[i][j]);
            }
            data += "\n";
        }
        return data;
    }
}
