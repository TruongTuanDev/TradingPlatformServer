package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import server_controller.TCPServer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

public class TCPServerGUI extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private TCPServer server;
    private JLabel clientCountLabel;
    private JLabel activeStatusLabel;

    public TCPServerGUI() {
        // Cấu hình giao diện
        setTitle("TCP Server Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel phía trên để chứa thông tin Number Client và Active
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Nhãn và ô chứa số lượng khách hàng (Number Client)
        clientCountLabel = new JLabel("Number Client: 0");
        topPanel.add(clientCountLabel);

        // Nhãn trạng thái Active
        activeStatusLabel = new JLabel("Active: No");
        topPanel.add(activeStatusLabel);

        add(topPanel, BorderLayout.NORTH);

        // Cấu hình bảng để hiển thị thông tin client
        String[] columnNames = {"IP", "Username", "Email", "Connect Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        // Nút điều khiển start/stop server
        JPanel panel = new JPanel();
        startButton = new JButton("Start Server");
        stopButton = new JButton("Stop Server");
        stopButton.setEnabled(false);

        panel.add(startButton);
        panel.add(stopButton);
        add(panel, BorderLayout.SOUTH);

        // Hành động khi nhấn nút Start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                server = new TCPServer(tableModel, clientCountLabel, activeStatusLabel);
                server.start();
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                activeStatusLabel.setText("Active: Yes");
            }
        });

        // Hành động khi nhấn nút Stop
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server != null) {
                    server.stopServer();
                    JOptionPane.showMessageDialog(null, "Server stopped.");
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    activeStatusLabel.setText("Active: No");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TCPServerGUI().setVisible(true);
            }
        });
    }
}
