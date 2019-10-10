

export default class Shopkeeper extends Phaser.Physics.Arcade.Image{
    constructor(scene, x, y) {
        super(scene, x, y);
        this.scene = scene;
        this.sprite = this.scene.physics.add.sprite(x, y, "dragon");
        //var event = []; // create empty array
        //this.collides = true;
                this.scene.children.add(this);

        this.scene.physics.add.existing(this);

//        // create event objects and store them in the array
//        event[0] = { text: "Hello, how are you?",
//                     options: [    { response: "Bad", next: 1 },
//                                   { response: "Good", next: 2 }
//                              ]
//                   };
//        event[1] = { text: "Why, what's wrong?",
//                     options: [    { response: "My dog ran away", next: 3},
//                                   { response: "I broke up with my girlfriend", next: 4}
//                              ]
//                   };
    }

//    talk(speech){
//
//    }
}