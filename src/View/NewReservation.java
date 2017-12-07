//package View;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.Vector;
//
//import static Controller.NewBookingController.getDates;
//import static Modules.V1_Database.LoadEntireDB;
//
//public class NewReservation implements ActionListener
//{
//
//    private JFrame newReservationWindow;
//    private Container basePane;
//
//    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    Double width = screenSize.getWidth();
//    Double height = screenSize.getHeight();
//
//    public static void main(String[] args)
//    {
//        LoadEntireDB();
//        NewReservation newReservation = new NewReservation();
//        newReservation.makeFrame();
//
//    }
//
//    public NewReservation()
//    {
//    }
//
//    public void makeFrame()
//    {
//        newReservationWindow = new JFrame("Make New Reservation");
//        newReservationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        newReservationWindow.setVisible(true);
//
//        basePane = newReservationWindow.getContentPane();
//        basePane.setLayout(new BorderLayout(6,6));
//        basePane.setVisible(true);
//        makeNorthPane();
//
//        newReservationWindow.pack();
//        newReservationWindow.setSize(((int)(width/3)), ((int)(height/2)));
//
//    }
//
//    public void makeNorthPane()
//    {
//        JPanel northpane = new JPanel();
//        basePane.add(northpane, BorderLayout.NORTH);
//        northpane.setLayout(new GridLayout(1, 3));
//
//        JLabel pickADate = new JLabel("Pick a date");
//        northpane.add(pickADate);
//        pickADate.setHorizontalAlignment(JTextField.CENTER);
//
//        String[] dates = getDates().toArray(new String[getDates().size()]);
//
//
////create JComboBox and assign it to the comboBox
//        JComboBox comboBox1 = new JComboBox(dates);
//        northpane.add(comboBox1);
//
//        ItemListener itemListener = new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                String OutStrng = "";
//                if(e.getStateChange() == ItemEvent.SELECTED)
//                {
//                    OutStrng += "Selected: " + (String)e.getItem();
//                }
//
//                else
//                {
//                    OutStrng += "DeSelected: " + (String)e.getItem();
//                    showStatus(OutStrng);
//
//                }
//
//                System.out.println(e.getStateChange());
//
//            }
//        };
//        comboBox1.addItemListener(itemListener);
//    }
//    public void actionPerformed(ActionEvent actionEvent) {
//
//    }
//
//}
