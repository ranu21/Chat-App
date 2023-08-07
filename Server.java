
import java.io.*;
import java.net.*;
public class Server {
    ServerSocket server;
    Socket socket; 

    BufferedReader br;
    PrintWriter out;
    // Constructor
    public Server(){
        try{
            server = new ServerSocket(6782);
            System.out.println("Server is ready to accept connection...");
            System.out.println("waiting...");
            socket= server.accept();
            br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out= new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
           
        } catch(Exception e){
            e.printStackTrace();
        } 
     
    }
    // two threads are required to read and write the data simultaneously
    public void startReading(){
      Runnable r1=()->{
        //System.out.println("Reader Started...");
         try{
        while(true){
        
            String msg= br.readLine();
            if(msg.equals("exit")){
                System.out.println("Client terminated the chat..");
                socket.close();
                break;
            }
            System.out.println("Client: "+ msg);
     

        }
     } catch(Exception e){
        //e.printStackTrace();
       // System.out.println("connection is closed");
     }  

      };
      new Thread(r1).start();
    }
    public void startWriting(){
        Runnable r2=()->{
           // System.out.println("writer started..");
            System.out.println("Write your msg now.."); 
            try{
            while(!socket.isClosed()){
                
             BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
              String content= br1.readLine();
              out.println(content);
              out.flush();

              if(content.equals("exit")){
                socket.close();
                break;
              }
    
            }
            System.out.println("connection is closed"); 
         }catch(Exception e){
            e.printStackTrace();
        }

        };
        new Thread(r2).start();

    }

    public static void main(String args[]){
        System.out.println("This is server...Server is getting ready");
        new Server();
    }
    
}
