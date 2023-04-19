package modell.Player.Tool;

import java.lang.Math;
public class Fishingrod extends Tool{

    public boolean isFishingRodBreaking(){
        return Math.random() * 100 <= 6;
    }
}
