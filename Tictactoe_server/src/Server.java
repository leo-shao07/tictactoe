// Java implementation of Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

// Server class 
public class Server 
{ 
	static List<Socket> sockets = new Vector<>();
	public static void main(String[] args) throws IOException 
	{ 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056); 
		int num = 0;
		// running infinite loop for getting 
		// client request 
		while (true) 
		{ 
			Socket s = null; 
			
			try
			{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				sockets.add(s);
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				System.out.println("Assigning new thread for this client"); 

				// create a new thread object
				//ClientHandler t = new ClientHandler(s, dis, dos,num); 
				Thread t = new Thread(new ClientHandler(s,dis,dos,num));
				num++;
				// Invoking the start() method 
				t.start(); 
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 

// ClientHandler class 
class ClientHandler extends Server implements Runnable 
{ 
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	final DataInputStream dis;
	final DataOutputStream dos; 
	DataInputStream dis2;
	DataOutputStream dos2;
	final Socket s; 
	int num,num2;//num your index num2 your component index

	// Constructor 
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,int num) 
	{ 
		this.s = s; 
		this.dis = dis; 
		this.dos = dos; 
		this.num = num;
	} 

	@Override
	public void run() 
	{ 
		int received; 
		int toreturn; 
		//while (true) 
		//{ 
			try { 
				dos.write(num);
				if(num %2 ==0)	num2 = num+1;
				else num2 = num -1;
				//received = dis.readUTF();
				//System.out.print(num+" "+received+"\n");
				while(true) {
					try {
						received = dis.read();
						if(received==9) 
						{ 
							System.out.println("Client " + this.s + " sends exit..."); 
							System.out.println("Closing this connection."); 
							this.s.close(); 
							//sockets.get(num2).close();
							System.out.println("Connection closed"); 
							break; 
						} 
						System.out.print(num+" "+received+"\n");
						dos2 = new DataOutputStream(sockets.get(num2).getOutputStream());
						dos2.write(received);
					}
					catch(Exception e){
						this.s.close();
						break;
					}
				}
				/*for (Socket sc : sockets) {
		        }*/
				// Ask user what he wants 
				/*dos.writeUTF("What do you want?[Date | Time]..\n"+ 
							"Type Exit to terminate connection."); 
				
				// receive the answer from client 
				received = dis.readUTF(); 
				
				if(received.equals("Exit")) 
				{ 
					System.out.println("Client " + this.s + " sends exit..."); 
					System.out.println("Closing this connection."); 
					this.s.close(); 
					System.out.println("Connection closed"); 
					break; 
				} 
				
				// creating Date object 
				Date date = new Date(); 
				
				// write on output stream based on the 
				// answer from the client 
				switch (received) { 
				
					case "Date" : 
						toreturn = fordate.format(date); 
						dos.writeUTF(toreturn); 
						break; 
						
					case "Time" : 
						toreturn = fortime.format(date); 
						dos.writeUTF(toreturn); 
						break; 
						
					default: 
						dos.writeUTF("Invalid input"); 
						break; 
				}*/ 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		//} 
	} 
} 
