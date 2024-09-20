package server_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.table.DefaultTableModel;

import services.UserService;

public class ClientHandler extends Thread {
	// Lớp xử lý cho từng client kết nối
	    private Socket clientSocket;
	    private DefaultTableModel tableModel;
	    private BufferedReader in;
	    private PrintWriter out;
	    private String clientIP;
	    private String connectTime;
	    private boolean isClosed;
	    private UserService userService;

	    public ClientHandler(Socket socket, DefaultTableModel tableModel, String clientIP, String connectTime) {
	        this.clientSocket = socket;
	        this.tableModel = tableModel;
	        this.clientIP = clientIP;
	        this.connectTime = connectTime;
	    }
	    public BufferedReader getIs() {
	        return in;
	    }

	    public PrintWriter getOs() {
	        return out;
	    }

	    @Override
	    public void run() {
	    	userService = new UserService();
	        try {
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            System.out.println("Đã vào đây "+ isClosed);
	            String message;
	            while (isClosed == false) {
	            	System.out.println("Đã vào đây "+ isClosed);
	                message = in.readLine();
	                System.out.println("Client gửi "+ message);
	                if (message == null) {
	                    break;
	                }
	                String email="";
		            String[] messageSplit = message.split(",");
		            int lengthMessage = messageSplit.length;
		            String username = messageSplit[1];
		            String password = messageSplit[2];
		            if(lengthMessage==4) {
		            	email = messageSplit[3];
		            }
		            Object[] row = {clientIP, username, email, connectTime};
		            tableModel.addRow(row);
	                
					System.out.println("Test : "+messageSplit[0]);
					switch (messageSplit[0]) {
					case "request-login": {
						out.println("login-succses");
						System.out.println("Mày muốn đăng nhập");
						break;
					}
					case "request-register" :{
						
						out.println("register-succses");
						break;
					}
					case "request-check-username" :{
						
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + messageSplit[0]);
					}
					
	            }
	        } catch (IOException e) {
	        	isClosed = true;
	            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
	        } finally {
	            try {
	                clientSocket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}

