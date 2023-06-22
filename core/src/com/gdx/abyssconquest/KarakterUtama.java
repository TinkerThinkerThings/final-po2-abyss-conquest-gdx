package com.gdx.abyssconquest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class KarakterUtama extends Character {
    private float speed;
    private int health;
    private boolean isJumping;
    private float jumpPower;
    private float gravity;
    private float velocityY;

    public KarakterUtama(float x, float y, float width, float height, String imagePath, float speed, int health) {
        super(x, y, width, height, imagePath);
        this.speed = speed;
        this.health = health;
        this.isJumping = false;
        this.jumpPower = 300f;
        this.gravity = 800f;
        this.velocityY = 0f;
    }

    @Override
    public void update(float delta) {
        float movement = speed * delta;
        boundsColDetect.x += movement;

        if (isJumping) {
            velocityY += gravity * delta;
            boundsColDetect.y += velocityY * delta;

            if (boundsColDetect.y <= 0) {
                boundsColDetect.y = 0;
                isJumping = false;
                velocityY = 0;
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = jumpPower;
        }
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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public float getWidth() {
        return boundsColDetect.width;
    }

    public float getHeight() {
        return boundsColDetect.height;
    }

    public Vector2 getPosition() {
        return new Vector2(boundsColDetect.x, boundsColDetect.y);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.draw(character, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
    }
}