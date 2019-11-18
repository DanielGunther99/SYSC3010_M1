
//Code here is modified version of the java receiver from Schramm
//Make sure to cite the source.

/**
 * SYSC3010
 * @author Daniel Gunther
 */
package com.m1.standalonepi;

import java.net.*;

public class packetReceiver {

	private final static int PACKETSIZE = 100 ;
	public static byte[] packetData;

	public static void main( String args[] )
	{ 
	      // Check the argument
	      try
	      {
	         // Convert the argument to ensure that is it valid
	         int port = 2000 ;

	         // Construct the socket
	         DatagramSocket socket = new DatagramSocket( port ) ;
	         System.out.println( "Receiving on port " + port ) ;

	         for( ;; )
	         {
		        DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
	            socket.receive( packet ) ;
				//Make the packet data accesible from any class.
				packetData = packet.getData();
				//For Testing Purposes
	            System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()).trim() ) ;
	        }  
	     }
	     catch( Exception e )
	     {
	        System.out.println( e ) ;
	     }
  }
}
