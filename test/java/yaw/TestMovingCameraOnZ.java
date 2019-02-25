package yaw;

import yaw.engine.World;
import yaw.engine.items.Item;
import yaw.engine.meshs.MeshBuilder;
import yaw.engine.meshs.Texture;

public class TestMovingCameraOnZ {
    public static void main(String[] args) throws Exception {

        World world = new World(0, 0, 700, 700);/* Create the world with its dimensions. */

        float[] f = new float[]{0.f, 0.f, 0.f};
        (new Thread(world)).start();/* Launches the thread responsible for the display and our game loop. */

        for (int i = 0; i < 5; i++) {

            Item item = world.createItem(i + "", f, 1, MeshBuilder.generateBlock(1, 1, 1));
            item.translate(i,i,i);

            if (i % 3 == 0)
                item.getAppearance().getMaterial().setTexture(new Texture("/ressources/grassblock.png"));
            else if (i % 3 == 1)
                item.getAppearance().getMaterial().setTexture(new Texture("/ressources/sand.png"));
            else
                item.getAppearance().getMaterial().setTexture(new Texture("/ressources/diamond.png"));
        }

        world.getCamera().translate(-15, 15, -10); // placing camera to have a side vue of the world
        world.getCamera().rotate(-45,-90,0); //rotate the camera to see the center of the world

        //loop for the camera's move
        float z = -10;
        boolean inversingmove = false;
        while (true) {
            if(inversingmove){
                z+=0.1f;
                world.getCamera().translate(0,0,0.1f);
                if(z>=10){
                    inversingmove = false;
                }
            }else{
                world.getCamera().translate(0,0,-0.1f);
                z-=0.1f;
                if(z<=-10){
                    inversingmove = true;
                }
            }

            Thread.sleep(20); /* Allows to see the block (cube) move at constant rate. */
        }
    }

}