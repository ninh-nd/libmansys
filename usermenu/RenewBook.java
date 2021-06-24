package usermenu;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import person.NormalUser;

public class RenewBook extends JFrame {
    private JFrame frmRenew;
    private JPanel contentPane;
    private JTable table = new JTable() {
        @Override
        public Class getColumnClass(int column) {
            return (getValueAt(0, column).getClass());
        } //Render true/false as checkboxes
        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == table.getColumnCount() - 1) return true;
            else return false;
        }
    };
    private static Vector<Boolean> checkbox = new Vector<Boolean>();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RenewBook frame = new RenewBook();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RenewBook() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 608, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        frmRenew = new JFrame("User Functions");
        frmRenew.setBounds(100, 100, 450, 300);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 592, 187);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Select books to renew:");
        lblNewLabel.setBounds(10, 11, 153, 20);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 33, 592, 154);
        panel.add(scrollPane);

        scrollPane.setViewportView(table);
        DefaultTableModel tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(UserMenu.user.viewRentedBooks(UserMenu.user));
        checkbox.clear();
        for (int i=0; i< tableModel.getRowCount(); i++) {
            checkbox.add(false);
        }

        tableModel.addColumn("Select", checkbox);
        table.setModel(tableModel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> renewList = new ArrayList<Integer>();
                for (int i=0; i< tableModel.getRowCount(); i++) {
                    if ((boolean) table.getModel().getValueAt(i, 6)) renewList.add((Integer) table.getModel().getValueAt(i, 0));
                }
                if (NormalUser.renewBook(renewList)) {
                	dispose();
                	UserMenu.frmUserFunctions.setState(Frame.NORMAL);
                }
            }
        });
        submitButton.setBounds(251, 215, 89, 23);
        contentPane.add(submitButton);
    }
}