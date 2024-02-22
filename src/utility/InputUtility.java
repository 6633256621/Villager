package utility;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class InputUtility {
    //array of all button which is interacted
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

    public static boolean isKeyPressed(KeyCode keycode) {
        return keyPressed.contains(keycode);
    }

    public static void setKeyPressed(KeyCode keycode,boolean pressed) {
        //if pressed add to array
        if(pressed){
            if(!keyPressed.contains(keycode)){
                keyPressed.add(keycode);
            }
        }
        //if release then remove from array
        else{
            keyPressed.remove(keycode);
        }
    }

    public static boolean isOnlyOneKeyPressed() {
        return keyPressed.size()==1;
    }
}

