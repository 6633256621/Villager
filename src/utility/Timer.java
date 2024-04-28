package utility;

import config.Config;
import config.GameState;
import config.Status;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Timer {
    public void drawTime(double elapsedTime, GraphicsContext gc) {
        drawFrame(gc);
        // Start the clock from 06:00 instead of 00:00
        long startingTime = 6 * 3600; // 6 hours * 3600 seconds/hour
        long adjustedTime = ((long) elapsedTime + startingTime) % (24 * 3600); // Ensure time wraps around at 24:00

        gc.setFont(new Font("Georgia", 30));
        gc.setFill(Color.WHITE); // Set the color to white for clear visibility
        String formattedTime = formatTime(adjustedTime);
        gc.fillText(formattedTime, Config.tileSize * 30-10, Config.tileSize);
        // Check if the adjusted time is 18:00
        if (formattedTime.equals("18:00")) {
            GameState.nightState = true;
        }
        if (formattedTime.equals("06:00")) {
            GameState.nightState = false;
        }

        // Check if the adjusted time is 00:00 and day hasn't been incremented yet
        if (formattedTime.equals("00:00") && !GameState.dayIncremented) {
            Config.day++; // Increment the day
            Status.dexBuff=false;
            Status.strengthBuff=false;
            Status.speedBuff=false;
            GameState.dayIncremented = true; // Mark day as incremented
        }

        // Reset the dayIncremented flag if the adjusted time is not 00:00
        if (!formattedTime.equals("00:00")) {
            GameState.dayIncremented = false;
        }

        gc.fillText("Day : "+Config.day, Config.tileSize * 27, Config.tileSize);
        gc.setFont(new Font("Georgia", 20));
    }
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    private void drawFrame(GraphicsContext gc) {
        UserInterface.drawSubWindow(Config.tileSize*25-10,14,Config.tileSize*7,Config.tileSize,gc);
        gc.drawImage(GameState.dayPic,Config.tileSize*25+5,22,Config.tileSize*0.7,Config.tileSize*0.7);
    }
}
