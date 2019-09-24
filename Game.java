import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Vector;

public class Game extends Application {
    public static final void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        Level l = new Level();
        GridPane grid = new GridPane();
        BorderPane border = new BorderPane();
        ScrollPane s1 = new ScrollPane();

        //image pixel size
        int size = l.pixSize;

        initBoard(grid,l, size);
        border.setCenter(grid);
        border.setBottom(s1);

        Scene s = new Scene(border);

        primaryStage.setScene(s);

        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                l.move(event.getText().charAt(0), border);
            }
        });

        primaryStage.setTitle("The Dankest Dungeon");
        primaryStage.show();
    }

    void initBoard(GridPane grid, Level l, int size) {

        l.gridIm = new Vector<Vector<StackPane>>();
        for(int i = 0; i < l.board.size(); i++) {
            l.gridIm.add(new Vector<StackPane>());
            for(int j = 0; j < l.board.get(i).size(); j++) {
                ImageView imV = new ImageView(l.board.get(i).get(j).im);
                imV.setPreserveRatio(true);
                imV.setFitHeight(size);
                imV.setFitWidth(size);
                StackPane stack = new StackPane();
                stack.getChildren().add(imV);
                l.gridIm.get(i).add(stack);
                grid.add(l.gridIm.get(i).get(j),i,j);
            }
        }

        ImageView playImV = new ImageView(l.player.im);
        playImV.setPreserveRatio(true);
        playImV.setFitHeight(size*3/4);
        playImV.setFitWidth(size*3/4);

        l.gridIm.get(l.player.x).get(l.player.y).getChildren().add(playImV);

        ImageView shopImV = new ImageView(l.shop.im);
        shopImV.setPreserveRatio(true);
        shopImV.setFitHeight(size*3/4);
        shopImV.setFitWidth(size*3/4);

        l.gridIm.get(l.shop.x).get(l.shop.y).getChildren().add(shopImV);
    }
}

