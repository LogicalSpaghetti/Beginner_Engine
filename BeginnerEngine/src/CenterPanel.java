import BlockLogic.Block;
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
    Selector selector = new Selector(); // right click, will let you add panels
    public CenterPanel() {
        add(selector);

        setPreferredSize(new Dimension(200, 200));
        setLayout(null);

        addMouseListener(this);
        setFocusable(true);

        setBackground(Color.BLACK);
        setVisible(true);
    }

    public void newBlock() {
        System.out.println(this + " Happened");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == this) {
            if (e.getButton() == 3) {
                Selector.showSelector(selector, e.getPoint());
            }
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

    public static class Selector extends JPanel implements MouseListener {
        public Selector() {
            setSize(200,200);
            setVisible(false);
            addMouseListener(this);
            setFocusable(true);
            setLayout(new OverlayLayout(this));

            SelectorButton[] panes = new SelectorButton[BlockManager.blocks.toArray().length];
            for (int i = 0; i < panes.length; i++) {
                panes[i] = new SelectorButton(BlockManager.blocks.get(i), this);
            }

            JPanel pane = new JPanel();
            pane.setPreferredSize(new Dimension(80,panes.length * 40)); // test:

            pane.setLayout(new GridLayout(panes.length, 1));

            for (SelectorButton jButton : panes) {
                pane.add(jButton);
            }

            JScrollPane scrollPane = new JScrollPane (
                    pane,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
            );

            add (scrollPane);
        }

        public static void showSelector(Selector selector, Point p) {
            Dimension d = selector.getParent().getSize();
            if (p.x + selector.getWidth() > d.width) {
                p.x = d.width - selector.getWidth();
            }
            if (p.y + selector.getHeight() > d.height) {
                p.y = p.y - selector.getHeight();
            }
            selector.setLocation(p);

            selector.setVisible(true);
        }
        public static void hideSelector(Selector selector) {
            selector.setVisible(false);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

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
    public static class SelectorButton extends JButton implements MouseListener {

        BlockType type;
        Selector selector;
        Object centerPanel;

        public SelectorButton(BlockType blockType, Selector selector) {
            setBackground(BlockManager.getColor(blockType.category()));
            type = blockType;
            System.out.println(blockType);
            this.addMouseListener(this);
            this.setFocusable(true);
            this.selector = selector;
            this.centerPanel = selector.getParent();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //newBlock();
            Selector.hideSelector(this.selector);
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

}
