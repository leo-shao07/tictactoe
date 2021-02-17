// Java implementation for a client 
// Save file as Client.java 

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*; 
import java.net.*; 
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane; 

// Client class 
public class Client 
{ 
	private static boolean leave;

	public static void main(String[] args) throws IOException 
	{ 
		leave = false;
		try
		{ 
			Frame frame = new Frame();
			 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500, 500);
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            leave = true;
					//JOptionPane.showMessageDialog(null, "you leave");
		            System.out.print("zzzzzzzzzzzzzzzzzzzzzzzzzzz");
		         }        
		      });  
			Scanner scn = new Scanner(System.in); 
			int num;
			// getting localhost ip 
			InetAddress ip = InetAddress.getByName("localhost"); 
	
			// establish the connection with server port 5056 
			Socket s = new Socket("192.168.0.14", 5056); 
	
			// obtaining input and out streams 
			DataInputStream dis = new DataInputStream(s.getInputStream()); 
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	
			// the following loop performs the exchange of 
			// information between client and client handler
			num = dis.read();
			System.out.print(num);
			if(num%2==0){//first
				JOptionPane.showMessageDialog(null, "you go first");
				while(leave == false) {
					while(frame.start==false) {
						System.out.print("a");
					}
					int tosend = frame.back;
					dos.write(tosend);
					frame.start = false;
					if(frame.over==true) {
						dos.write(9);
						break;
					}
					int in = dis.read();
					System.out.println(in);
					frame.go(in);
					if(frame.over==true) {
						dos.write(9);
						break;
					}
				}
				dos.write(10);
			}
			else {//second
				JOptionPane.showMessageDialog(null, "you go second");
				while(leave == false) {
					int in = dis.read();
					System.out.println(in);
					frame.go(in);
					if(frame.over==true) {
						dos.write(9);
						break;
					}
					while(frame.start==false) {
						System.out.print("b");
					}
					int tosend = frame.back;
					dos.write(tosend);
					frame.start=false;
					if(frame.over==true) {
						dos.write(9);
						break;
					}
						
					//System.out.println(dis.readUTF());
					//String tosend = scn.nextLine(); 
					//dos.writeUTF(tosend); 
				}
				dos.write(10);
				System.out.print("xxxxxxxx");
			}
			
			
			// closing resources 
			scn.close(); 
			dis.close(); 
			dos.close(); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
	} 
} 
