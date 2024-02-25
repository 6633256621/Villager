package tile;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import panel.GamePanel;

public class Tile {
    public Image image;
    public ImageView imageView;
    public boolean collision = false;
    public void makeScale(GamePanel gp) {
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(gp.getTileSize());
        this.imageView.setFitHeight(gp.getTileSize());
    }

    public ImageView getImageView() {
        return imageView;
    }
}
