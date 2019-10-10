var cursors;
export default class Character extends Phaser.Physics.Arcade.Image {
    constructor(scene, x, y){
        super(scene, x, y);
        this.scene = scene;
        this.scene.children.add(this);
        this.scene.physics.add.existing(this);
        this.sprite = this.scene.physics.add.sprite(x, y, "dragon");

        cursors = scene.input.keyboard.createCursorKeys();
        // Create the player's walking animations from the texture atlas. These are stored in the global
                // animation manager so any sprite can access them.
                const anims = scene.anims;
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
    }

    update(){
        const speed = 175;
                const prevVelocity = this.sprite.body.velocity.clone();
                //const { keys, sprite } = this;

                // Stop any previous movement from the last frame
                this.sprite.body.setVelocity(0);
                //cursors = this.input.keyboard.createCursorKeys();
                // Horizontal movement
                if (cursors.left.isDown) {
                  this.sprite.body.setVelocityX(-speed);
                } else if (cursors.right.isDown) {
                  this.sprite.body.setVelocityX(speed);
                }

                // Vertical movement
                if (cursors.up.isDown) {
                  this.sprite.body.setVelocityY(-speed);
                } else if (cursors.down.isDown) {
                  this.sprite.body.setVelocityY(speed);
                }

                // Normalize and scale the velocity so that player can't move faster along a diagonal
                this.sprite.body.velocity.normalize().scale(speed);

             // Update the animation last and give left/right animations precedence over up/down animations
                 if (cursors.left.isDown) {
                   this.sprite.anims.play("left", true);
                 } else if (cursors.right.isDown) {
                   this.sprite.anims.play("right", true);
                 } else if (cursors.up.isDown) {
                   this.sprite.anims.play("up", true);
                 } else if (cursors.down.isDown) {
                   this.sprite.anims.play("down", true);
                 }  else {
                  this.sprite.anims.stop();

                  if (prevVelocity.x < 0)      this.sprite.anims.play("idle-left", true);
                  else if (prevVelocity.x > 0) this.sprite.anims.play("idle-right", true);
                  else if (prevVelocity.y < 0) this.sprite.anims.play("idle-up", true);
                  else if (prevVelocity.y > 0) this.sprite.anims.play("idle-down", true);
                }
    }

}