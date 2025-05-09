package main;

import object.OBJ_Keyboard;
import object.OBJ_Mouse;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Keyboard();
        gp.obj[0].worldX = 1 * gp.tileSize;
        gp.obj[0].worldY = 1 * gp.tileSize;

        gp.obj[1] = new OBJ_Mouse();
        gp.obj[1].worldX = 2 * gp.tileSize;
        gp.obj[1].worldY = 2 * gp.tileSize;

        gp.obj[2] = new OBJ_Powersupply();
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 3 * gp.tileSize; 

        gp.obj[3] = new OBJ_Keyboard();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 4 * gp.tileSize;
        
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                System.out.println("Object " + i + " at (" + gp.obj[i].worldX + ", " + gp.obj[i].worldY + ")");
                System.out.println("Object " + i + " name: " + gp.obj[i].name);
            } else {
                System.out.println("Object " + i + " is null");
            }
        }
    }
}