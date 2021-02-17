import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.*;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Frame extends JFrame{
	int back;
	boolean start = false;
	private JLabel[] map = new JLabel[9];
	private int[] map2={0,0,0,0,0,0,0,0,0};//1 x  2 o
	boolean flag = true;//true x    false o
	private ImageIcon circle,cross;
	private Image image,change,circle2,cross2;
	boolean over = false;
	
	public Frame(){
		super("tictactoe");
		
		cross = new ImageIcon(getClass().getResource("cross.png"));
		image = cross.getImage();
		change = image.getScaledInstance(70, 70, java.awt.Image.SCALE_AREA_AVERAGING);
		cross = new ImageIcon(change);
		//corss2 = change;
		
		circle = new ImageIcon(getClass().getResource("circle.png"));
		image = circle.getImage();
		change = image.getScaledInstance(70, 70, java.awt.Image.SCALE_AREA_AVERAGING);
		circle = new ImageIcon(change);
		
		setLayout(new GridLayout(3, 3));
		//MouseHandler mouseHandler = new MouseHandler();
		for(int i = 0;i<9;i++) {
			map[i] = new JLabel();
			map[i].setBackground(Color.black);
			map[i].setOpaque(true);
			add(map[i]);
			map[i].setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
			//map[i].addMouseMotionListener(mouseHandler);
			map[i].addMouseListener(new MouseClickHandler()); // add handler
			//map[i].addMouseListener(mouseHandler);
		}
		
	}
	
   private class MouseClickHandler extends MouseAdapter {
      public void mouseClicked(MouseEvent event){
    	  
    	  if(flag == true) {
    		  for(int i = 0;i < 9;i++) {
    			  if(event.getComponent().equals(map[i])) {
    				  System.out.printf("%d", i);
    				  map2[i] = 1;
    				  map[i].setIcon(cross);
    				  back = i;
    				  start = true;
    			  }
    		  }
    	  }
    	  else {
    		  for(int i = 0;i < 9;i++) {
    			  if(event.getComponent().equals(map[i])) {
    				  System.out.printf("%d", i);
    				  map2[i] = 2;
    				  map[i].setIcon(circle);
    				  back = i;
    				  start = true;
    			  }
    		  }
    	  }
    	  if(check()==1 && flag == true) {
    		  //x win
    		  over=true;
    		  JOptionPane.showMessageDialog(null,"cross win");
    		  System.exit(1);
    	  }
    	  if(check()==1 && flag == false) {
    		  //o win
    		  over = true;
    		  JOptionPane.showMessageDialog(null,"circle win");
    		  System.exit(1);
    	  }
    	  if(check()==2) {
    		  //draw
    		  over=true;
    		  JOptionPane.showMessageDialog(null,"draw");
    		  System.exit(1);
    	  }
    	  flag = !flag;
      }
   } 
   public int check() {
	   if(map2[0] == map2[1]&& map2[0]==map2[2] &&map2[0] != 0) return 1;
	   if(map2[3] == map2[4]&& map2[3]==map2[5] &&map2[3] != 0) return 1;
	   if(map2[6] == map2[7]&& map2[6]==map2[8] &&map2[6] != 0) return 1;
	   if(map2[0] == map2[3]&& map2[0]==map2[6] &&map2[0] != 0) return 1;
	   if(map2[1] == map2[4]&& map2[1]==map2[7] &&map2[1] != 0) return 1;
	   if(map2[2] == map2[5]&& map2[2]==map2[8] &&map2[2] != 0) return 1;
	   if(map2[0] == map2[4]&& map2[0]==map2[8] &&map2[0] != 0) return 1;
	   if(map2[2] == map2[4]&& map2[2]==map2[6] &&map2[2] != 0) return 1;
	   for(int i = 0 ;i<9;i++) {
		   if(map2[i] == 0) return 0;
	   }
	   return 2;
   }
   
   public int get() {
	   return back;
   }
   
   public void go(int i) {
	   if(flag==true) {
		   map2[i] = 1;
		   map[i].setIcon(cross);
	   }
	   else {
		   map2[i] = 2;
		   map[i].setIcon(circle);
	   }
	   if(check()==1 && flag == true) {
	 		  //x win
		   	  over=true;
	 		  JOptionPane.showMessageDialog(null,"cross win");
	 		  System.exit(1);
	 	  }
	 	  if(check()==1 && flag == false) {
	 		  //o win
	 		  over=true;
	 		  JOptionPane.showMessageDialog(null,"circle win");
	 		  System.exit(1);
	 	  }
	 	  if(check()==2) {
	 		  //draw
	 		  over=true;
	 		  JOptionPane.showMessageDialog(null,"draw");
	 		  System.exit(1);
	 	  }
	   flag = !flag;
   }
}

