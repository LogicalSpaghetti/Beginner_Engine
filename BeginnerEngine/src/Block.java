import GeneralLogic.BlockManager;
import GeneralLogic.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// a draggable segment of code that stacks with other blocks to form classes
public class Block extends JPanel implements MouseListener, MouseMotionListener {

    Point initialLocation;
    Point initialClick;
    BlockType type;
    JLabel label = new JLabel();
    CenterPanel centerPanel;

    public Block(BlockType type, CenterPanel centerPanel) {
        this.type = type;
        this.centerPanel = centerPanel;
        initializeTextField();

        setSize(100, 100);
        setBackground(BlockManager.getColor(type));

        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    private void initializeTextField() {
        label.setPreferredSize(new Dimension(100, 100));
        label.setOpaque(false);
        label.setBorder(null);
        label.setVisible(true);
        label.setText(type.name());
        label.setVerticalAlignment(SwingConstants.TOP);
        add(label);
        label.setLocation(10, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
        initialClick.translate((int) getLocation().getX(), (int) getLocation().getY());
        initialLocation = getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(BlockManager.getLighterColor(type));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(BlockManager.getColor(type));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (initialClick != null && initialLocation != null) {
            int x = (int) (getLocation().x + e.getX() - initialClick.getX() + initialLocation.getX());
            int y = (int) (getLocation().y + e.getY() - initialClick.getY() + initialLocation.getY());
            setLocation(x, y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}