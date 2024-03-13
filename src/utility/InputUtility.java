package utility;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class InputUtility {
    private static boolean isClicked = false;
    //array of all button which is interacted
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

    public static boolean isKeyPressed(KeyCode keycode) {
        return keyPressed.contains(keycode);
    }

    public static void setKeyPressed(KeyCode keycode, boolean pressed) {
        //if pressed add to array
        if (!keycode.equals(KeyCode.R)&&!keycode.equals(KeyCode.P)){
            if (pressed) {
                //normal
                if (!keyPressed.contains(keycode)) {
                    keyPressed.add(keycode);
                }
            }
            //if release then remove from array
            else {
                keyPressed.remove(keycode);
            }
        }
    }

    public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
        if (keycode.equals(KeyCode.R)||keycode.equals(KeyCode.P)){
            if (pressed) {

                if (!keyPressed.contains(keycode) && !isClicked) {
                    keyPressed.add(keycode);
                    return;
                } else if (keyPressed.contains(keycode) && !isClicked) {
                    return;
                } else if (keyPressed.contains(keycode) && isClicked) {
                    keyPressed.remove(keycode);
                    return;
                }

                //normal
                if (!keyPressed.contains(keycode)) {
                    keyPressed.add(keycode);
                }
            }
            //if release then remove from array
            else {
                isClicked = true;
                return;
            }
        }
    }

    public static boolean isOnlyOneKeyPressed() {
        return keyPressed.size() == 1;
    }
    public static boolean isOnlyTwoKeyPressed() {
        return keyPressed.size() == 2;
    }

}


