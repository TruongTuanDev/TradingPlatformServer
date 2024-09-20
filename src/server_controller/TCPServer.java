package server_controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class TCPServer extends Thread {
    private ServerSocket serverSocket;
    private DefaultTableModel tableModel;
    private Set<Socket> clientSockets;
    private boolean isRunning = true;
    private JLabel clientCountLabel;  // Nhãn hiển thị số lượng khách hàng
    private JLabel activeStatusLabel; // Nhãn hiển thị trạng thái server

    public TCPServer(DefaultTableModel tableModel, JLabel clientCountLabel, JLabel activeStatusLabel) {
        this.tableModel = tableModel;
        this.clientCountLabel = clientCountLabel;
        this.activeStatusLabel = activeStatusLabel;
        clientSockets = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("Server started. Listening on port 9999...");

            // Cập nhật trạng thái server
            activeStatusLabel.setText("Active: Yes");

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                
                clientSockets.add(clientSocket);
                System.out.println("Client connected!");

                // Cập nhật số lượng client trong nhãn
                updateClientCount();

                // Lấy địa chỉ IP của client và thời gian kết nối
                String clientIP = clientSocket.getInetAddress().toString();
                String connectTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Tạo luồng riêng để xử lý client
                new ClientHandler(clientSocket, tableModel, clientIP, connectTime, this).start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Phương thức cập nhật số lượng client
    public synchronized void updateClientCount() {
        clientCountLabel.setText("Number Client: " + clientSockets.size());
    }

    // Phương thức loại bỏ client khi ngắt kết nối
    public synchronized void removeClient(Socket clientSocket) {
        clientSockets.remove(clientSocket);
        updateClientCount();
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
