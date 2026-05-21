import java.awt.*;
import java.awt.geom.*;

public class GraphicsCard
{
   private String word;
   private boolean isVisible;
   private int shape;
   private Color color;
   
   public GraphicsCard(Color color, int shape)
   {
      this.color = color;
      this.shape = shape;
      this.isVisible = false;
   }
   
   
   public void drawCard(Graphics2D g, int x, int y)
   {
      if (shape == 0)
      {
         g.setColor(Color.BLACK);
         g.fill(new Rectangle(x,y,95,95));
         if (isVisible)
         {
            g.setColor(color);
            g.fill(new Rectangle(x + 20, y + 20, 50, 50));
            g.setColor(Color.WHITE);
            g.draw(new Rectangle(x + 20, y + 20, 50, 50));
         }
      }
      else if (shape == 1)
      {
         g.setColor(Color.BLACK);
         g.fill(new Rectangle(x,y,95,95));
         if (isVisible)
         {
            g.setColor(color);
            g.fill(new Ellipse2D.Float(x + 20, y + 20, 50, 50));
            g.setColor(Color.WHITE);
            g.draw(new Ellipse2D.Float(x + 20, y + 20, 50, 50));
         }
      }
      else
      {
         g.setColor(Color.BLACK);
         g.fill(new Rectangle(x,y,95,95));
         if (isVisible)
         {
            g.setColor(color);
            int xVertices[]={x + 47, x + 20, x + 75};
            int yVertices[]={y + 20, y + 75, y + 75};
            g.fillPolygon(xVertices, yVertices, 3);
            g.setColor(Color.WHITE);
            g.drawPolygon(xVertices, yVertices, 3);
         }
      }
   }
   
   public void toggleWordVisible()
   {
      isVisible = !isVisible;
   }
   
   public boolean isVisible()
   {
      return isVisible;
   }
   
   public boolean compareTo(GraphicsCard card)
   {
      if (this.shape == card.getShape() && this.color == card.getColor())
         return true;
      else
         return false;
   }
   
   //getters
   public int getShape()
   {
      return shape;
   }
   
   public Color getColor()
   {
      return color;
   }
   
}