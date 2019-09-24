import javafx.scene.image.Image;
import java.util.HashMap;


public class ShopKeeper {
    HashMap<String, Potion> store;  //store inventory
    int health;
    private int money;                      //money the shopkeeper has
    Image im;                       //Hold the image
    int x;                          //x coordinate
    int y;                          //y coordinate
    ExplosionPotion exp;				//test explosion potion
    FlyPotion fly;
    IcePotion ice;
    AcidPotion acid;

    ShopKeeper(int xx, int yy){
        store = new HashMap<String, Potion>();
        money = 500;
        health = 100;
        im = new Image("/resources/shopkeepersteve.png");
        x = xx;
        y = yy;
        exp = new ExplosionPotion();
        fly = new FlyPotion();
        ice = new IcePotion();
        acid = new AcidPotion();
        store.put("Explosion", exp);
        store.put("Fly", fly);
        store.put("Ice", ice);
        store.put("Acid", acid);

    }

    public void buy(Potion pot, Dragon player){
        if(this.money >= pot.cost){
            this.money -= pot.cost;
            store.put(pot.toString(), pot);
            player.inventory.remove(pot.toString());
        }
        else{
            System.out.println("Shopkeeper does not have enough money");
        }
    }

    /*Method that checks if the player has enough money to purchase the item
    * Also checks if the store has that certain item. If it does then it removes that money from the player
    * removes the item from the store and puts it into the player's inventory*/
    public void sell(String p, Potion pot, Dragon player){
        if(player.money >= pot.cost){
            if(store.containsValue(pot)) {
                player.money -= pot.cost;
                this.store.remove(p);
                player.inventory.put(p, pot);
            }
            else{
                System.out.println("Shopkeeper does not have that item");
            }
        }
        else{
            System.out.println("You don't have enough money to buy that item"); 
        }
    }


}
