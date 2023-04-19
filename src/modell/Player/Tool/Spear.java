package modell.Player.Tool;

import java.lang.Math;
public class Spear extends Tool{

    public boolean isSpearBreaking(){
        return Math.random() * 100 <= 10;
    }
}
