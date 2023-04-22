package model.Island.Animal;

import java.lang.Math;

    public class Bear extends Animal{
        public final int DAMAGE = 45;
        public final int MEAT = 16;

    public boolean isBearDamagingYou(boolean have_spear){
        return have_spear ? Math.random() * 100 <= 35 : Math.random() * 100 <= 70;
    }
}
