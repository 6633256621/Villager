package panel;

import config.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Import Label
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;

public class MenuPane extends BorderPane {
    Button play, how;
    Image background = new Image(ClassLoader.getSystemResourceAsStream("backgrounds/menu.jpg"));
    Image howPic = new Image(ClassLoader.getSystemResourceAsStream("buttons/how.png"));
    Image playPic = new Image(ClassLoader.getSystemResourceAsStream("buttons/play.png"));
    Image gifImage = new Image(ClassLoader.getSystemResourceAsStream("buttons/slime.gif")); // Change "your_gif.gif" to your actual GIF file
    BackgroundImage backgroundImage = new BackgroundImage(background,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, new BackgroundSize(
            this.getWidth(), // Width of the MenuPane
            this.getHeight(), // Height of the MenuPane
            false, // Width is relative
            false, // Height is relative
            true, // Width is contain
            true // Height is contain
    ));

    public MenuPane() {
        super();
        this.setBackground(new Background(backgroundImage));
        // Add GIF image above the play button
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setFitWidth(Config.tileSize);
        gifImageView.setFitHeight(Config.tileSize);
        setButton();

        // Create title label
        Label titleLabel = new Label("Lost in Island");
        titleLabel.setStyle("-fx-font-size: 120px; -fx-text-fill: white; -fx-font-weight: bold;"); // Adjust font size, color, and style
        VBox.setMargin(titleLabel, new Insets(50, 0, 20, 0)); // Add padding to the top

        // Set the title label above the play button
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        this.setTop(titleBox);

        // Set the how button in the bottom-right
        HBox bottomRightBox = new HBox(how);
        bottomRightBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightBox.setSpacing(10); // Adjust spacing as needed
        HBox.setMargin(how, new Insets(0, 10, 10, 0)); // Add margin to the right
        this.setBottom(bottomRightBox);

        // Set the play button in the center
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(gifImageView);
        centerBox.getChildren().add(play);
        // Set padding to move the play button up
        VBox.setMargin(gifImageView, new Insets(-150, 0, 0, 0)); // Adjust the top margin as needed
        VBox.setMargin(play, new Insets(-13, 0, 0, 0)); // Adjust the top margin as needed

        // Set the center of the BorderPane to the VBox containing the GIF image and the play button
        this.setCenter(centerBox);

        // Add hover effect to the play button
        play.setOnMouseEntered(e -> play.setStyle("-fx-background-color: #666666;"));
        play.setOnMouseExited(e -> play.setStyle("-fx-background-color: black;"));

        // Add hover effect to the how button
        how.setOnMouseEntered(e -> how.setStyle("-fx-background-color: #666666;"));
        how.setOnMouseExited(e -> how.setStyle("-fx-background-color: black;"));

        // Add action for play button
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Goto.startGame();
            }
        });
    }

    public void setButton() {
        play = new Button();
        play.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        ImageView playImageView = new ImageView(playPic); // Create ImageView for playPic
        playImageView.setFitHeight(Config.tileSize * 2);
        playImageView.setFitWidth(Config.tileSize * 6);
        play.setGraphic(playImageView); // Set playPic as graphic for the play button

        how = new Button();
        how.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        ImageView howImageView = new ImageView(howPic); // Create ImageView for howPic
        howImageView.setFitHeight(Config.tileSize * 2);
        howImageView.setFitWidth(Config.tileSize * 2);
        howImageView.setPreserveRatio(false);
        how.setGraphic(howImageView); // Set howPic as graphic for button


        how.setPrefWidth(Config.tileSize * 2);
        how.setPrefHeight(Config.tileSize * 2);
    }
}
