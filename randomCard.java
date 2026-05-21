import java.awt.*; 
public class randomCard
{
   private Color[] colors = new Color[8];
   
   public void createColors()
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
   
   public Shape createRandomShape(int x, int y)
   {
      int randomNum = (int)(Math.random() + 1 * 3);
      
      if (randomNum == 1)
         return new Rectangle(x + ,y + , );
      else if (randomNum == 2)
         return new Oval(x,y, );
      else 
         return new Triangle(x,y, );
   }
   
}