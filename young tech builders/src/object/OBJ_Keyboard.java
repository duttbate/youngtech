package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Keyboard extends SuperObject{

    public OBJ_Keyboard() {

     name = "Keyboard";
        try {
             image = ImageIO.read(getClass() .getResourceAsStream("/res/objects/Keyboard.png"));
        } catch (IOException e) {
         // Log the error
         
            }

            collision = true;
    }

    
}
