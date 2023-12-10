import GeneralLogic.BlockManager;
import GeneralLogic.BlockType;

import javax.swing.*;
import javax.swing.border.Border;
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
        setBackground(BlockManager.getColor(type.category()));

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
        label.setLocation(10,0);

        System.out.println(label);
    }

    private void trySnap() {
        // when released, look for the nearest block within range and snap to it
        int snapBoundsHeight = 60;
        if (getHeight() / 2 < snapBoundsHeight) {
            snapBoundsHeight = getHeight() / 2;
        }

        Rectangle topCheck = new Rectangle();
        Rectangle bottomCheck = new Rectangle();

        topCheck.setBounds(
                getX() - 30,
                getY() - 30,
                60,
                snapBoundsHeight
        );
        bottomCheck.setBounds(
                getX() - 30,
                getY() + getHeight() - snapBoundsHeight,
                60,
                snapBoundsHeight + 30
        );

        for (int i = 0; i < centerPanel.blocks.size(); i++) {
            Block currentBlock = centerPanel.blocks.get(i);
            Point bottomOfCurrent = new Point(currentBlock.getX(), currentBlock.getY() + currentBlock.getHeight());
            Point topOfCurrent = currentBlock.getLocation();

            if (topCheck.contains(bottomOfCurrent)) {
                setLocation(bottomOfCurrent);
                break;
            } else if (bottomCheck.contains(topOfCurrent)) {
                setLocation((int) topOfCurrent.getX(), (int) (topOfCurrent.getY() - getHeight()));
                break;
            }
        }
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
        trySnap();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground();
    }

    @Override
    public void mouseExited(MouseEvent e) {

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