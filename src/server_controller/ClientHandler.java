package server_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.table.DefaultTableModel;
import model.User;
import services.UserService;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private DefaultTableModel tableModel;
    private BufferedReader in;
    private PrintWriter out;
    private String clientIP;
    private String connectTime;
    private TCPServer server;
    private UserService userService;

    public ClientHandler(Socket socket, DefaultTableModel tableModel, String clientIP, String connectTime, TCPServer server) {
        this.clientSocket = socket;
        this.tableModel = tableModel;
        this.clientIP = clientIP;
        this.connectTime = connectTime;
        this.server = server;
    }

    @Override
    public void run() {
        userService = new UserService();
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client gửi: " + message);

                String[] messageSplit = message.split(",");
                String username = messageSplit[3];
                String email = messageSplit[2];
                String password = messageSplit[1];
                Object[] row = {clientIP, username, email, connectTime};
//                Object[] row = {1, 1, 1, 1};
	            tableModel.addRow(row);

                // Xử lý thông điệp và thực hiện hành động
                switch (messageSplit[0]) {
                    case "request-login":
                        System.out.println("Mày muốn đăng nhập");
                        break;

                    case "request-register":
                        User user = userService.createUser(username, password, email);
                        if (user != null) {
                            out.println("register-success");
                        } else {
                            out.println("register-fail");
                        }
                        break;

                    case "request-check-username":
                        // Thực hiện kiểm tra tên người dùng (nếu cần)
                        break;

                    default:
                        throw new IllegalArgumentException("Unexpected value: " + messageSplit[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Xóa client khỏi danh sách và cập nhật số lượng client
            server.removeClient(clientSocket);
        }
    }
}
