import java.util.Vector;

public class Potion {
    public double cost = 0;
    int damage;
    int radius;
    int toss;

    public void useToss(Vector<Vector<Tile>> board, Dragon player, Character key) {

    }

    public void useFly(Vector<Vector<Tile>> board, Dragon player, Character key){

    }
}

class ExplosionPotion extends Potion {
    // int damage;
    // int radius;

    ExplosionPotion() {
        cost = 10;
        damage = 10;
        radius = 3;
        toss = 2;
    }

    //method to throw the potion to a tile at a certain range.
    //TODO: make the range based off the potion type
    public void useToss(Vector<Vector<Tile>> board, Dragon player, Character key) {
        if(key == 'd') {
            board.get(player.x).get(player.y+2).temp = 'P';
        }
        else if(key == 'a') {
            board.get(player.x).get(player.y-2).temp = 'P';
        }
        else if(key == 'w') {
            board.get(player.x-2).get(player.y).temp = 'P';
        }
        else {
            board.get(player.x+2).get(player.y).temp = 'P';
        }
    }

}

class FirePotion extends Potion {

    FirePotion(){
        cost = 5;
        damage = 5;
        radius = 2;
        toss = 4;
    }

    public void useToss(Vector<Vector<Tile>> board, Dragon player, Character key) {
        if(key == 'd') {
            board.get(player.x).get(player.y + this.toss).temp = 'P';
        }
        else if(key == 'a') {
            board.get(player.x).get(player.y - this.toss).temp = 'P';
        }
        else if(key == 'w') {
            board.get(player.x - this.toss).get(player.y).temp = 'P';
        }
        else {
            board.get(player.x + this.toss).get(player.y).temp = 'P';
        }
    }
}

class IcePotion extends Potion {

    IcePotion(){
        cost = 5;
        damage = 5;
        radius = 2;
        toss = 4;
    }

    public void useToss(Vector<Vector<Tile>> board, Dragon player, Character key) {
        if(key == 'd') {
            board.get(player.x).get(player.y + this.toss).temp = 'P';
        }
        else if(key == 'a') {
            board.get(player.x).get(player.y - this.toss).temp = 'P';
        }
        else if(key == 'w') {
            board.get(player.x - this.toss).get(player.y).temp = 'P';
        }
        else {
            board.get(player.x + this.toss).get(player.y).temp = 'P';
        }
    }
}

class AcidPotion extends Potion {

    AcidPotion(){
        cost = 15;
        damage = 20;
        radius = 1;
        toss = 2;
    }

    public void useToss(Vector<Vector<Tile>> board, Dragon player, Character key) {
        if(key == 'd') {
            board.get(player.x).get(player.y + this.toss).temp = 'P';
        }
        else if(key == 'a') {
            board.get(player.x).get(player.y - this.toss).temp = 'P';
        }
        else if(key == 'w') {
            board.get(player.x - this.toss).get(player.y).temp = 'P';
        }
        else {
            board.get(player.x + this.toss).get(player.y).temp = 'P';
        }
    }
}

class FlyPotion extends Potion {
    int travel;

    FlyPotion(){
        cost = 20;
        damage = 0;
        travel = 4;
    }

    public void useFly(Vector<Vector<Tile>> board, Dragon player, Character key){
        board.get(player.x).get(player.y).temp = ' ';
        if(key == 'd') {
            board.get(player.x).get(player.y + this.travel).temp = 'D';
            player.y += this.travel;
        }
        else if(key == 'a') {
            board.get(player.x).get(player.y - this.travel).temp = 'D';
            player.y -= this.travel;
        }
        else if(key == 'w') {
            board.get(player.x - this.travel).get(player.y).temp = 'D';
            player.x -= this.travel;
        }
        else {
            board.get(player.x + this.travel).get(player.y).temp = 'D';
            player.x += this.travel;
        }
    }
}
