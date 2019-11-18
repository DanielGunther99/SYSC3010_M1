package com.m1.standalonepi;

import java.net.*;
import java.util.Scanner;

public class packetSender {

	public static void main(String[] args) 
   {
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] );
	         socket = new DatagramSocket() ;
	         byte[] packetData = packetReceiver.packetData;
	         while (true)
	         {
	        		 if (packetData.length == 0) {
                                     break;
                                 }
	        		 DatagramPacket packet = new DatagramPacket( packetData, packetData.length, host, 2001 ) ;
	        		 socket.send( packet ) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}
