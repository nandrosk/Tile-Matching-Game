import java.awt.*;

public class GraphicsLab
{
   public static void main(String[] args)
   {
      exercise1();
      exercise3();
      exercise5();
   }
   
   //1
   public static void exercise1()
   {
      DrawingPanel panel = new DrawingPanel(400,400);
      panel.setBackground(Color.CYAN);
      Graphics g = panel.getGraphics();
      
      //draw all 4 grids
      drawE1(g, 0, 0, 100, 5, 1, 1);
      drawE1(g, 10, 120, 24, 4, 5, 5);
      drawE1(g, 150, 20, 40, 5, 6, 6);
      drawE1(g, 130, 275, 36, 3, 3, 3);
   }
   
   public static void drawE1(Graphics g, int x, int y, int size, int numCircles, int rows, int columns)
   {
      for (int c = 0; c < columns; c++)
      {
         for (int r = 0; r < rows; r++)
         {
            drawOneFigure(g, x + (size*r), y + (size*c), size, numCircles); //draw grid of figures with specified rows and columns
         }
      }
   }
   
   public static void drawOneFigure(Graphics g, int x, int y, int size, int numCircles)
   {
      g.setColor(Color.GREEN); 
      g.fillRect(x,y,size,size); //draw background rectangle
      
      int circleSize = size / numCircles;
      for (int i = 0; i < numCircles; i++) //draw circles
      {
         int offset = (i*circleSize);
         g.setColor(Color.YELLOW);
         g.fillOval(x + (i*(circleSize/2)), y + (i*(circleSize/2)), size-offset, size-offset);
         g.setColor(Color.BLACK);
         g.drawOval(x + (i*(circleSize/2)), y + (i*(circleSize/2)), size-offset, size-offset);
      }
      g.drawLine(x + (size/2), y, x + (size/2), y+size); //draw vertical line
      g.drawLine(x, y + (size/2), x+size, y + (size/2)); //draw horizontal line
      g.drawRect(x, y, size, size); //draw outline of rectangle
   }
   
   //3
   public static void exercise3()
   {
      DrawingPanel panel = new DrawingPanel(420,300);
      panel.setBackground(Color.DARK_GRAY);
      Graphics g = panel.getGraphics();
      
      //draw all 3 grids
      drawE3(g, 0, 0, 100, 5, 1);
      drawE3(g, 20, 120, 150, 3, 10);
      drawE3(g, 180, 20, 200, 4, 5);
   }
   
   //NOTE: numSquares is the number of squares in each row and column of each board
   public static void drawE3(Graphics g, int x, int y, int sizeOfFigure, int numSquares, int rowsAndColumns)
   {
      int sizeOfBoard = sizeOfFigure / rowsAndColumns;
      
      for (int c = 0; c < rowsAndColumns; c++)
      {
         for (int r = 0; r < rowsAndColumns; r++)
         {
            drawOneBoard(g, x + (sizeOfBoard*r), y + (sizeOfBoard*c), sizeOfBoard, numSquares); //draw the grid of boards
         }
      }
   }
   
   public static void drawOneBoard(Graphics g, int x, int y, int sizeOfBoard, int numSquares)
   {
      int squareSize = sizeOfBoard / numSquares;
      boolean isWhite = false;
      //keeps track of whether the next square should be white or grey.
      //The first square is always grey, so it starts off false
      
      for (int c = 0; c < numSquares; c++) //draw the grid of squares in the board
      {
         for (int r = 0; r < numSquares; r++)
         {
            if (isWhite) //alternate colors based on isWhite to create the checkerboard pattern
            {
               g.setColor(Color.WHITE);
               g.fillRect(x + (squareSize*r), y + (squareSize*c), squareSize, squareSize);
               isWhite = !isWhite;
            }
            else
            {
               g.setColor(Color.LIGHT_GRAY);
               g.fillRect(x + (squareSize*r), y + (squareSize*c), squareSize, squareSize);
               isWhite = !isWhite;
            }
         }
         
         //if the board has an even number of squares in each row and column, 
         //isWhite needs to be flipped again before the start of a new column
         //to keep the checkerboard pattern
         if (numSquares % 2 == 0)
            isWhite = !isWhite;
      }
      g.setColor(Color.BLACK);
      g.drawRect(x, y, sizeOfBoard, sizeOfBoard); //draw board outline
   }
   
   //5
   public static void exercise5()
   {
      DrawingPanel panel = new DrawingPanel(650,400);
      panel.setBackground(Color.DARK_GRAY);
      Graphics g = panel.getGraphics();
      
      //draw all 6 figures
      drawE5(g, 0, 0, 4, 20, 0, 1);
      drawE5(g, 50, 70, 5, 30, 0, 1);
      drawE5(g, 10, 150, 4, 25, 0, 8);
      drawE5(g, 250, 200, 3, 25, 10, 6);
      drawE5(g, 400, 20, 2, 35, 35, 4);
      drawE5(g, 425, 180, 5, 20, 10, 10);
   }
   
   public static void drawE5(Graphics g, int x, int y, int numPairs, int size, int offset, int columns)
   {
      boolean isOffset = false; //keeps track of whether to offset the row or not
      for (int c = 0; c < columns; c++) //draws the rows
      {
         if (isOffset)
            drawOneLine(g, x + offset, y + (c*size), numPairs, size);
         else
            drawOneLine(g, x, y + (c*size), numPairs, size);
         isOffset = !isOffset;
         y += 2; //adding the bit of space between rows
      }  
   }
   
   public static void drawOneLine(Graphics g, int x, int y, int numPairs, int size)
   {
      for (int i = 0; i < numPairs; i++) //draws one row of the specified number of pairs
      {
         //draws the black square
         g.setColor(Color.BLACK);
         g.fillRect(x + (i*size*2), y, size, size);
         //draws the crossed lines
         g.setColor(Color.BLUE);         
         g.drawLine(x + (i*size*2), y, x + (i*size*2) + size, y + size);         
         g.drawLine(x + (i*size*2), y + size, x + (i*size*2) + size, y);
         //draws the white square
         g.setColor(Color.WHITE);
         g.fillRect(x + (i*size*2)+size, y, size, size);
      }
   }
}