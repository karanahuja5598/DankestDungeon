import LevelOne from "./level1.js";

const config = {
  type: Phaser.AUTO,
  width: 800,
  height: 600,
  backgroundColor: "#000",
  parent: "game-container",
  pixelArt: true,
  scene: LevelOne,
  physics: {
      default: "arcade",
      arcade: {
        gravity: { y: 0 }
      }
    }
};

const game = new Phaser.Game(config);