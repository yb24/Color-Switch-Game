import javafx.animation.KeyFrame;
import javafx.scene.Group;

public class Tile_object_animation {
    public Group shape;
    public KeyFrame animation;
    private Obstacle retObstacle;

    Tile_object_animation() {
        shape = null;
        animation = null;
    }

    Tile_object_animation(Group shape, KeyFrame Animation, Obstacle o) {
        this.shape = shape;
        this.animation = Animation;
        this.retObstacle = o;
    }

    public Obstacle getRetObstacle() {
        return retObstacle;
    }
}
