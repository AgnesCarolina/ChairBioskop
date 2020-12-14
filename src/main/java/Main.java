import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;
import java.sql.SQLException;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {
    /* Declare */
    JPanel headerPanel, bodyPanel, footerPanel, blokPanel,
            leftTopPanel, centerTopPanel, rightTopPanel,
            leftMiddlePanel, centerMiddlePanel, rightMiddlePanel,
            leftBottomPanel, centerBottomPanel, rightBottomPanel;
    JButton confirm;
    JLabel screenLabel;

    JPanel panelDateTime = new JPanel();
    JPanel panelChair = new JPanel();
    JTabbedPane TabbedPane = new JTabbedPane();

    Vector<Chair> chairs = new Vector<>();

    public void initChair() {
        try {
            Database db = new Database();
            System.out.println("Database is connected: " + db.isConnected());
            chairs = db.selectChair();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public Main() {
        initChair();

        /* START Initialize Objects */
        setLayout(new BorderLayout());
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bodyPanel = new JPanel(new GridLayout(0, 1));
        blokPanel = new JPanel(new GridLayout(3, 3));
        screenLabel = new JLabel("SCREEN");
        leftTopPanel = new JPanel(new GridLayout(3, 4));
        centerTopPanel = new JPanel(new GridLayout(3, 5));
        rightTopPanel = new JPanel(new GridLayout(3, 4));
        leftMiddlePanel = new JPanel(new GridLayout(3, 4));
        centerMiddlePanel = new JPanel(new GridLayout(3, 5));
        rightMiddlePanel = new JPanel(new GridLayout(3, 4));
        leftBottomPanel = new JPanel(new GridLayout(3, 4));
        centerBottomPanel = new JPanel(new GridLayout(3, 5));
        rightBottomPanel = new JPanel(new GridLayout(3, 4));
        footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirm = new JButton("Confirm");

        /* End */

        /* Customize Objects */
        leftTopPanel.setBorder(new EmptyBorder(45, 30, 15, 10));
        centerTopPanel.setBorder(new EmptyBorder(45, 15, 15, 15));
        rightTopPanel.setBorder(new EmptyBorder(45, 10, 15, 30));
        leftMiddlePanel.setBorder(new EmptyBorder(20, 30, 25, 10));
        centerMiddlePanel.setBorder(new EmptyBorder(20, 15, 25, 15));
        rightMiddlePanel.setBorder(new EmptyBorder(20, 15, 25, 30));
        leftBottomPanel.setBorder(new EmptyBorder(15, 30, 40, 15));
        centerBottomPanel.setBorder(new EmptyBorder(15, 15, 40, 15));
        rightBottomPanel.setBorder(new EmptyBorder(15, 15, 40, 30));
        headerPanel.setBackground(Color.darkGray);
        screenLabel.setForeground(Color.white);
        /* End */


        headerPanel.add(screenLabel);

        for (Chair bangku : chairs) {
            String code = bangku.getNoChair();
            String huruf = code.substring(0, 1);
            String angka = code.substring(1, code.length());
            /*End*/

            if ((huruf.equalsIgnoreCase("A") ||
                    huruf.equalsIgnoreCase("B") ||
                    huruf.equalsIgnoreCase("C"))) {
                if (Integer.parseInt(angka) >= 1 && Integer.parseInt(angka) <= 4) leftTopPanel.add(bangku);
                if (Integer.parseInt(angka) >= 5 && Integer.parseInt(angka) <= 9) centerTopPanel.add(bangku);
                if (Integer.parseInt(angka) >= 10 && Integer.parseInt(angka) <= 13) rightTopPanel.add(bangku);
            } else if ((huruf.equalsIgnoreCase("D") ||
                    huruf.equalsIgnoreCase("E") ||
                    huruf.equalsIgnoreCase("F"))) {
                if (Integer.parseInt(angka) >= 1 && Integer.parseInt(angka) <= 4) leftMiddlePanel.add(bangku);
                if (Integer.parseInt(angka) >= 5 && Integer.parseInt(angka) <= 9) centerMiddlePanel.add(bangku);
                if (Integer.parseInt(angka) >= 10 && Integer.parseInt(angka) <= 13) rightMiddlePanel.add(bangku);
            } else if ((huruf.equalsIgnoreCase("G") ||
                    huruf.equalsIgnoreCase("H") ||
                    huruf.equalsIgnoreCase("I"))) {
                if (Integer.parseInt(angka) >= 1 && Integer.parseInt(angka) <= 4) leftBottomPanel.add(bangku);
                if (Integer.parseInt(angka) >= 5 && Integer.parseInt(angka) <= 9) centerBottomPanel.add(bangku);
                if (Integer.parseInt(angka) >= 10 && Integer.parseInt(angka) <= 13) rightBottomPanel.add(bangku);
            }

        }
        blokPanel.add(leftTopPanel);
        blokPanel.add(centerTopPanel);
        blokPanel.add(rightTopPanel);
        blokPanel.add(leftMiddlePanel);
        blokPanel.add(centerMiddlePanel);
        blokPanel.add(rightMiddlePanel);
        blokPanel.add(leftBottomPanel);
        blokPanel.add(centerBottomPanel);
        blokPanel.add(rightBottomPanel);
        bodyPanel.add(blokPanel);
        footerPanel.add(confirm);
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizeChair();
                confirm.setText("Booking Successful");
                setVisible(true);
            }
        });

        panelChair.add(headerPanel);
        panelChair.add(bodyPanel);
        panelChair.add(footerPanel);
        /* END */

        /* START Setting Panel */
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(TabbedPane);
        TabbedPane.add("Date & Time ", panelDateTime);
        TabbedPane.add("Book Chair", panelChair);

        setVisible(true);
        /* END */
    }

    public void finalizeChair(){
        for(int i = 0; i < chairs.size() ; i++){
            if(chairs.get(i).getStatus().equalsIgnoreCase("Filled")){
                chairs.get(i).setStatus("Booked");
                chairs.get(i).setBackground(Color.WHITE);
            }
        }
    }

    public static void main(String[] agrs) {
        new Main();


    }
}