package controller;

import dto.GameDto;
import dto.UserDto;
import service.BookmarkService;
import service.GameService;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;

public class UserView implements Observer {
    private final UserController userController;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private UserDto userDto;

    public JFrame frame;

    public JButton btnAdd = new JButton("Add bookmark");
    public JButton btnRemove = new JButton("Remove bookmark");
    public JButton btnSettings = new JButton("Settings");
    public JButton btnLogout = new JButton("Logout");
    public JToggleButton btnNotif = new JToggleButton();
    private String selectedTeam1;
    private String selectedTeam2;
    private String selectedTeam1Table2;
    private String selectedTeam2Table2;
    private ArrayList<String> categoriesL;
    private JPanel panel2;
    private JTextArea notifications = new JTextArea(5,20);
    private ImageIcon imgNotif1;
    private ImageIcon imgNotif2;

    public Object[] row = new Object[6];
    public Object[] row2 = new Object[6];
    JTable table = new JTable();
    JTable table2 = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
    public DefaultTableModel model2 = new DefaultTableModel();
    public String[] categories = new String[] {"All categories"};

    public JComboBox<String> categoriesList = new JComboBox<>(categories);

    public UserView(UserDto userDto, UserService userService, GameService gameService, BookmarkService bookmarkService) throws IOException {
        this.userController = new UserController(userService, gameService, bookmarkService);
        this.userDto = userDto;
        frame = new JFrame("Flashscore - User");
        initialize();
        frame.setVisible(true);
    }


    private void applyFilter(String filterCategory) {
        ArrayList<GameDto> games = userController.getGames();
        model.setRowCount(0);
        if (games != null) {

            for (GameDto g : games) {
                if (filterCategory.equals("All categories") || filterCategory.equals(g.getCategory())) {
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

        model2.setRowCount(0);
        ArrayList<GameDto> bookmarkedGames = userController.getBookmarkedGames(userDto);
        if (bookmarkedGames != null) {
            for (GameDto g : bookmarkedGames) {
                if (filterCategory.equals("All categories") || filterCategory.equals(g.getCategory())) {
                    row2[0] = g.getTeam1();
                    row2[1] = g.getScoreTeam1();
                    row2[2] = g.getScoreTeam2();
                    row2[3] = g.getTeam2();
                    row2[4] = g.getCategory();
                    row2[5] = g.getStatus();
                    model2.addRow(row2);
                }
            }
        }

    }

    private void resetComboBox () {
        ArrayList<GameDto> games = userController.getGames();
        categories = new String[] {"All categories"};
        categoriesL = new ArrayList( Arrays.asList(categories));
        categoriesList = new JComboBox<>(categories);
        for (GameDto g : games) {
            if (!categoriesL.contains(g.getCategory())) {
                categoriesL.add(g.getCategory());
                categoriesList.addItem(g.getCategory());
            }
        }

        categoriesList.addItemListener(arg0 -> applyFilter(categoriesList.getSelectedItem().toString()));
        panel2.remove(0);
        panel2.add(categoriesList,BorderLayout.NORTH,0);
        panel2.revalidate();
        panel2.repaint();
    }

    private void initialize() throws IOException {
        ArrayList<GameDto> games = userController.getGames();
        ArrayList<GameDto> bookmarkedGames;
        imgNotif1 = new ImageIcon(ImageIO.read(new URL("https://i.imgur.com/IcTMw1Q.png")));
        imgNotif2 = new ImageIcon(ImageIO.read(new URL("https://i.imgur.com/STVjUwR.png")));
        btnNotif.setIcon(imgNotif1);
        notifications.setVisible(false);
        notifications.setEditable(false);
        notifications.setLineWrap(true);

        JLabel lblMesaj = new JLabel ("You're logged as " + userDto.getUsername());
        Object[] columns = {"Team 1","Score Team 1","Score Team 2","Team 2", "Category", "Status"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 700, 200);

        Object[] columns2 = {"Team 1","Score Team 1","Score Team 2","Team 2", "Category", "Status"};
        model2.setColumnIdentifiers(columns2);
        table2.setModel(model2);

        table2.setBackground(Color.LIGHT_GRAY);
        table2.setForeground(Color.black);
        table2.setFont(font);
        table2.setRowHeight(30);

        JScrollPane pane2 = new JScrollPane(table2);
        pane2.setBounds(0, 0, 700, 200);

        if(games != null) {
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

        bookmarkedGames = userController.getBookmarkedGames(userDto);
        if(bookmarkedGames != null) {
            for (GameDto g : bookmarkedGames) {
                row2[0] = g.getTeam1();
                row2[1] = g.getScoreTeam1();
                row2[2] = g.getScoreTeam2();
                row2[3] = g.getTeam2();
                row2[4] = g.getCategory();
                row2[5] = g.getStatus();
                model2.addRow(row2);
            }
        }

        categoriesL = new ArrayList( Arrays.asList(categories));

        for (GameDto g : games) {
            if (!categoriesL.contains(g.getCategory())) {
                categoriesL.add(g.getCategory());
                categoriesList.addItem(g.getCategory());
            }
        }


        JPanel panou = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        JPanel panel6 = new JPanel(new GridLayout(2,1));
        JPanel panel7 = new JPanel(new FlowLayout());
        JPanel panelNotif = new JPanel(new GridLayout(2,1));
        JPanel panelNotifButton = new JPanel(new FlowLayout());
        JPanel panelNotifText = new JPanel(new FlowLayout());
        JPanel panel22 = new JPanel(new GridLayout(2,1));
        panel1.setPreferredSize(new Dimension(700,400));
        panel3.setPreferredSize(new Dimension(700,400));
        panel1.add(pane, BorderLayout.CENTER);
        panel3.add(pane2, BorderLayout.CENTER);
        panel4.add(btnAdd);
        panel4.add(btnRemove);
        panelNotifButton.add(btnNotif);
        panelNotifText.add(notifications);
        panelNotif.add(panelNotifButton);
        panelNotif.add(panelNotifText);
        panel22.add(panel4);
        panel22.add(panelNotif);
        panel5.add(lblMesaj);
        panel7.add(btnSettings);
        panel7.add(btnLogout);
        panel6.add(panel5);
        panel6.add(panel7);
        panel2.add(categoriesList, BorderLayout.NORTH);
        panel2.add(panel22, BorderLayout.CENTER);
        panel2.add(panel6, BorderLayout.SOUTH);
        panou.add(panel1, BorderLayout.WEST);
        panou.add(panel2, BorderLayout.CENTER);
        panou.add(panel3, BorderLayout.EAST);


        frame.add(panou);

        frame.setSize(1700,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        categoriesList.addItemListener(arg0 -> applyFilter(categoriesList.getSelectedItem().toString()));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = table.getSelectedRow();
                selectedTeam1 = model.getValueAt(j, 0).toString();
                selectedTeam2 = model.getValueAt(j, 3).toString();
            }
        });

        btnNotif.addActionListener( e -> {
            notifications.setVisible(!notifications.isVisible());
            if (!notifications.isVisible()) {
                btnNotif.setIcon(imgNotif1);
                notifications.setText("");
            }
        });

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = table2.getSelectedRow();
                selectedTeam1Table2 = model2.getValueAt(j, 0).toString();
                selectedTeam2Table2 = model2.getValueAt(j, 3).toString();
            }
        });

        btnAdd.addActionListener( e -> {
            if(selectedTeam1 != null) {
                userController.addBookmark(userDto, selectedTeam1, selectedTeam2);
                applyFilter(categoriesList.getSelectedItem().toString());
            }
        });

        btnRemove.addActionListener( e -> {
            if(selectedTeam1Table2 != null) {
                userController.removeBookmark(userDto, selectedTeam1Table2, selectedTeam2Table2);
                applyFilter(categoriesList.getSelectedItem().toString());
            }
        });

        btnSettings.addActionListener( e -> {
            new SettingsView(userDto, userController);

        });

        btnLogout.addActionListener( e -> {
            frame.setVisible(false);
            frame.dispose();
        });


    }

    @Override
    public void update(java.util.Observable observable, Object o) {
        String[] details;
        if (o != null) {
            details = (String[]) o;
            boolean bookmarked = false;
            ArrayList<GameDto> bookmarkedGames = userController.getBookmarkedGames(userDto);
            if (bookmarkedGames != null) {
                for (GameDto g : bookmarkedGames) {
                    if (g.getTeam1().equals(details[0]) && g.getTeam2().equals(details[1]))
                        bookmarked = true;
                }
            }
            if (bookmarked) {
                if (!details[8].equals(details[9])) {
                    if (details[9].equals("Started"))
                        notifications.append(details[7] + ": The game between " + details[0] + " and " + details[1] + " has just started.\n");
                    if (details[9].equals("Ended"))
                        notifications.append(details[7] + ": The game between " + details[0] + " and " + details[1] + " has ended. The final score was " + details[4] + "-" + details[5] + "\n");
                }
                if (!details[2].equals(details[4]))
                    notifications.append(details[7] + ": Goal! Team " + details[0] + " scored against team " + details[1] + ". The score is now " + details[4] + "-" + details[5] + "\n");
                if (!details[3].equals(details[5]))
                    notifications.append(details[7] + ": Goal! Team " + details[1] + " scored against team " + details[0] + ". The score is now " + details[4] + "-" + details[5] + "\n");
                if (!notifications.getText().isEmpty())
                    btnNotif.setIcon(imgNotif2);
            }
        }
        resetComboBox();
        applyFilter(categoriesList.getSelectedItem().toString());
    }
}
