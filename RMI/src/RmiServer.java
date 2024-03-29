import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer extends java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4474380052629460309L;
	String address;
	Registry registry; 

	public void receiveMessage(String x) throws RemoteException{
		System.out.println(x);
	}
	
	public RmiServer() throws RemoteException{
		try{  
			address = (InetAddress.getLocalHost()).toString();
		}
		catch(Exception e){
			System.out.println("can't get address.");
		}
		int port=1099; 
		System.out.println("this address=" + address +  ",port=" + port);
		try{
			registry = LocateRegistry.createRegistry(port);
			registry.rebind("rmiServer", this);
		}
		catch(RemoteException e){
			System.out.println("remote exception"+ e);
		}
	}
	static public void main(String args[]){
		try{
			RmiServer server = new RmiServer();
		}
		catch (Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}