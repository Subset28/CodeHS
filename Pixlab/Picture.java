import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void testKeepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void negate() {
      Pixel[][] pixels = this.getPixels2D();
      for(Pixel[] rowArray : pixels)
      {
          for(Pixel pixelObj : rowArray)
          {
              pixelObj.setRed(255-pixelObj.getRed());
              pixelObj.setGreen(255-pixelObj.getGreen());
              pixelObj.setBlue(255-pixelObj.getBlue());
          }
      }
  }
  
  public void Grayscale() {
      Pixel[][] pixels = this.getPixels2D();
      for(Pixel[] rowArray : pixels)
      {
          for(Pixel pixelObj : rowArray)
          {
              int average = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
              pixelObj.setRed(average);
              pixelObj.setBlue(average);
              pixelObj.setGreen(average);
          }
      }
  }
  
  public void mirrorArms() {
      Pixel[][] pixels = this.getPixels2D();
      int mirrorPoint = 191;
      for(int row = 158; row < mirrorPoint; row++)
      {
          for(int col = 105; col<170; col++)
          {
              Pixel original = pixels[row][col];
              Pixel mirror = pixels[mirrorPoint + (mirrorPoint - row)][col];
              mirror.setColor(original.getColor());
          }
      }
      
      mirrorPoint = 198;
      for(int row = 170; row <mirrorPoint; row++)
      {
          for(int col = 239; col<293; col++)
          {
              Pixel original = pixels[row][col];
              Pixel mirror = pixels[mirrorPoint + (mirrorPoint - row)][col];
              mirror.setColor(original.getColor());
          }
      }
  }
  
  public void mirrorGull()
  {
      Pixel[][] pixels = this.getPixels2D();
      int mirrorPOint = 345;
      
      for(int row = 230; row<320; row++)
      {
          for(int col = 230; col<mirrorPOint; col++)
          {
              Pixel original = pixels[row][col];
              Pixel mirror = pixels[row][mirrorPOint + (mirrorPOint - col)];
              mirror.setColor(original.getColor());
          }
      }
  }
  
  public void mirrorDiagonal()
  {
      Pixel[][] pixels = this.getPixels2D();
      int maxLength = Math.min(pixels.length, pixels[0].length);
      for(int row = 0; row<maxLength; row++)
      {
          for(int col = 0; col<row; col++)
          {
              Color temp = pixels[row][col].getColor();
              pixels[row][col].setColor(pixels[col][row].getColor());
              pixels[col][row].setColor(temp);
          }
      }
  }
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
