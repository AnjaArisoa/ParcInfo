package parc;
import parc.*;
import java.net.*;
import java.util.concurrent.TimeoutException;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class ServiceClient  implements Runnable{ 
    private  Socket ma_connection;      
    private  String id;

  private  void terminer(){
	try{
	    if (ma_connection != null)      
		{System.out.format("Terminaison pour %s\n", id); ma_connection.close();} 
		    	}
	catch (IOException e) {
	    System.out.format("Terminaison pour %s\n", id);
	    e.printStackTrace();
	}
	return;
    }

    public ServiceClient(Socket la_connection, String mid)
    {
	ma_connection= la_connection;
	id=mid;  
    }
    
    public  void run(){
		// Phase d initialisation 
    	BufferedReader flux_entrant=null;
    	PrintWriter ma_sortie =null;
    	try{ 
	    InputStreamReader isr = new InputStreamReader(ma_connection.getInputStream(), "UTF-8");//getInputStream return zavatra azo,lecture socket
	    flux_entrant = new BufferedReader(isr) ; //les donnees venat du client 
	    ma_sortie = new PrintWriter(ma_connection.getOutputStream() , true);//l'ip du client et port ou va entrer le client dans le serveur,getOutputStream:ecriture socket
	    String c_ip = ma_connection.getInetAddress().toString() ;//ip pc
	    int c_port= ma_connection.getPort();  //port d'entree  
	    ma_sortie.format("[%s] : Hello %s  sur le port %d, \n" ,  id, c_ip, c_port );//respecte format dams serveur
    	} 
    	catch (Exception e1) {
		System.out.println("Erreur d initialisation") ;e1.printStackTrace();} 	
	
	    String  message_lu = new String();
	    // Fin de l initialisation
	    
	    // Boucle principale //
		JFrame jf=new JFrame();
	    while ( true  ) 
 	    {
	    	try {
				message_lu = flux_entrant.readLine();//message lire
			} 
	    	catch (IOException ioe) { ;} 
	    	if (message_lu == null){
		    	System.out.println( "Client deconnecté, je termine\n" )  ;
			    terminer();//deconnexion
			    return; }
		    System.out.format( "%s --> [%s]]\n", id, message_lu);
			jf.setSize(840,560);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setResizable(false);
            jf.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
            jf.setVisible(true);
               JLabel jb=new JLabel();
              jb.setText(message_lu);
              jf.add(jb);
	    	
	    }
 	   
    }}
