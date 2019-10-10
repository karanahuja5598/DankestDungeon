

export default class MeanMenu extends Phaser.Scene{

    constructor(){
        super("MeanMenu");
    }

    preload(){
        this.load.spritesheet('button', "../assets/images/Start-Game.png", {frameWidth: 100, frameHeight: 100})
        this.load.image('logo', '../assets/images/danklogo.png');
        this.load.image('background', '../assets/images/menu-background.jpg');
    }

    create(){

        var background = this.add.image(400,300,'background');
        background.setAlpha(0.3);

        var logo = this.add.image(400,200,'logo');
        logo.setScale(2);


        var button = this.add.image(400, 400, 'button');
        button.setInteractive();
        button.on("pointerdown", () => {this.scene.start("LevelOne");});

        //var helloButton = this.add.text(400, 400, 'Hello Phaser!', { fill: '#0f0' });
        //helloButton.setInteractive();
        //helloButton.on("pointerdown", () => {this.scene.start("LevelOne");});
        //Phaser.Scene.call(this, {key: "LevelOne"});

        // const button = this.add.text(500,500, "press me plz", {fill: '#000'});
        // button.setInteractive();
        // button.on("pointerdown", () => this.scene.start("LevelOne"));



    }


}
