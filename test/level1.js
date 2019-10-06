var player;
var cursors;

export default class LevelOne extends Phaser.Scene {

    preload() {
        this.load.image("tiles", "../assets/tilesets/dungeonTileset.png");
        this.load.tilemapTiledJSON("map", "../assets/tilemaps/dungtest.json");
        //this.load.atlas("atlas", "../assets/atlas/atlas.png", "../assets/atlas/atlas.json");
        this.load.spritesheet("dragon", "../assets/images/FlameTail v2.png", {frameWidth: 16, frameHeight: 16});
    }

    create() {
        const map = this.make.tilemap({ key: "map" });

        const tileset = map.addTilesetImage("dungeonTileset", "tiles");
        const floor = map.createStaticLayer("Floor", tileset, 0, 0);
        const walls = map.createStaticLayer("Wall", tileset, 0, 0);

        walls.setCollisionByProperty({ collides: true });

        // Object layers in Tiled let you embed extra info into a map - like a spawn point or custom
        // collision shapes. In the tmx file, there's an object layer with a point named "Spawn Point"
        const spawnPoint = map.findObject("Objects", obj => obj.name === "Spawn Point");

        // Create a sprite with physics enabled via the physics system. The image used for the sprite has
        // a bit of whitespace, so I'm using setSize & setOffset to control the size of the player's body.
        player = this.physics.add.sprite(spawnPoint.x, spawnPoint.y, "dragon");


        // Watch the player and worldLayer for collisions, for the duration of the scene:
        this.physics.add.collider(player, walls);

        // Create the player's walking animations from the texture atlas. These are stored in the global
        // animation manager so any sprite can access them.
        const anims = this.anims;
        anims.create({
            key: 'left',
            frames: anims.generateFrameNumbers("dragon", {start: 8, end: 11}),
            repeat: -1
        });
        anims.create({
            key: 'right',
            frames: anims.generateFrameNumbers("dragon", {start: 12, end: 15}),
            repeat: -1
        });
        anims.create({
            key: 'up',
            frames: anims.generateFrameNumbers("dragon", {start: 4, end: 7}),
            repeat: -1
        });
        anims.create({
            key: 'down',
            frames: anims.generateFrameNumbers("dragon", {start: 0, end: 3}),
            repeat: -1
        });
        anims.create({
            key: 'idle-left',
            frames: anims.generateFrameNumbers("dragon", {start: 11, end: 11}),
            repeat: -1
        });
        anims.create({
            key: 'idle-right',
            frames: anims.generateFrameNumbers("dragon", {start: 15, end: 15}),
            repeat: -1
        });
        anims.create({
            key: 'idle-up',
            frames: anims.generateFrameNumbers("dragon", {start: 5, end: 5}),
            repeat: -1
        });
        anims.create({
            key: 'idle-down',
            frames: anims.generateFrameNumbers("dragon", {start: 0, end: 0}),
            repeat: -1
        });

        /*
        anims.create({
            key: "misa-left-walk",
            frames: anims.generateFrameNames("atlas", {
                prefix: "misa-left-walk.",
                start: 0,
                end: 3,
                zeroPad: 3
            }),
            frameRate: 10,
            repeat: -1
        });
        anims.create({
            key: "misa-right-walk",
            frames: anims.generateFrameNames("atlas", {
                prefix: "misa-right-walk.",
                start: 0,
                end: 3,
                zeroPad: 3
            }),
            frameRate: 10,
            repeat: -1
        });
        anims.create({
            key: "misa-front-walk",
            frames: anims.generateFrameNames("atlas", {
                prefix: "misa-front-walk.",
                start: 0,
                end: 3,
                zeroPad: 3
             }),
             frameRate: 10,
             repeat: -1
        });
        anims.create({
            key: "misa-back-walk",
            frames: anims.generateFrameNames("atlas", {
                prefix: "misa-back-walk.",
                start: 0,
                end: 3,
                zeroPad: 3
            }),
            frameRate: 10,
            repeat: -1
        });*/


        const camera = this.cameras.main;
        camera.startFollow(player);
        camera.setBounds(0, 0, map.widthInPixels, map.heightInPixels);

        cursors = this.input.keyboard.createCursorKeys();

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
            walls.renderDebug(graphics, {
                tileColor: null, // Color of non-colliding tiles
                collidingTileColor: new Phaser.Display.Color(243, 134, 48, 255), // Color of colliding tiles
                faceColor: new Phaser.Display.Color(40, 39, 37, 255) // Color of colliding face edges
            });
        });

    }

    update(time, delta) {
        const speed = 175;
        const prevVelocity = player.body.velocity.clone();

        // Stop any previous movement from the last frame
        player.body.setVelocity(0);

        // Horizontal movement
        if (cursors.left.isDown) {
          player.body.setVelocityX(-speed);
        } else if (cursors.right.isDown) {
          player.body.setVelocityX(speed);
        }

        // Vertical movement
        if (cursors.up.isDown) {
          player.body.setVelocityY(-speed);
        } else if (cursors.down.isDown) {
          player.body.setVelocityY(speed);
        }

        // Normalize and scale the velocity so that player can't move faster along a diagonal
        player.body.velocity.normalize().scale(speed);

     // Update the animation last and give left/right animations precedence over up/down animations
         if (cursors.left.isDown) {
           player.anims.play("left", true);
         } else if (cursors.right.isDown) {
           player.anims.play("right", true);
         } else if (cursors.up.isDown) {
           player.anims.play("up", true);
         } else if (cursors.down.isDown) {
           player.anims.play("down", true);
         }  else {
          player.anims.stop();

          if (prevVelocity.x < 0)      player.anims.play("idle-left", true);
          else if (prevVelocity.x > 0) player.anims.play("idle-right", true);
          else if (prevVelocity.y < 0) player.anims.play("idle-up", true);
          else if (prevVelocity.y > 0) player.anims.play("idle-down", true);
        }
   }
}