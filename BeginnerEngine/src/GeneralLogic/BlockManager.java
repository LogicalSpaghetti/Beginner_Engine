package GeneralLogic;

import java.awt.*;
import java.util.ArrayList;
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

    public static Color getColor(String category) {
        return switch (category) {
            case "Motion" -> Color.blue;
            case "Looks" -> Color.magenta;
            case "Control" -> Color.yellow;
            default -> Color.white;
        };
    }

    // Other methods to interact with the blocks list
}
