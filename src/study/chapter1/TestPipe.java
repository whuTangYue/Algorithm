package study.chapter1;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;


public class TestPipe {
	public static void main(String[] args){
		Sender s = new Sender();
		Receiver r = new Receiver();
		PipedInputStream pi = r.getPipedInputStream();
		PipedOutputStream po= s.getPipedOutputStream();
		try
		{
			pi.connect(po);
	}catch(IOException e){
		e.printStackTrace();
	}
		s.start();
		r.start();
	}
}
class Sender extends Thread{
	PipedOutputStream out = null;
	
	public PipedOutputStream getPipedOutputStream()
	{
		out = new PipedOutputStream();
		return out;
	}
	@Override
	public void run()
	{
		try
		{
			out.write("Hello , Receiver!".getBytes());
		}catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			out.close();
		}catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
class Receiver extends Thread{
	PipedInputStream in = null;
	public PipedInputStream getPipedInputStream(){
		in = new PipedInputStream();
		return in;
	}
	@Override
	public void run()
	{
		byte[] bys = new byte[1024];
		try
		{
			in.read(bys);
			System.out.println("Message read:" + new String(bys).trim());
			in.close();
		}catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
