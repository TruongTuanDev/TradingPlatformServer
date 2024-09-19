package server_controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.table.DefaultTableModel;


public class TCPServer extends Thread{
	private ServerSocket serverSocket;
    private DefaultTableModel tableModel;
    private Set<Socket> clientSockets;
    private boolean isRunning = true;

    public TCPServer(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        clientSockets = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("Server started. Listening on port 5000...");

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                
                clientSockets.add(clientSocket);
                System.out.println("Đã có thằng vào hehe");

                // Lấy địa chỉ IP của client và thời gian kết nối
                String clientIP = clientSocket.getInetAddress().toString();
                String connectTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Tạo luồng riêng để xử lý client
                new ClientHandler(clientSocket, tableModel, clientIP, connectTime).start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void stopServer() {
        isRunning = false;
        try {
            for (Socket socket : clientSockets) {
                socket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

