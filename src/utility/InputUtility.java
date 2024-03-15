package utility;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class InputUtility {
    private static boolean isClicked = false;
    private static boolean keyPressHandled = false;
    private static boolean keyTwo = false;
    private static ArrayList<KeyCode> lastKeyPressed = new ArrayList<>();
    //array of all button which is interacted
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
    private static int i =0;
    public static boolean isKeyPressed(KeyCode keycode) {
        if(keycode.equals(KeyCode.RIGHT)&&keyPressed.contains(keycode)) {
        }
        return keyPressed.contains(keycode);
    }

    public static void setKeyPressed(KeyCode keycode, boolean pressed) {
        //if pressed add to array
        if (!keycode.equals(KeyCode.R)&&!keycode.equals(KeyCode.P)&&
                !(keycode.equals(KeyCode.UP)||keycode.equals(KeyCode.DOWN)||keycode.equals(KeyCode.LEFT)||keycode.equals(KeyCode.RIGHT))
        ){
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
    public static void setOneTimeTriggered(KeyCode keycode, boolean pressed) {
        if (keycode.equals(KeyCode.UP)||keycode.equals(KeyCode.DOWN)||keycode.equals(KeyCode.LEFT)||keycode.equals(KeyCode.RIGHT)){
            if (pressed) {
                if (keyPressHandled) {
                    keyPressed.remove(keycode);
                    keyPressHandled = true;
                }
                else if (!keyPressHandled&&!keyPressed.contains(keycode)) {
                    keyPressed.add(keycode);
                    keyPressHandled = true;
                    if(keyPressed.contains(KeyCode.R)){
                        if (keycode.equals(KeyCode.UP)) {
                            UserInterface.slotRow--;
                            if (UserInterface.slotRow < 0) {
                                UserInterface.slotRow = 0;
                            }
                        }
                        if (keycode.equals(KeyCode.LEFT)) {
                            UserInterface.slotCol--;
                            if (UserInterface.slotCol < 0) {
                                UserInterface.slotCol = 0;
                            }
                        }
                        if (keycode.equals(KeyCode.DOWN)) {
                            UserInterface.slotRow++;
                            if (UserInterface.slotRow > 3) {
                                UserInterface.slotRow = 3;
                            }
                        }
                        if (keycode.equals(KeyCode.RIGHT)) {
                            UserInterface.slotCol++;
                            if (UserInterface.slotCol > 4) {
                                UserInterface.slotCol = 4;
                            }
                        }
                    }
                }

            }
            //if release then remove from array
            else {
                keyPressed.remove(keycode);
                keyPressHandled = false;
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


