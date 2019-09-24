import javafx.scene.image.Image;

import java.util.HashMap;

public class Dragon {
    int x; //x coordinate
    int y; //y coordinate
    int money;                          //amount of money the player has
    Image im;
    HashMap<String, Potion> inventory;  //player inventory for items
    ExplosionPotion exp;				//test explosion potion
    FlyPotion fly;                      //test fly potion
    AcidPotion acid;
    IcePotion ice;

    Dragon(int xx, int yy) {
        x = xx;
        y = yy;
        im = new Image("/resources/lizard_m_idle_anim_f1.png");
        inventory = new HashMap<String,Potion>();
        money = 100;
        exp = new ExplosionPotion();
        fly = new FlyPotion();
        acid = new AcidPotion();
        ice = new IcePotion();
        inventory.put("Explosion", exp);
        inventory.put("Fly", fly);
        inventory.put("Acid", acid);
        inventory.put("Ice", ice);
    }

    //can change to return the item
    //Method that uses an item if it exists in the player's inventory
    public boolean Use(String p){
        if(inventory.containsKey(p) == true) {
            inventory.remove(p);
            return true;
        }
        else {
            System.out.print("Player does not have the item");
        }
        return false;
    }

    //Method that adds an item to the players inventory
    public void add(String key, Potion pot) {
        this.inventory.put(key, pot);
    }
}
