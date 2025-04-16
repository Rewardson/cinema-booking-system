import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CinemaBookingSystem {
    private JFrame frame;
    private JButton[][] seats;
    private final int ROWS = 5;
    private final int COLS = 5;
    private List<String> bookedSeats;

    public CinemaBookingSystem() {
        frame = new JFrame("Cinema Booking System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel seatPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));
        seats = new JButton[ROWS][COLS];
        bookedSeats = new ArrayList<>();
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton seat = new JButton("S" + (i * COLS + j + 1));
                seat.setBackground(Color.GREEN);
                seat.setOpaque(true);
                seat.setBorderPainted(false);
                seat.addActionListener(new SeatSelectionListener(i, j));
                seats[i][j] = seat;
                seatPanel.add(seat);
            }
        }
        
        JPanel buttonPanel = new JPanel();
        JButton showBooked = new JButton("Show Booked Seats");
        JButton showAvailable = new JButton("Show Available Seats");
        
        showBooked.addActionListener(e -> showSeats(true));
        showAvailable.addActionListener(e -> showSeats(false));
        
        buttonPanel.add(showBooked);
        buttonPanel.add(showAvailable);
        
        frame.add(seatPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class SeatSelectionListener implements ActionListener {
        int row, col;

        SeatSelectionListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedSeat = seats[row][col];
            if (selectedSeat.getBackground() == Color.GREEN) {
                selectedSeat.setBackground(Color.RED);
                bookedSeats.add(selectedSeat.getText());
            } else if (selectedSeat.getBackground() == Color.RED) {
                selectedSeat.setBackground(Color.GREEN);
                bookedSeats.remove(selectedSeat.getText());
            }
        }
    }

    private void showSeats(boolean booked) {
        StringBuilder seatList = new StringBuilder();
        if (booked) {
            seatList.append("Booked Seats: \n");
            for (String seat : bookedSeats) {
                seatList.append(seat).append(" ");
            }
        } else {
            seatList.append("Available Seats: \n");
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (seats[i][j].getBackground() == Color.GREEN) {
                        seatList.append(seats[i][j].getText()).append(" ");
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(frame, seatList.toString());
    }

    public static void main(String[] args) {
        new CinemaBookingSystem();
    }
}
