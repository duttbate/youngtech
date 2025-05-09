import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Powersupply extends SuperObject{
    
    public OBJ_Powersupply() {

     name = "Mouse";
        try {
             image = ImageIO.read(getClass() .getResourceAsStream("/res/objects/mouse.png"));
        } catch (IOException e) {
             e.printStackTrace(); // Log the error
            }

            collision = true;
    }
}
