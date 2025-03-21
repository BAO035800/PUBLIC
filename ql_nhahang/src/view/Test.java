package view;
import javax.swing.*;

public class Test{
    public static void main(String[] args) {
        JFrame frame = new JFrame("GroupLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        JButton btn1 = new JButton("Button 1");
        JButton btn2 = new JButton("Button 2");

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(btn1)
                .addComponent(btn2)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(btn1)
                .addComponent(btn2)
        );

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
