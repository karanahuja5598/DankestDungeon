import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Level {
    Vector<Vector<Tile>> board;
    Dragon player;
    ShopKeeper shop;
    int startSize;
    int pixSize;
    Vector<Vector<StackPane>> gridIm = new Vector<Vector<StackPane>>();

    Level() {
        //generate a basic, hollow 10x10 level
        board = new Vector<Vector<Tile>>();
        pixSize = 64;
        startSize = 10;
        for(int i = 0; i < startSize; i++) {
            board.add(new Vector<Tile>());
            for(int j = 0; j < startSize; j++) {
                board.get(i).add(new Tile());
            }
        }

        for(int i = 0; i < startSize; i++) {
            board.get(0).get(i).setWall();
            board.get(startSize-1).get(i).setWall();
            board.get(i).get(0).setWall();
            board.get(i).get(startSize-1).setWall();
        }

        //Testing purposes
        board.get(1).get(3).health = 10;
        board.get(1).get(3).type = "metal";

        player = new Dragon(1,1);

        board.get(player.x).get(player.y).temp = 'D';
        board.get(player.x).get(player.y).isOn = true;

        shop = new ShopKeeper(7,1);
        board.get(shop.x).get(shop.y).isOn = true;
        board.get(shop.x).get(shop.y).type = "Shop";
    }

    void createPlayerImage() {
        ImageView playImV = new ImageView(player.im);
        playImV.setPreserveRatio(true);
        playImV.setFitHeight(pixSize*3/4);
        playImV.setFitWidth(pixSize*3/4);
        gridIm.get(player.x).get(player.y).getChildren().add(playImV);
    }

    void removePlayerImage() {
        gridIm.get(player.x).get(player.y).getChildren().remove(1);
    }

    //method for creating shopkeeper image
    void createShopImage(){
        ImageView shopImV = new ImageView(shop.im);
        shopImV.setPreserveRatio(true);
        shopImV.setFitHeight(pixSize*3/4);
        shopImV.setFitWidth(pixSize*3/4);
        gridIm.get(shop.x).get(shop.y).getChildren().add(shopImV);
    }

    boolean move(char key, BorderPane border) {
        switch (key) {
            //move up
            case 'w':
                if(board.get(player.x).get(player.y - 1).health == 0 && npcAhead('w', border) == true) {
                    board.get(player.x).get(player.y).isOn = false;
                    removePlayerImage();
                    board.get(player.x).get(player.y - 1).isOn = true;
                    player.y--;	//update the players y coordinate
                    createPlayerImage();
                    return true;
                }
                break;
            //move left
            case 'a':
                if(board.get(player.x - 1).get(player.y).health == 0 && npcAhead('a', border) == true) {
                    board.get(player.x).get(player.y).isOn = false;
                    removePlayerImage();
                    board.get(player.x - 1).get(player.y).isOn = true;
                    player.x--;	//update the players y coordinate
                    createPlayerImage();
                    return true;
                }
                break;
            //move down
            case 's':
                if(board.get(player.x).get(player.y + 1).health == 0 && npcAhead('s', border) == true) {
                    board.get(player.x).get(player.y).isOn = false;
                    board.get(player.x).get(player.y).temp = ' ';
                    removePlayerImage();
                    board.get(player.x).get(player.y + 1).isOn = true;
                    board.get(player.x).get(player.y + 1).temp = 'D';
                    player.y++;	//update the players y coordinate
                    createPlayerImage();
                    return true;
                }
                break;
            //move right
            case 'd':
                if(board.get(player.x + 1).get(player.y).health == 0 && npcAhead('d', border) == true) {
                    board.get(player.x).get(player.y).isOn = false;
                    removePlayerImage();
                    board.get(player.x + 1).get(player.y).isOn = true;
                    player.x++;	//update the players y coordinate
                    createPlayerImage();
                    return true;
                }
                break;

            //Displays whats in the inventory to use for throw functionality
            case 't':
                Iterator invIterator = player.inventory.entrySet().iterator();

                while(invIterator.hasNext()) {
                    Map.Entry element = (Map.Entry) invIterator.next();
                    System.out.print(element.getKey().toString()+ "\n");
                }
                Potion p = player.inventory.get("Explosion");
                Potion f = player.inventory.get("Fly");

                //asks the user for another input for the direction to throw the item
                System.out.print("What direction?");
                Scanner in = new Scanner(System.in);
                key = in.next().charAt(0);

                //p.useToss(board, player, key); //calls the toss method

                //f.useFly(board, player, key); // test for fly potion

                break;

            /*Displays the player inventory By pressing "i"
            * if the inventory is open and the player presses "i" again it closes it*/
            case 'i':
                if(border.getBottom() == null) {
                    Iterator playerIterator = player.inventory.entrySet().iterator();

                    ListView<Button> list = new ListView();
                    ObservableList<Button> buttons = FXCollections.<Button>observableArrayList();
                    list.setPrefHeight(100);
                    list.setPrefWidth(100);
                    Button b;

                    while (playerIterator.hasNext()) {
                        Map.Entry element = (Map.Entry) playerIterator.next();
                        b = new Button(element.getKey().toString());
                        buttons.add(b);
                    }
                    list.setItems(buttons);
                    border.setBottom(list);
                }
                else{
                    border.setBottom(null);
                }

        }
        return false;
    }

    //method used to destroy walls, return true if the player has the correct item available to destroy the wall
    boolean destroy(char move) {
        if(move == 'd') {
            if(board.get(player.x).get(player.y + 1).type == "metal") {
                 if(player.inventory.containsKey("Explosion") == true) {
                    player.Use("Explosion");
                    return true;
                }
                else {
                    System.out.print("Player does not have the correct item in their inventory");
                    return false;
                }
            }

        }
        return false;
    }

    void printLevel() {
        for(Vector<Tile> v : board) {
            for(Tile t : v) {
                System.out.print(t.temp);
            }
            System.out.print('\n');
        }
    }

    /*method that checks if an npc is ahead
      If npc has a health of more than 0 the player cant move ahead
      If npc is dead then the player can move ahead
     */
    boolean npcAhead(Character dir, BorderPane border){
        ListView<Button> list = new ListView();
        ObservableList<Button> buttons = FXCollections.<Button>observableArrayList();
        list.setPrefHeight(100);
        list.setPrefWidth(100);
        if(dir == 'd') {
            if (board.get(player.x + 1).get(player.y).isOn == true) {
                if(board.get(player.x + 1).get(player.y).type == "Shop"){
                    Button b;

                    Iterator invIterator = shop.store.entrySet().iterator();

                    while(invIterator.hasNext()) {
                        Map.Entry element = (Map.Entry) invIterator.next();
                        if(shop.store.get(element.getKey().toString()) != null) {
                            b = new Button(element.getKey().toString());

                            b.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    shop.sell(element.getKey().toString(), shop.store.get(element.getKey().toString()), player);
                                }
                            });
                            buttons.add(b);
                        }
                    }
                    list.setItems(buttons);
                    border.setBottom(list);
                }
                return false;
            }else{
                border.setBottom(null);
            }
        }
        else if(dir == 'a') {
            if (board.get(player.x - 1).get(player.y).isOn == true) {
                if(board.get(player.x - 1).get(player.y).type == "Shop"){
                    Label la = new Label("This is Store");
                    Button b;

                    Iterator invIterator = shop.store.entrySet().iterator();

                    while(invIterator.hasNext()) {
                        Map.Entry element = (Map.Entry) invIterator.next();
                        if(shop.store.get(element.getKey().toString()) != null) {
                            b = new Button(element.getKey().toString());

                            b.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    shop.sell(element.getKey().toString(), shop.store.get(element.getKey().toString()), player);
                                }
                            });
                            buttons.add(b);
                        }
                    }
                    list.setItems(buttons);
                    border.setBottom(list);
                }
                return false;
            }
            else{
                border.setBottom(null);
            }
        }
        else if(dir == 's') {
            if (board.get(player.x).get(player.y + 1).isOn == true) {
                return false;
            }
        }
        else if(dir == 'w') {
            if (board.get(player.x).get(player.y - 1).isOn == true) {
                return false;
            }
        }
        else {
            return true;
        }
        return true;
    }
}
