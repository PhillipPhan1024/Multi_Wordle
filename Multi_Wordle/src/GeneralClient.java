import java.io.*;
import java.net.*;
import java.util.Scanner;

//a
public class GeneralClient
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			InetAddress ip = InetAddress.getByName("localhost");
			Socket s = new Socket(ip, 4444);
			
			BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter write = new PrintWriter(s.getOutputStream(), true);
	
			// the following loop performs the exchange of
			// information between client and client handler
			
			Frame frame = new Frame();
			long startTime = System.currentTimeMillis();
						
			frame.wordle();
			long stopTime = System.currentTimeMillis();
			
			long elapsedTime = stopTime - startTime;
		
			String tosend = frame.getResult();
			write.println(elapsedTime + " " + tosend);
			System.out.println(elapsedTime + " " + tosend);
			
			while (true)
			{
				try {
					
					String received = read.readLine();
					System.out.println(received);
					
				} catch (Exception e) {
					
				}
				
				if(frame.getResult().equals("Exit"))
				{
					System.out.println("[CLIENT] " + "Closing this connection : " + s);
					s.close();
					System.out.println("[CLIENT] " + "Connection closed");
					break;
				}
		
				
			}
			
			scanner.close();
			read.close();
			write.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
