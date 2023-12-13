package GeneralLogic;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BlockManager {
    public static final List<BlockType> blocks = Arrays.asList(
            new BlockType("BlockType 1", "Motion"),
            new BlockType("BlockType 2", "Motion"),
            new BlockType("BlockType 3", "Looks"),
            new BlockType("BlockType 4", "Looks"),
            new BlockType("BlockType 5", "Control"),
            new BlockType("BlockType 6", "Control")
    );

    public static Color getColor(BlockType type) {
        return switch (type.category()) {
            case "Motion" -> Color.blue;
            case "Looks" -> Color.magenta;
            case "Control" -> Color.yellow;
            default -> Color.white;
        };
    }

    public static Color getLighterColor(BlockType type) {
        return switch (type.category()) {
            case "Motion" -> new Color(50, 100, 255);
            case "Looks" -> new Color(255, 100, 255);
            case "Control" -> new Color(255, 255, 100);
            default -> Color.white;
        };
    }
}
