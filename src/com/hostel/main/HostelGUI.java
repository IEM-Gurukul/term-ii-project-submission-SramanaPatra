package com.hostel.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.*;

import com.hostel.model.*;
import com.hostel.service.*;

public class HostelGUI {

    private static List<Student> students = new ArrayList<>();
    private static List<Room> rooms = new ArrayList<>();
    private static HostelService service;

    private static DefaultTableModel tableModel;

    private static JLabel totalStudentsLabel;
    private static JLabel roomsUsedLabel;
    private static JLabel feesPaidLabel;

    public static void main(String[] args) {

        rooms.add(new Room(101,2));
        rooms.add(new Room(102,2));

        service = new HostelService(rooms);

        JFrame frame = new JFrame("Smart Hostel Management System");
        frame.setSize(1000,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /* ---------------- TITLE ---------------- */

        JLabel title = new JLabel("SMART HOSTEL MANAGEMENT SYSTEM",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,28));
        title.setForeground(new Color(45,62,80));
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        frame.add(title,BorderLayout.NORTH);

        /* ---------------- SIDEBAR ---------------- */

        JPanel dashboard = new JPanel();
        dashboard.setLayout(new GridLayout(5,1,15,15));
        dashboard.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        dashboard.setBackground(new Color(45,62,80));

        JButton addStudent = createButton("Add Student");
        JButton allocateRoom = createButton("Allocate Room");
        JButton payFee = createButton("Pay Fee");
        JButton viewStudents = createButton("Refresh Table");
        JButton exit = createButton("Exit");

        dashboard.add(addStudent);
        dashboard.add(allocateRoom);
        dashboard.add(payFee);
        dashboard.add(viewStudents);
        dashboard.add(exit);

        frame.add(dashboard,BorderLayout.WEST);

        /* ---------------- TABLE ---------------- */

        String[] columns = {"ID","Name","Room","Fee Status"};
        tableModel = new DefaultTableModel(columns,0);

        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Arial",Font.PLAIN,14));
        table.setSelectionBackground(new Color(100,149,237));
        table.setGridColor(Color.LIGHT_GRAY);

        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
        table.getTableHeader().setBackground(new Color(45,62,80));
        table.getTableHeader().setForeground(Color.WHITE);

        frame.add(new JScrollPane(table),BorderLayout.CENTER);

        /* ---------------- STATS ---------------- */

        JPanel statsPanel = new JPanel(new GridLayout(1,3,20,20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        totalStudentsLabel = createStatLabel("Total Students: 0");
        roomsUsedLabel = createStatLabel("Rooms Occupied: 0");
        feesPaidLabel = createStatLabel("Fees Paid: 0");

        statsPanel.add(totalStudentsLabel);
        statsPanel.add(roomsUsedLabel);
        statsPanel.add(feesPaidLabel);

        frame.add(statsPanel,BorderLayout.SOUTH);

        /* ---------------- ACTIONS ---------------- */

        addStudent.addActionListener(e -> addStudent());
        allocateRoom.addActionListener(e -> allocateRoom());
        payFee.addActionListener(e -> payFee());
        viewStudents.addActionListener(e -> refreshTable());
        exit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    /* ---------------- BUTTON STYLE ---------------- */

    private static JButton createButton(String text){

        JButton button = new JButton(text);

        button.setFont(new Font("Arial",Font.BOLD,15));
        button.setFocusPainted(false);
        button.setBackground(new Color(70,130,180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(12,10,12,10));

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt){
                button.setBackground(new Color(100,149,237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt){
                button.setBackground(new Color(70,130,180));
            }
        });

        return button;
    }

    /* ---------------- STAT LABEL ---------------- */

    private static JLabel createStatLabel(String text){

        JLabel label = new JLabel(text,SwingConstants.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,15));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return label;
    }

    /* ---------------- ADD STUDENT ---------------- */

    private static void addStudent(){

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] fields = {
                "Student ID:", idField,
                "Student Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);

        if(option == JOptionPane.OK_OPTION){

            try{
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();

                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Name cannot be empty");
                    return;
                }

                students.add(new Student(id,name));
                refreshTable();

                JOptionPane.showMessageDialog(null,"✔ Student Added Successfully");

            } catch(Exception e){
                JOptionPane.showMessageDialog(null,"❌ Invalid Input");
            }
        }
    }

    /* ---------------- ALLOCATE ROOM ---------------- */

    private static void allocateRoom(){

        try{
            String input = JOptionPane.showInputDialog("Enter Student ID");
            if(input == null) return;

            int id = Integer.parseInt(input.trim());

            for(Student s : students){

                if(s.getId() == id){

                    try{
                        service.allocateRoom(s);
                        refreshTable();

                        JOptionPane.showMessageDialog(null,
                                "✔ Room Allocated: "+s.getRoomNumber());

                    } catch(Exception e){
                        JOptionPane.showMessageDialog(null,"❌ "+e.getMessage());
                    }

                    return;
                }
            }

            JOptionPane.showMessageDialog(null,"❌ Student not found");

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"❌ Invalid Input");
        }
    }

    /* ---------------- PAY FEE ---------------- */

    private static void payFee(){

        try{
            String input = JOptionPane.showInputDialog("Enter Student ID");
            if(input == null) return;

            int id = Integer.parseInt(input.trim());

            for(Student s : students){

                if(s.getId() == id){

                    s.payFee();
                    refreshTable();

                    JOptionPane.showMessageDialog(null,"✔ Fee Paid Successfully");
                    return;
                }
            }

            JOptionPane.showMessageDialog(null,"❌ Student not found");

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"❌ Invalid Input");
        }
    }

    /* ---------------- REFRESH TABLE ---------------- */

    private static void refreshTable(){

        tableModel.setRowCount(0);

        int roomsUsed = 0;
        int feesPaid = 0;

        for(Student s : students){

            if(s.getRoomNumber() != 0)
                roomsUsed++;

            if(s.isFeePaid())
                feesPaid++;

            tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getRoomNumber(),
                    s.isFeePaid() ? "Paid" : "Pending"
            });
        }

        totalStudentsLabel.setText("Total Students: "+students.size());
        roomsUsedLabel.setText("Rooms Occupied: "+roomsUsed);
        feesPaidLabel.setText("Fees Paid: "+feesPaid);
    }
}