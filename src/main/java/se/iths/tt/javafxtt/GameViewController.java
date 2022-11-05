package se.iths.tt.javafxtt;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameViewController {
    public Canvas canvas;
    public GraphicsContext context;
    public SnakeModel snake = new SnakeModel();

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        gameAnimation.start();
    }

    GameAnimation gameAnimation = new GameAnimation() {
        float time;
        @Override
        public void tick(float secondsSinceLastFrame) {
            time += secondsSinceLastFrame;
            if (time < 0.2)
                return;

            snake.update();
            render();
            time = 0.0f;
        }
    };


    public void render() {
        context.setFill(Color.PAPAYAWHIP);
        context.fillRect(0,0,400,400);
        context.setFill(Color.BLUE);
        context.fillRect(snake.getPosition().x()*10, snake.getPosition().y()*10, 5,5);
    }

    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case UP -> snake.setUp();
            case DOWN -> snake.setDown();
            case LEFT -> snake.setLeft();
            case RIGHT -> snake.setRight();
        }
    }
}
