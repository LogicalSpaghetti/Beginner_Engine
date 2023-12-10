import GeneralLogic.BlockManager;
import GeneralLogic.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

// allows users to easily drag and drop blocks into the code area
public class CenterPanel extends JPanel implements MouseListener {

    public ArrayList<Block> blocks = new ArrayList<>();
    JPanel selector = new JPanel();

    public CenterPanel() {
        defineSelector(selector);
        add(selector);

        setPreferredSize(new Dimension(200, 200));
        setLayout(null);

        addMouseListener(this);
        setFocusable(true);

        setBackground(Color.BLACK);
        setVisible(true);
    }

    public static void newBlock(CenterPanel cp, BlockType type, Point loc) {
        cp.blocks.add(new Block(type, cp));

        Block block = cp.blocks.get(cp.blocks.size() - 1);

        cp.add(block);
        loc = new Point(loc.x - cp.getLocationOnScreen().x - 10, loc.y - cp.getLocationOnScreen().y - 10);
        block.setLocation(loc);
        block.setVisible(true);
    }

    public void defineSelector(JPanel selector) {
        selector.setSize(200, 200);
        selector.setVisible(false);
        selector.addMouseListener(this);
        selector.setFocusable(true);
        selector.setLayout(new OverlayLayout(selector));

        SelectorButton[] panes = new SelectorButton[BlockManager.blocks.toArray().length];
        for (int i = 0; i < panes.length; i++) {
            panes[i] = new SelectorButton(this, BlockManager.blocks.get(i));
        }

        JPanel pane = new JPanel();
        pane.setPreferredSize(new Dimension(80, panes.length * 15));

        pane.setLayout(new GridLayout(panes.length, 1));

        for (SelectorButton jButton : panes) {
            pane.add(jButton);
        }

        JScrollPane scrollPane = new JScrollPane(
                pane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );


        selector.add(scrollPane);
    }

    public static void showSelector(JPanel selector, Point p) {
        Dimension d = selector.getParent().getSize();
        if (p.x + selector.getWidth() > d.width) {
            p.x = d.width - selector.getWidth();
        }
        if (p.y + selector.getHeight() > d.height) {
            p.y = p.y - selector.getHeight();
        }
        selector.setLocation(p);

        selector.setVisible(true);
        selector.grabFocus();
    }

    public static void hideSelector(CenterPanel centerPanel) {
        centerPanel.selector.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            showSelector(selector, e.getPoint());
        }
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
