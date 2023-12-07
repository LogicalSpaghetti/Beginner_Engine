import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    CenterPanel selPanel = new CenterPanel();
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Logic Frame");
        setSize(900,700);

        setLayout(new BorderLayout());
        add(selPanel, BorderLayout.CENTER);

        setFocusable(false);

        JPanel tempPanel = new JPanel();
        tempPanel.setPreferredSize(new Dimension(100,100));
        add(tempPanel,BorderLayout.WEST);

        setVisible(true);
    }
}
