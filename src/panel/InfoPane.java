package panel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class InfoPane extends VBox {
    Image page = new Image(ClassLoader.getSystemResourceAsStream("buttons/background.png"));

    public InfoPane() {
        super();
        this.setAlignment(Pos.CENTER); // Center align the content of the VBox
        this.setPrefSize(1000, 1000);

        // Create HBox for the "Close" button and align it to the top right
        HBox closeButtonBox = new HBox();
        closeButtonBox.setAlignment(Pos.TOP_CENTER);
        closeButtonBox.setPadding(new Insets(0,0,0,430));

        // Create Close object
        Close close = new Close();

        // Add Close button to the HBox
        closeButtonBox.getChildren().add(close);

        // Create VBox for "Made By" label and align it to top center
        VBox madeByBox = new VBox();
        madeByBox.setAlignment(Pos.CENTER);
        madeByBox.setPadding(new Insets(0, 0, 0, 0)); // Add top padding to push it down

        // Create and configure the "Made By" label
        Label titleLabel = new Label("Made By");
        titleLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;"); // Example style

        // Add "Made By" label to the VBox
        madeByBox.getChildren().add(titleLabel);

        // Create labels for additional information
        Label infoLabel1 = new Label("6633256621 Sorravich Lakngoenchai");
        Label infoLabel2 = new Label("6633219421 Worawalun Setthawiwat");
        Label infoLabel3 = new Label("6633071821 Nattapong Rukngan");
        Label infoLabel4 = new Label("6633207921 Ravisara Maka");
        infoLabel1.setStyle("-fx-font-size: 18; -fx-font-weight: 600;");
        infoLabel2.setStyle("-fx-font-size: 18; -fx-font-weight: 600;");
        infoLabel3.setStyle("-fx-font-size: 18; -fx-font-weight: 600;");
        infoLabel4.setStyle("-fx-font-size: 18; -fx-font-weight: 600;");

        // Add labels to the VBox
        this.getChildren().addAll(closeButtonBox, madeByBox, infoLabel1, infoLabel2, infoLabel3, infoLabel4);

        BackgroundSize backgroundSize = new BackgroundSize(500, 350, false, false, false, false);

        // Set background image centered in the VBox
        this.setBackground(new Background(new BackgroundImage(
                page,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        )));

        // Set margin to center the VBox
        VBox.setMargin(this, new Insets(150, 0, 0, 0));
    }
}
