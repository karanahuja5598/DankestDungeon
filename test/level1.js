var player;
var cursors;
var isOverlapping;
var collideCallback;
var shop;
var button;

import Shopkeeper from "./shopkeeper.js"
import Character from "./character.js"




export default class LevelOne extends Phaser.Scene {

    constructor(){
        super("LevelOne");
    }

    preload() {
        this.load.image("tiles", "../assets/tilesets/dungeonTileset.png");
        this.load.tilemapTiledJSON("map", "../assets/tilemaps/dungtest.json");
        this.load.spritesheet("dragon", "../assets/images/FlameTail v2.png", {frameWidth: 16, frameHeight: 16});
//        this.load.scenePlugin({
//                    key: 'rexuiplugin',
//                    url: 'https://rawgithubusercontent.com/rexrainbow/phaser3-rex-notes/master/plugins/dist/rexuiplugin.min.js',
//                    sceneKey: 'rexUI'
//                });
        this.load.scenePlugin("rexUI", "../phaser3-rex-notes-master/phaser3-rex-notes-master/plugins/dist/rexuiplugin.min.js");
        this.load.image("heart", "../assets/images/heart.png");
    }

    create() {

        const map = this.make.tilemap({ key: "map" });

        const tileset = map.addTilesetImage("dungeonTileset", "tiles");
        this.floor = map.createStaticLayer("Floor", tileset, 0, 0);
        this.walls = map.createStaticLayer("Wall", tileset, 0, 0);

        this.walls.setCollisionByProperty({ collides: true });

        // Object layers in Tiled let you embed extra info into a map - like a spawn point or custom
        // collision shapes. In the tmx file, there's an object layer with a point named "Spawn Point"
        const spawnPoint = map.findObject("Objects", obj => obj.name === "Spawn Point");

        // Create a sprite with physics enabled via the physics system. The image used for the sprite has
        // a bit of whitespace, so I'm using setSize & setOffset to control the size of the player's body.
        this.player = new Character(this, spawnPoint.x, spawnPoint.y);
        //shopkeeper
        this.shop = new Shopkeeper(this, 100, 100);

        // Watch the player and worldLayer for collisions, for the duration of the scene:
        this.physics.world.addCollider(this.player.sprite, this.walls);

        this.player.sprite.setCollideWorldBounds(true);
        this.shop.sprite.setCollideWorldBounds(true);




        //add interaction for player and shop
        this.physics.add.overlap(this.player.sprite, this.shop.sprite, check, null, this);

        function check(player, shop){
            this.input.keyboard.once("keydown_A", event => {
                    this.add.text(200, 200, 'Buy and Sell Items here', {
                                  font: "18px monospace",
                                  fill: "#000000",
                                  padding: { x: 20, y: 10 },
                                  backgroundColor: "#ffffff"
                             }).setScrollFactor(0).setDepth(30);
            });
        }


        const camera = this.cameras.main;
        camera.startFollow(this.player);
        camera.setBounds(0, 0, map.widthInPixels, map.heightInPixels);


        // Help text that has a "fixed" position on the screen
        this.add.text(16, 16, 'Arrow keys to move\nPress "D" to show hitboxes', {
              font: "18px monospace",
              fill: "#000000",
              padding: { x: 20, y: 10 },
              backgroundColor: "#ffffff"
         }).setScrollFactor(0).setDepth(30);

        // Debug graphics
        this.input.keyboard.once("keydown_D", event => {
        // Turn on physics debugging to show player's hitbox
            this.physics.world.createDebugGraphic();
            // Create worldLayer collision graphic above the player, but below the help text
            const graphics = this.add.graphics().setAlpha(0.75).setDepth(20);
            this.walls.renderDebug(graphics, {
                tileColor: null, // Color of non-colliding tiles
                collidingTileColor: new Phaser.Display.Color(243, 134, 48, 255), // Color of colliding tiles
                faceColor: new Phaser.Display.Color(40, 39, 37, 255) // Color of colliding face edges
            });
        });

        //add player health bar
        var health = [];
        var xcoord = 30;
        var ycoord = 510;
        for(var i = 0; i < 10; i++) {
          health.push(this.add.image(xcoord + i*32,ycoord,'heart'));
          //health[i].setScale(0.5);
        }
        this.add.text(xcoord, ycoord + 32, "Health");
    }

    update(time, delta) {
        this.player.update();

   }
}
