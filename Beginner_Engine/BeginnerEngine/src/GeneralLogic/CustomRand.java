package GeneralLogic;

import java.awt.*;

public class CustomRand {
    public static Color randColor() {
        return new Color((int) Math.floor(Math.random() * 16777215));
    }
}
