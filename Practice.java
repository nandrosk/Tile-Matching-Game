import javax.swing.*; 
import java.awt.*;
import java.awt.event.*; 
import java.awt.geom.*; 

public class Practice extends JPanel implements MouseListener
{
   Practice()
   {
   }
     
   static JFrame f = new JFrame("Memory Game Practice");
   static GraphicsCard[][] cardList = new GraphicsCard[4][4];
   static int mouseClicks = 0;
   static GraphicsCard cardVisible1;
   static GraphicsCard cardVisible2;
      
   public static void main(String[] args)
   {
      Practice m = new Practice();
      JPanel p = new JPanel();
        
      for (int i = 0; i < 4; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            cardList[i][j] = new GraphicsCard("hello");
         }
      }
      f.setSize(600,600);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().add(m);
      f.addMouseListener(m);
      f.show();
   }
   
   @Override
   public void paintComponent(Graphics graphics) 
   {
      Graphics2D g = (Graphics2D) graphics;
      super.paintComponent(g); 
      int x = 90;
      int y = 90;
      int size = 95;
      for (int c = 0; c < 4; c++)
      {
         x = 90;
         for (int r = 0; r < 4; r++)
         {
            if (cardList[r][c] != null)
               cardList[r][c].drawCard(g, x + (r*size), y + (c * size));
            x += 10;
         }
         y += 10;
      }
   }
   
   public void mousePressed(MouseEvent e)  
   {
      int mouseX = e.getX();
      int mouseY = e.getY();
              
      int cardClickedR = whichCard(mouseX);
      int cardClickedC = whichCard(mouseY);
      if (cardClickedR != -1 && cardClickedC != -1)
      {
            if (mouseClicks == 0)
           {
              if (cardVisible1 != null && cardVisible2 != null)
              {
                 cardVisible1.toggleWordVisible();
                 cardVisible2.toggleWordVisible();
              }
              if (!cardList[cardClickedR][cardClickedC].isVisible())
              {
                 cardVisible1 = cardList[cardClickedR][cardClickedC];
                 cardList[cardClickedR][cardClickedC].toggleWordVisible(); 
                 mouseClicks++;
              }
           }
           else if (mouseClicks == 1)
           {
              if (!cardList[cardClickedR][cardClickedC].isVisible() && !cardList[cardClickedR][cardClickedC].equals(cardVisible1))
              {  
                 cardVisible2 = cardList[cardClickedR][cardClickedC];
                 cardList[cardClickedR][cardClickedC].toggleWordVisible();
                 mouseClicks = 0;
              }
           }
       }
       f.repaint();
   }
   
   public int whichCard(int num)
   {
      if (num >= 90 && num <= 185)
         return 0;
      else if (num >= 195 && num <= 290)
         return 1;
      else if (num >= 300 && num <= 395)
         return 2;
      else if (num >= 405 && num <= 500)
         return 3;
      else
         return -1;
   }

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