package com.gdx.abyssconquest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Zombie extends Character {
    private float speed;

    public Zombie(float x, float y, float width, float height, String imagePath, float speed) {
        super(x, y, width, height, imagePath);
        this.speed = speed;
    }

    @Override
    public void update(float delta) {
        float movement = speed * delta;
        boundsColDetect.x -= movement;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(character, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
