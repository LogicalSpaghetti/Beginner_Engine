import GeneralLogic.BlockManager;
import GeneralLogic.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectorButton extends JButton implements MouseListener {
    BlockType type;
    CenterPanel centerPanel;
    JLabel label = new JLabel();

    public SelectorButton(CenterPanel centerPanel, BlockType type) {
        this.centerPanel = centerPanel;
        this.type = type;

        setBackground(BlockManager.getColor(type));
        initializeTextField();

        addMouseListener(this);
        setFocusable(true);
    }

    private void initializeTextField() {
        label.setPreferredSize(new Dimension(100, 100));
        label.setOpaque(false);
        label.setBorder(null);
        label.setVisible(true);
        label.setFocusable(false);
        label.setText(type.name());
        add(label);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        CenterPanel.hideSelector(centerPanel);
        CenterPanel.newBlock(centerPanel, type, e.getLocationOnScreen());
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
