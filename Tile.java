import javafx.scene.image.Image;

//used to represent dungeon floor tiles
public class Tile {
    /* health is a representation of the durability of a tile
     a value of -1 means it is indestructible/impassible
     a value of  0 means it can be walked through
     a value of anything > 0 represents
        how much damage the obstacle must take to be destroyed
     */
    int health;
    /* the type represents the type of the tile
    type wall  means it is just a wall
    type floor means it is just the floor
    more types to be added later
     */
    String type;
    Image im;
    char temp; //temporary used to represent the tile when it is printed
    boolean isOn = false;
    boolean isWall = false;

    Tile() {
        health = 0;
        type = "floor";
        temp = ' ';
        im = new Image("/resources/floor_1.png");
    }

    void setFloor() {
        health = 0;
        type = "floor";
        temp = ' ';
        im = new Image("/resources/floor_1.png");
    }

    void setWall() {
        health = -1;
        type = "wall";
        temp = '*';
        im = new Image("/resources/wall_mid.png");
    }

    void setIsWall(boolean set){
        isWall = set;
    }

    boolean getIsWall(){
        return isWall;
    }

    void setIsOn(boolean set){
        isOn = set;
    }

    Boolean getIsOn(){
        return isOn;
    }

    void checkIfWall(){
        if(type == "wall"){
            setIsWall(true);
        }else{
            setIsWall(false);
        }
    }
}

class Wall extends Tile{
    
    Wall(){
        health = 5;
        type = "wall";
        temp = 'w';
    }

}

class WoodWall extends Tile{
    
    WoodWall(){
        health = 7;
        type = "wood";
        temp = 'd';
    }
}


class MetalWall extends Tile{
    
    MetalWall(){
        health = 10;
        type = "metal";
        temp = 'm';
    }

}


class ReinforcedWall extends Tile{

    ReinforcedWall(){
        health = 15;
        type = "reinforced";
        temp = 'r';
    }

}

class DiamondWall extends Tile{

    DiamondWall(){
        health = 20;
        type = "diamond";
        temp = 'd';
    }
}


