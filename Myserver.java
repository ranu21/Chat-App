import java.net.*;
import java.io.*;
public class Myserver {
   
    ServerSocket server;
    Socket socket;
     
     BufferedReader br;
     PrintWriter out;
     public Myserver(){
        try{
           server= new ServerSocket(7777);
           System.out.println("server is ready to accept connection");
           System.out.println("waiting...");

           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out=new PrintWriter(socket.getOutputStream());
            
           startReading();
           startWriting();

        }catch(Exception e){
            e.printStackTrace();
        }

     }

     public void startReading(){
        Runnable r1=() ->{
            System.out.println("Reader started");
            
            while(true){
                try{
                String msg= br.readline();
                if(msg.equals("exit")){
                    System.out.println("client terminated ");
                    break;
                }
                System.out.println("Client: "+msg);
            }catch(Exception e) {
             e.printStackTrace();
            }
         }
     };
        new Thread(r1).start();

     }

     public void startWriting(){
        Runnable r2= ()->{
            while(true){
                try{
                    BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
       new Thread(r2).start();
      }
    public static void main(String args[]){
        System.out.println("this is server...Going to start server..");
        new Myserver();

    }
}
    
