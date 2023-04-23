package model.Island;

import model.Island.Animal.Animal;

import java.util.Random;

public class Island {
    // instantiation or objects
    private Animal animal;
    private Material material;


    // constructor for random generate sources:
    // generated sources looks like:
    /*1) bears number [min = 4, max = 20]
    * 2) rabbits number [min = 10, max = 30]
    * 3) tree number [min = 15, max = 40]
    * 4) fruit tree number [ min = 5, max = 20]
    * 5) waters number [min = 0, max = 3]
    * 6) clays number[min = 5, max = 20]*/
    public Island(){
        setAnimal(new Animal());
        setMaterial(new Material());
        Random rand = new Random();
        // set the real number of sources
        animal.setBear_number(rand.nextInt(17) + 4);
        animal.setRabbit_number(rand.nextInt(21) + 10);
        material.setTree_number(rand.nextInt(26) + 15);
        material.setFruit_tree_number(rand.nextInt(16) + 5);
        material.setWater_number(rand.nextInt(4));
        material.setClay_number(rand.nextInt(16) + 5);

        // set the number of sources that the player know
        // animals:
        animal.setKnow_bear(0); // 0
        animal.setKnow_rabbit(1); // 1

        // materials:
        material.setKnow_tree_number(4); // 4
        material.setKnow_fruit_tree_number(1); // 1
        material.setKnow_water_number(0); // 0
    }

    // getters and setters
    // animal:
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    // material:
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
