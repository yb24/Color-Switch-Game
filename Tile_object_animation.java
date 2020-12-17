import javafx.animation.KeyFrame;
import javafx.scene.Group;

public class Tile_object_animation {
    public Group shape;
    KeyFrame animation;

    Tile_object_animation() {
        shape = null;
        animation = null;
    }

    Tile_object_animation(Group shape, KeyFrame Animation) {
        this.shape = shape;
        this.animation = Animation;
    }
}
