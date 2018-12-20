import comp102x.IO;
import comp102x.Canvas;
import comp102x.ColorImage;
import java.awt.Graphics;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
 
public class chain implements MouseListener
{
    public int n=0;
   public static int r=0,g=0,w=0;
   public Canvas canvas;
   public char winner='a';
   public Queue<que> q;
      public class node
      {
    public char data;
    public int count;
    public int maxc;
    public node(char d,int c,int m)
    {
        data=d;
        count =c;
        maxc = m;
    }
    }
    public node[][] arr;
   public ColorImage player1,player2;
   
   ColorImage[] hor = new ColorImage[10];
   ColorImage[] ver = new ColorImage[10];
    ColorImage[] red =new ColorImage[180];
   ColorImage[] green=new ColorImage[180];
    ColorImage[] white = new ColorImage[180];
    //ColorImage newGa =new ColorImage("new.png");
    public  int d=0,z=0;
    public chain()
    {
        int i,j;
        canvas = new Canvas(394,500);
         arr = new node[100][100];
        for(i=0;i<10;i++)
        {
            hor[i]=new ColorImage("hor.png");
            ver[i]=new ColorImage("ver.png");
        }
       for(i=0;i<180;i++)
       {
            red[i]=new ColorImage("red.gif");
           green[i] = new ColorImage("green.png");
            white[i] = new ColorImage("white.jpg");
        }
        for(i=57,j=0;i<500&&j<10;i=i+55,j++)
        {
        canvas.add(hor[j],2,i);
    }
     for(i=67,j=0;i<394&&j<10;i=i+65,j++)
        {
        canvas.add(ver[j],i,2);
    }
     for(i=0;i<9;i++)
    {
        for(j=0;j<6;j++)
        {
            int m;
            if(i==0&&j==0 || i==0&&j==5 || i==8&&j==0 || i==8&&j==5)
            m=2;
            else if(i==0 || j==0 || i==8 || j==5)
            m= 3;
            else
            m=4;
            arr[i][j] =new node('a',0,m);
        }
    }
        player1 = new ColorImage("player1.png");
        player2=new ColorImage("player2.png");
      
        canvas.addMouseListener(this);
    }
    public boolean check()
{
    
    if(d<=1)
    {
        d++;
         return(false);
    }
    else{
    int i=0,j=0,k=0;
    char c='a';
    for(i=0;i<9;i++)
    {
        for(j=0;j<6;j++)
        {
            if(arr[i][j].data=='a')
                continue;
            else if(k==0)
            {
                c=arr[i][j].data;
                k++;
            }
            else
            {
                if(arr[i][j].data==c)
                    continue;
                else
                    return(false);
            }
        }
    }
        winner = c;
        return(true);
    }
}
public class que{
    public int xa;
    public int ya;
    public que(){}
    public que(int x,int y)
    {
        xa=x;
        ya=y;
        }
}
    public void mouseClicked(MouseEvent e)
    {
         int x= e.getX();
         int y = e.getY();
         x=x-2;
         y=y-2;
         x=(x/65);
         y=(y/55);
         int temp =x;
         x=y;
         y=temp;
         boolean t=true;
       
    if(t)
    {
        char c;
        if(n%2==0)
            c='*';
        else
            c='#';
            n++;
            if(arr[x][y].data=='a')
            {
                arr[x][y].data=c;
                arr[x][y].count++;
                show(c,arr[x][y].count,y,x);
               }
         else if(arr[x][y].data == c)
            {
                arr[x][y].count++;
                show(c,arr[x][y].count,y,x);
                }
            else 
            {
            d--;
            n--;
        }
   Queue<que> q = new LinkedList<que>();
             if(arr[x][y].count>=arr[x][y].maxc)
            {
                rec(0,c,x,y,q);
             }
          t=check();    
          if(winner=='*')
          {
        canvas.add(player1,50,150);
        
    }
    else if(winner =='#')
    {
        canvas.add(player2,50,150);
       
    }
    }
   
     
    }
    public void mousePressed(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}

public boolean exist(int i,int j)
{
    if(i>=0 && i<=8 &&j>=0 &&j<=5)
        return(true);
    else
        return(false);
}

public void rec(int m,char c,int x,int y,Queue<que> q)
{
    if(m==0)
    {
        arr[x][y].data='a';
        arr[x][y].count=0;
        show('a',0,y,x);
        if(exist(x-1,y))
            {
                que ele = new que(x-1,y);
                arr[x-1][y].data=c;
                arr[x-1][y].count++;       
                show(c,arr[x-1][y].count,y,x-1);
         q.offer(ele);
            }
        if(exist(x+1,y))
            {
                que ele = new que(x+1,y);
                arr[x+1][y].data=c;
                arr[x+1][y].count++;
                show(c,arr[x+1][y].count,y,x+1);
                 q.offer(ele);
            }
        if(exist(x,y-1))
            {
               que ele = new que(x,y-1);
                arr[x][y-1].data=c;
                arr[x][y-1].count++;
             
                show(c,arr[x][y-1].count,y-1,x);
                   q.offer(ele);
            }
        if(exist(x,y+1))
            {
                que ele = new que(x,y+1);
                arr[x][y+1].data=c;
                arr[x][y+1].count++;
                
                show(c,arr[x][y+1].count,y+1,x);
                q.offer(ele);
            }
    
               que ele;
               ele = q.remove();
              
                if(arr[ele.xa][ele.ya].count>=arr[ele.xa][ele.ya].maxc)
                {
                    rec(0,c,ele.xa,ele.ya,q);
                }
            else
            {
               rec(1,c,ele.xa,ele.ya,q);
            }
    }
    else
    {
        que ele = new que();
        if(!(q.isEmpty()))
        {
        ele=q.peek();
        q.remove();
        if(arr[ele.xa][ele.ya].count>=arr[ele.xa][ele.ya].maxc)
        {   
            rec(0,c,ele.xa,ele.ya,q);
        }
        else
        {
            rec(1,c,ele.xa,ele.ya,q);
        }
        }
        else
            return;
    }
}                       
public void show(char c,int co,int j,int k)
{
    if(c=='a')
    canvas.add(white[w++],j*65+4,k*55+6);
    else if(c=='*')
    {
        if(co==1)
        canvas.add(red[r++],j*65+8,k*55+12);
        else if(co==2)
        {
           canvas.add(red[r++],j*65+8,k*55+12);
        canvas.add(red[r++],j*65+30,k*55+12);
    }
        else
        {
             canvas.add(red[r++],j*65+8,k*55+12);
        canvas.add(red[r++],j*65+30,k*55+12);
        canvas.add(red[r++],j*65+20,k*55+30);
    }
    }
    else
    {
     if(co==1)
        canvas.add(green[g++],j*65+8,k*55+12);
        else if(co==2)
        {
         canvas.add(green[g++],j*65+8,k*55+12);
        canvas.add(green[g++],j*65+30,k*55+12);
    }
        else
        {
        canvas.add(green[g++],j*65+8,k*55+12);
        canvas.add(green[g++],j*65+30,k*55+12);
            canvas.add(green[g++],j*65+20,k*55+30);
        }
    }
}

}

