
import comp102x.Canvas;
import comp102x.ColorImage;
import java.awt.Graphics;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;

public class playGame implements MouseListener
{
   public Canvas canvas2;
    public ColorImage logo,play;
    public playGame()
    {
        int i,j;
       // pause(1000);
        canvas2 = new Canvas(394,500);
        logo=new ColorImage("logo.jpg");
        play=new ColorImage("play.png");
      canvas2.add(logo,100,100);
      canvas2.add(play,180,400);
        canvas2.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e){
        int x =e.getX();
        int y=e.getY();
        if(x>=180 && x<=230 && y>=400 && y<=450)
    {
        chain game=new chain();
    }
    }
    public void mousePressed(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   

}
