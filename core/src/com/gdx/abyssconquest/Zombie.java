package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie extends Character {
    // Konstanta Rows and Columns of The Sprite Sheet
    private static final int FRAME_COLS = 2, FRAME_ROWS = 2;

    // Objek yang Digunakan
    Animation<TextureRegion> zombieAnimation;
    Texture sprshZombie;
    SpriteBatch batch;
    private float stateTime;

    private float speed;

    public Zombie(float x, float y, float width, float height, String imagePath, float speed) {
        super(x, y, width, height, imagePath);
        this.speed = speed;
    }

    @Override
    public void create() {
        sprshZombie = new Texture(Gdx.files.internal("assets/images/spritesheet_zombie.png"));
        TextureRegion[][] tmp = TextureRegion.split(sprshZombie, sprshZombie.getWidth() / FRAME_COLS,
                sprshZombie.getHeight() / FRAME_ROWS);
        TextureRegion[] attackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                attackFrames[index++] = tmp[i][j];
            }
        }
        zombieAnimation = new Animation<TextureRegion>(1f, attackFrames);
        batch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = zombieAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
    }

    public void updateStateTime(float delta) {
        stateTime += delta;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}