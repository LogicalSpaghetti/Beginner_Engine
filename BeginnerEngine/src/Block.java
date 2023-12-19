import GeneralLogic.BlockManager;
import GeneralLogic.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// a draggable segment of code that links with other blocks
public class Block extends JPanel implements MouseListener, MouseMotionListener {

    CenterPanel centerPanel;

    Point initialLocation;
    Point initialClick;

    BlockType type;

    JPanel topBar = new JPanel();
    InputPanel inputPanel = new InputPanel();
    OutputPanel outputPanel = new OutputPanel();

    public Block(BlockType type, CenterPanel centerPanel) {
        this.type = type;
        this.centerPanel = centerPanel;

        setLayout(new BorderLayout());

        initializeSubPanels();
        sizeBlock();

        setBackground(BlockManager.getColor(type));
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    private void initializeSubPanels() {
        topBar.setBackground(Color.LIGHT_GRAY);
        inputPanel.setBackground(Color.CYAN);
        outputPanel.setBackground(Color.PINK);

        sizeTopBar();
        sizeInput();
        sizeOutput();

        add(topBar, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(outputPanel, BorderLayout.EAST);
    }

    private void sizeTopBar() {
        topBar.setPreferredSize(new Dimension(12, 20));
        // width = nameTextField.width
    }

    private void sizeInput() {
        inputPanel.setPreferredSize(new Dimension(40,200));
        // height = numInputs * 20
    }
    private void sizeOutput() {
        outputPanel.setPreferredSize(new Dimension(60,100));
        // height = numOutputs * 20
    }

    private void sizeBlock() {
        Dimension topDim = topBar.getPreferredSize();
        Dimension inDim = inputPanel.getPreferredSize();
        Dimension outDim = outputPanel.getPreferredSize();

        int x = Math.max(topDim.width, (inDim.width + outDim.width));
        int y = (topDim.height + Math.max(inDim.height, outDim.height));
        setSize(x, y);
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


    private static class InputPanel extends JPanel {
        InputPanel() {

        }

        private static class InputNode extends JPanel {

        }
    }

    private static class OutputPanel extends JPanel {
        OutputPanel() {

        }

        private static class OutputNode extends JPanel {

            JTextField textField = new JTextField();

            OutputNode(String outputType) {
                textField.setText(outputType);

                int width = SwingUtilities.computeStringWidth(textField.getFontMetrics(textField.getFont()), textField.getText()) + 20; // Adding padding

                Dimension preferredSize = getPreferredSize();
                preferredSize.width = width;
                setPreferredSize(preferredSize);
            }
        }
    }
}
