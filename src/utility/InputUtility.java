package utility;

import config.GameState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import object.Trader;

import java.util.ArrayList;

public class InputUtility {
    private static boolean isClicked = false;
    private static boolean keyPressHandled = false;
    private static ArrayList<KeyCode> lastKeyPressed = new ArrayList<>();
    //array of all button which is interacted
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
    private static int i = 0;

    public static boolean isKeyPressed(KeyCode keycode) {
        if (keycode.equals(KeyCode.RIGHT) && keyPressed.contains(keycode)) {
        }
        return keyPressed.contains(keycode);
    }

    public static void setKeyPressed(KeyCode keycode, boolean pressed) {
        //if pressed add to array
        if (!keycode.equals(KeyCode.R) && !keycode.equals(KeyCode.P) && !keycode.equals(KeyCode.J) &&
                !(keycode.equals(KeyCode.UP) || keycode.equals(KeyCode.DOWN) || keycode.equals(KeyCode.LEFT) || keycode.equals(KeyCode.RIGHT))
        ) {
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
        if (keycode.equals(KeyCode.R) || keycode.equals(KeyCode.P) || keycode.equals(KeyCode.J)) {
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

        if (keycode.equals(KeyCode.UP) || keycode.equals(KeyCode.DOWN) || keycode.equals(KeyCode.LEFT) || keycode.equals(KeyCode.RIGHT) || keycode.equals(KeyCode.ENTER)|| keycode.equals(KeyCode.SPACE)) {
            if (pressed) {
                checkPress(keycode);
            }
            //if release then remove from array
            else {
                keyPressed.remove(keycode);
                keyPressHandled = false;
            }
        }
    }
    private static void checkPress(KeyCode keycode) {

        if (keyPressHandled) {
            keyPressed.remove(keycode);
            keyPressHandled = true;
        } else if (!keyPressHandled && !keyPressed.contains(keycode)) {
            keyPressed.add(keycode);
            keyPressHandled = true;
            if (keyPressed.contains(KeyCode.R) || GameState.chestState|| GameState.traderState) {
                if (keyPressed.contains(KeyCode.J)) {
                    pressJ(keycode);
                } else {
                    notPressJ(keycode);
                }
            }
            if (GameState.traderState && !GameState.chooseState) {
                traderCheck(keycode);
            }
        }


    }
    private static void traderCheck(KeyCode keycode) {
        if (keycode.equals(KeyCode.UP)) {
            Trader.optionCol--;
            if (Trader.optionCol < 0) {
                Trader.optionCol = 0;
            }
        }
        if (keycode.equals(KeyCode.DOWN)) {
            Trader.optionCol++;
            if (Trader.optionCol > 1) {
                Trader.optionCol = 1;
            }
        }
    }
    private static void pressJ(KeyCode keycode){
        if (keycode.equals(KeyCode.UP)) {
            UserInterface.leftSlotRow--;
            Trader.buyingRow--;
            if (Trader.buyingRow < 0) {
                Trader.buyingRow = 0;
            }
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
            Trader.buyingRow++;
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
    }
    private static void notPressJ(KeyCode keycode) {
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
    public class MouseInputUtility {
        private static boolean mouseClicked;

        public static void handleMousePressed(MouseEvent event) {
            if (event.isPrimaryButtonDown()) {
                if (!mouseClicked) {
                    mouseClicked = true;
                }
            }
        }

        public static boolean isMouseClicked() {
            if (mouseClicked) {
                mouseClicked = false; // Automatically reset after one click
                return true;
            }
            return false;
        }
    }

    public static boolean isOnlyOneKeyPressed() {
        return keyPressed.size() == 1;
    }

    public static ArrayList<KeyCode> getKeyPressed() {
        return keyPressed;
    }
}


