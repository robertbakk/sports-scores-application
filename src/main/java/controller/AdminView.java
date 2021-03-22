package controller;

import dto.GameDto;
import service.BookmarkService;
import service.GameService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminView {
    private final AdminController adminController;

    public JFrame frame;
    private JTextField team1;
    private JTextField team2;
    private JTextField category;
    private JTextField updateTeam1;
    private JTextField updateTeam2;
    private JTextField updateCategory;
    private JTextField updateScore1;
    private JTextField updateScore2;
    private String selectedTeam1;
    private String selectedTeam2;
    private String selectedScore1;
    private String selectedScore2;
    private String selectedCategory;
    private String selectedStatus;
    public JButton btnAddGame = new JButton("Add game");
    public JButton btnUpdateGame = new JButton("Update game");
    public JButton btnDeleteGame = new JButton("Delete game");
    public JButton btnSendEmails = new JButton("Send emails");
    public JButton btnConfirmAdd = new JButton("Confirm");
    public JButton btnConfirmUpdate = new JButton("Confirm");
    public Object[] row = new Object[6];
    JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private CardLayout cardLayout = new CardLayout();

    public AdminView(UserService userService, GameService gameService, BookmarkService bookmarkService) {
        this.adminController = new AdminController(userService, gameService, bookmarkService);
        frame = new JFrame("Flashscore - Admin");
        initialize();
        frame.setVisible(true);
    }

    public void addUserView(UserView userView) {
        adminController.addObserver(userView);
    }

    private void updateTable() {
        model.setRowCount(0);
        if(adminController.getGames()!=null) {
            ArrayList<GameDto> games = adminController.getGames();
            for (GameDto g : games) {
                row[0] = g.getTeam1();
                row[1] = g.getScoreTeam1();
                row[2] = g.getScoreTeam2();
                row[3] = g.getTeam2();
                row[4] = g.getCategory();
                row[5] = g.getStatus();
                model.addRow(row);
            }
        }
    }


    private void initialize() {

        JLabel lblTeam1 = new JLabel("Team 1");
        JLabel lblTeam2 = new JLabel("Team 2");
        JLabel lblCategory = new JLabel("Category");

        JLabel lblUpdateTeam1 = new JLabel("Team 1");
        JLabel lblUpdateTeam2 = new JLabel("Team 2");
        JLabel lblUpdateCategory = new JLabel("Category");
        JLabel lblUpdateScore1 = new JLabel("Score 1");
        JLabel lblUpdateScore2 = new JLabel("Score 2");
        JLabel lblUpdateStatus = new JLabel("Status");

        String[] statuses = new String[] {"Not started", "Started", "Ended"};

        JComboBox<String> statusesList = new JComboBox<>(statuses);

        Object[] columns = {"Team 1","Score Team 1","Score Team 2","Team 2", "Category", "Status"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1000, 200);

        if(adminController.getGames()!=null) {
            ArrayList<GameDto> games = adminController.getGames();
            for (GameDto g : games) {
                row[0] = g.getTeam1();
                row[1] = g.getScoreTeam1();
                row[2] = g.getScoreTeam2();
                row[3] = g.getTeam2();
                row[4] = g.getCategory();
                row[5] = g.getStatus();
                model.addRow(row);
            }
        }


        team1 = new JTextField(10);
        team2 = new JTextField(10);
        category = new JTextField(10);
        updateTeam1 = new JTextField(10);
        updateTeam2 = new JTextField(10);
        updateCategory = new JTextField(10);
        updateScore1 = new JTextField(10);
        updateScore2 = new JTextField(10);

        JLabel lblNewLabel = new JLabel("Enter the game details");
        lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD | Font.ITALIC, 16));

        JLabel lblNewLabel2 = new JLabel("Enter the game details");
        lblNewLabel2.setFont(new Font("Franklin Gothic Demi", Font.BOLD | Font.ITALIC, 16));

        JPanel panou = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelButoane = new JPanel(new GridBagLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel11 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new FlowLayout());
        JPanel panel7 = new JPanel(new FlowLayout());
        JPanel panel8 = new JPanel(new FlowLayout());
        JPanel panel9 = new JPanel(new FlowLayout());
        JPanel panel10 = new JPanel(new FlowLayout());
        JPanel cardPanel = new JPanel(cardLayout);
        JPanel panelAdd = new JPanel(new GridLayout(5,1));
        JPanel panelUpdate = new JPanel(new GridLayout(8,1));
        JPanel panelConfirmAdd = new JPanel(new FlowLayout());
        JPanel panelConfirmUpdate = new JPanel(new FlowLayout());
        panel1.add(lblNewLabel);
        panel2.add(lblTeam1);
        panel2.add(team1);
        panel3.add(lblTeam2);
        panel3.add(team2);
        panel4.add(lblCategory);
        panel4.add(category);
        panel5.add(lblUpdateTeam1);
        panel5.add(updateTeam1);
        panel6.add(lblUpdateTeam2);
        panel6.add(updateTeam2);
        panel7.add(lblUpdateScore1);
        panel7.add(updateScore1);
        panel8.add(lblUpdateScore2);
        panel8.add(updateScore2);
        panel9.add(lblUpdateStatus);
        panel9.add(statusesList);
        panel10.add(lblUpdateCategory);
        panel10.add(updateCategory);
        panel11.add(lblNewLabel2);

        panelConfirmAdd.add(btnConfirmAdd);
        panelConfirmUpdate.add(btnConfirmUpdate);

        panelAdd.add(panel11);
        panelAdd.add(panel2);
        panelAdd.add(panel3);
        panelAdd.add(panel4);
        panelAdd.add(panelConfirmAdd);

        panelUpdate.add(panel1);
        panelUpdate.add(panel5);
        panelUpdate.add(panel6);
        panelUpdate.add(panel7);
        panelUpdate.add(panel8);
        panelUpdate.add(panel9);
        panelUpdate.add(panel10);
        panelUpdate.add(panelConfirmUpdate);

        panelButoane.add(btnAddGame);
        panelButoane.add(btnUpdateGame);
        panelButoane.add(btnDeleteGame);
        panelButoane.add(btnSendEmails);
        cardPanel.add(panelAdd, "addpanel");
        cardPanel.add(panelUpdate, "updatepanel");
        panel.add(panelButoane,BorderLayout.NORTH);
        panel.add(cardPanel,BorderLayout.SOUTH);
        panou.add(pane,BorderLayout.CENTER);
        panou.add(panel,BorderLayout.EAST);

        frame.add(panou);

        cardPanel.setVisible(false);

        frame.setSize(1200,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = table.getSelectedRow();
                selectedTeam1 = model.getValueAt(j, 0).toString();
                selectedTeam2 = model.getValueAt(j, 3).toString();
                selectedScore1 = model.getValueAt(j, 1).toString();
                selectedScore2 = model.getValueAt(j, 2).toString();
                selectedCategory = model.getValueAt(j, 4).toString();
                selectedStatus = model.getValueAt(j, 5).toString();
                updateTeam1.setText(model.getValueAt(j, 0).toString());
                updateScore1.setText(model.getValueAt(j, 1).toString());
                updateScore2.setText(model.getValueAt(j, 2).toString());
                updateTeam2.setText(model.getValueAt(j, 3).toString());
                updateCategory.setText(model.getValueAt(j, 4).toString());
                //updateStatus.setText(model.getValueAt(j, 5).toString());
                statusesList.setSelectedItem(model.getValueAt(j, 5).toString());
            }
        });

        btnAddGame.addActionListener(e ->  {
            cardPanel.setVisible(true);
            cardLayout.show(cardPanel,"addpanel");
        });
        btnUpdateGame.addActionListener(e ->  {
            cardPanel.setVisible(true);
            cardLayout.show(cardPanel,"updatepanel");
        });

        btnDeleteGame.addActionListener(e -> {
            cardPanel.setVisible(false);
            adminController.deleteGame(selectedTeam1,selectedTeam2);
            updateTable();
            updateTeam1.setText("");
            updateTeam2.setText("");
            updateScore1.setText("");
            updateScore2.setText("");
            updateCategory.setText("");
        });
        btnConfirmAdd.addActionListener(e -> {
            cardPanel.setVisible(false);
            adminController.addGame(team1.getText(),team2.getText(),category.getText());
            updateTable();
            team1.setText("");
            team2.setText("");
            category.setText("");
        });

        btnSendEmails.addActionListener(e -> {
            cardPanel.setVisible(false);
            adminController.sendEmails(selectedTeam1,selectedTeam2);
            updateTable();
            team1.setText("");
            team2.setText("");
            category.setText("");
            updateTeam1.setText("");
            updateTeam2.setText("");
            updateScore1.setText("");
            updateScore2.setText("");
            updateCategory.setText("");
        });

        btnConfirmUpdate.addActionListener(e -> {
            cardPanel.setVisible(false);
            adminController.updateGame(selectedTeam1,selectedTeam2,updateTeam1.getText(),updateTeam2.getText(), selectedScore1, selectedScore2, updateScore1.getText(),updateScore2.getText(),selectedCategory,updateCategory.getText(),selectedStatus,statusesList.getSelectedItem().toString());
            updateTable();
        });


    }
}
