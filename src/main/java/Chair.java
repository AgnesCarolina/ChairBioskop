import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chair extends JButton implements MouseListener {
    public String noChair, status;

    public Chair(String noChair, String status) {
        this.noChair = noChair;
        this.status = status;
        this.setText(noChair);
        this.addMouseListener(this);
        if (this.getStatus().equalsIgnoreCase("Empty")) {
            this.setBackground(Color.RED);
        } else if(this.getStatus().equalsIgnoreCase("Filled")) {
            this.setBackground(Color.GREEN);
        } else if(this.getStatus().equalsIgnoreCase("Booked")){
            this.setBackground(Color.WHITE);
        }
    }

    public String getNoChair() {
        return noChair;
    }
    public void setNoChair(String noChair) {
        this.noChair = noChair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.getStatus().equalsIgnoreCase("Empty")) {
            this.setStatus("Filled");
            this.setBackground(Color.GREEN);
        } else if(this.getStatus().equalsIgnoreCase("Filled")){
            this.setStatus("Empty");
            this.setBackground(Color.RED);
        } else if(this.getStatus().equalsIgnoreCase("Booked")) {
            this.setBackground(Color.BLUE);
        }

        System.out.println(this.getNoChair() +" : "+this.getStatus());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}