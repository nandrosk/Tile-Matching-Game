import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.concurrent.TimeUnit;

public class GraphicsGame extends JPanel implements MouseListener
{

   public GraphicsGame()
   {}
   static JFrame f = new JFrame("Memory Game Practice");
   static GraphicsCard[][] cardList = new GraphicsCard[4][4]; //the board
   static int mouseClicks = 0; 
   static GraphicsCard cardVisible1; //keeps track of the card that is visible after the first click
   static GraphicsCard cardVisible2; //keeps track of the card that is visible after the second click
   static int cardVisible1R; //keeps track of the index of the
   static int cardVisible1C; //card that is visible after the first click
   static Color[] colors = new Color[8];
   boolean boardClear = false;
   long timeStart;
   long timeEnd;

   public static void main(String[] args) 
   {  
      GraphicsGame gg = new GraphicsGame();
      
      //set up the board and such
      f.setSize(600,600);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().add(gg);
      f.addMouseListener(gg);
      createBoard();
      f.show();
   }
   
   public void paintComponent(Graphics graphics) 
   {
      Graphics2D g = (Graphics2D) graphics;
      super.paintComponent(g); 
      int x = 90;
      int y = 90;
      int size = 95;
      if (boardClear) //if the board is empty, draw the play again button and show how much time it took them to clear the board
      {
         g.setColor(Color.BLACK);
         g.fill(new Rectangle(250,250,100,50));
         g.setColor(Color.WHITE);
         g.drawString("Play again", 270, 280);
         
         timeEnd = (int)System.currentTimeMillis(); //board clear, stop timer
 
         long time = ((timeEnd - timeStart) / 1000); //calculate time and convert from ms to s

         if (time > 60) //if more than a minute, show the time in minutes and seconds
         {
            int minutes = (int)( time / 60);
            int seconds = (int)(time % 60);
            g.setColor(Color.BLACK);
            g.drawString("Time:", 280, 330);
            g.drawString("" + minutes + " min", 277, 350);
            g.drawString("" + seconds + " sec", 277, 360);
         }
         else //otherwise, just show it as seconds
         {
            g.setColor(Color.BLACK);
            g.drawString("Time:", 280, 330);
            g.drawString("" + time + " sec", 277, 350);
         }
      }
      else //draw the board of cards
      {
         for (int c = 0; c < cardList.length; c++)
         {
            x = 90;
            for (int r = 0; r < cardList[c].length; r++)
            {
               if (cardList[r][c] != null)
                  cardList[r][c].drawCard(g, x + (r*size), y + (c * size));
               x += 10;
            }
            y += 10;
         }
      }
   }
   
   public void mousePressed(MouseEvent e)
   {
      int mouseX = e.getX();
      int mouseY = e.getY();
      
      if (boardClear) //if the board is empty,
      {
         if (mouseX >= 250 && mouseX <= 350 && mouseY >= 270 && mouseY <= 320) //check if they clicked on the play again button
         {
            //if so, start a new game
            boardClear = false;
            for (int c = 0; c < cardList.length; c++)
            {
               for (int r = 0; r < cardList[c].length; r++)
               {
                  cardList[r][c] = null;
               }
            }
            createBoard();
            cardVisible1 = null; //reset to null so the game knows to start the timer on the first click
            f.repaint(); 
         }
      }
      else
      {
         //figure out the row and column of the card that was clicked on
         int cardClickedR = whichCard(mouseX);
         int cardClickedC = whichCard(mouseY);
         
         
         if (cardClickedR != -1 && cardClickedC != -1) //checking to made sure they actually clicked on a card
         {
            if (mouseClicks == 0) //if it's the first card in the pair,
            {
               //if it's the first click of the game (determined based on if cardVisible1 is null), start timer
               if (cardVisible1 == null)
               {
                  timeStart = (int)System.currentTimeMillis();
                  System.out.println("start");
               }
               if (cardVisible1 != null && cardVisible2 != null) //and the last pair clicked wasn't a match
               {
                  //flip the two cards over
                  cardVisible1.toggleWordVisible();
                  cardVisible2.toggleWordVisible();
               }
               if (cardList[cardClickedR][cardClickedC] != null && (!cardList[cardClickedR][cardClickedC].isVisible())) //if the card being clicked on hasn't been removed already and isn't already visible,
               {
                  //flip it over and store necessary info
                  cardVisible1 = cardList[cardClickedR][cardClickedC];
                  cardList[cardClickedR][cardClickedC].toggleWordVisible(); 
                  cardVisible1R = cardClickedR;
                  cardVisible1C = cardClickedC;
                  mouseClicks++;
               }
            }
            else if (mouseClicks == 1) //if it's the second card being clicked,
            {
               if (cardList[cardClickedR][cardClickedC] != null /*&& !cardList[cardClickedR][cardClickedC].isVisible()*/ && !cardList[cardClickedR][cardClickedC].equals(cardVisible1)) //and it's hasn't already been removed, isn't the same card that was just clicked,
               {
                  //flip it over, record necessary info, and check if it's a match
                  cardVisible2 = cardList[cardClickedR][cardClickedC];
                  cardList[cardClickedR][cardClickedC].toggleWordVisible();
                  
                  if (cardList[cardClickedR][cardClickedC].compareTo(cardList[cardVisible1R][cardVisible1C])) //if match, remove both cards
                  {
                     cardList[cardClickedR][cardClickedC] = null;
                     cardList[cardVisible1R][cardVisible1C] = null;
                     //check if the board is clear
                     boardClear = true;
                     for (int i = 0; i < cardList.length; i++)
                     {
                        if (!boardClear) //if the inner loop found something we're done
                           break; 
                        for (int j = 0; j < cardList.length; j++)
                        {
                           if (cardList[i][j] != null)
                           {
                              boardClear = false;
                              break;
                           }
                        }
                     }
                  }
                  mouseClicks = 0; //reset mouse click counter
               }
            }
         }
         f.repaint(); //update board graphics
      }
   }
   
   public int whichCard(int num) //determines the row or column index number of the card being clicked on
   {
      if (num >= 115 && num <= 210)
         return 0;
      else if (num >= 220 && num <= 315)
         return 1;
      else if (num >= 325 && num <= 420)
         return 2;
      else if (num >= 430 && num <= 525)
         return 3;
      else
         return -1;
   }
   
   public static void createBoard()
   {
      createColors();
      for (int i = 0; i < colors.length; i++)
      {
         int shape = (int)(Math.random() * 3); //generating number that determines the shape on the card
         
         //location of the first card in the pair
         int tempR = (int)(Math.random() * 4);
         int tempC = (int)(Math.random() * 4);
         //if the spot already has a card, generate a new random location
         while (cardList[tempR][tempC] != null)
         {
            tempR = (int)(Math.random() * 4);
            tempC = (int)(Math.random() * 4);
         }
         cardList[tempR][tempC] = new GraphicsCard(colors[i], shape); //create the card
         
         //location of the second card in the pair
         tempR = (int)(Math.random() * 4);
         tempC = (int)(Math.random() * 4);
         //if the spot already has a card, generate a new random location
         while (cardList[tempR][tempC] != null)
         {
            tempR = (int)(Math.random() * 4);
            tempC = (int)(Math.random() * 4);
         }
         cardList[tempR][tempC] = new GraphicsCard(colors[i], shape); //create the card
      }
   }
   
   public static void createColors() //fills the array of colors used to create the cards
   {
      colors[0] = Color.RED;
      colors[1] = Color.ORANGE;
      colors[2] = Color.YELLOW;
      colors[3] = Color.GREEN;
      colors[4] = Color.BLUE;
      colors[5] = Color.MAGENTA;
      colors[6] = Color.PINK;
      colors[7] = Color.GRAY;
   }
   
   //necessary override methods
   @Override
   public void mouseEntered(MouseEvent e) 
   {         
   }

   @Override
   public void mouseExited(MouseEvent e) 
   {        
   }
   
   @Override
   public void mouseReleased(MouseEvent e) 
   {
   }
   
   public void mouseClicked(MouseEvent e) 
   {         
   }
   
}