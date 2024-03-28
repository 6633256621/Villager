package utility;

import config.GameState;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class InputUtility {
    private static boolean isClicked = false;
    private static boolean keyPressHandled = false;
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
        if (!keycode.equals(KeyCode.R)&&!keycode.equals(KeyCode.P)&&!keycode.equals(KeyCode.J)&&
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
        if (keycode.equals(KeyCode.R)||keycode.equals(KeyCode.P)||keycode.equals(KeyCode.J)){
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

        if (keycode.equals(KeyCode.UP)||keycode.equals(KeyCode.DOWN)||keycode.equals(KeyCode.LEFT)||keycode.equals(KeyCode.RIGHT)||keycode.equals(KeyCode.ENTER)){
            if (pressed) {
                if (keyPressHandled) {
                    keyPressed.remove(keycode);
                    keyPressHandled = true;
                }
                else if (!keyPressHandled&&!keyPressed.contains(keycode)) {
                    keyPressed.add(keycode);
                    keyPressHandled = true;
                    if(keyPressed.contains(KeyCode.R)|| GameState.chestState){
                        if (keyPressed.contains(KeyCode.J)){
                            if (keycode.equals(KeyCode.UP)) {
                                UserInterface.leftSlotRow--;
                                if (UserInterface.leftSlotRow < 0) {
                                    UserInterface.leftSlotRow = 0;
                                }
                            }
                            if (keycode.equals(KeyCode.LEFT)) {
                                UserInterface.leftSlotCol--;
                                if (UserInterface.leftSlotCol < 0) {
                                    UserInterface.leftSlotCol = 0;
                                }
                            }
                            if (keycode.equals(KeyCode.DOWN)) {
                                UserInterface.leftSlotRow++;
                                if (UserInterface.leftSlotRow > 3) {
                                    UserInterface.leftSlotRow = 3;
                                }
                            }
                            if (keycode.equals(KeyCode.RIGHT)) {
                                UserInterface.leftSlotCol++;
                                if (UserInterface.leftSlotCol > 4) {
                                    UserInterface.leftSlotCol = 4;
                                }
                            }
                        } else {
                            if (keycode.equals(KeyCode.UP)) {
                                UserInterface.rightSlotRow--;
                                if (UserInterface.rightSlotRow < 0) {
                                    UserInterface.rightSlotRow = 0;
                                }
                            }
                            if (keycode.equals(KeyCode.LEFT)) {
                                UserInterface.rightSlotCol--;
                                if (UserInterface.rightSlotCol < 0) {
                                    UserInterface.rightSlotCol = 0;
                                }
                            }
                            if (keycode.equals(KeyCode.DOWN)) {
                                UserInterface.rightSlotRow++;
                                if (UserInterface.rightSlotRow > 3) {
                                    UserInterface.rightSlotRow = 3;
                                }
                            }
                            if (keycode.equals(KeyCode.RIGHT)) {
                                UserInterface.rightSlotCol++;
                                if (UserInterface.rightSlotCol > 4) {
                                    UserInterface.rightSlotCol = 4;
                                }
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

    public static ArrayList<KeyCode> getKeyPressed() {
        return keyPressed;
    }
}


