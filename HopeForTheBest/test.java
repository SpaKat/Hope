import java.net.*;
		import java.io.*;
public class test {

	
	public static void main(String[] args) {
		

		URL whatismyip;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
