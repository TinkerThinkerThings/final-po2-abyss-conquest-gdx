package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    // Konstanta Rows and Columns of The Sprite Sheet
    private static final int FRAME_COLS = 2, FRAME_ROWS = 2;

    // Objek yang Digunakan
    Animation<TextureRegion> zombieAnimationKanan, zombieAnimationKiri;
    TextureRegion currentFrame;
    Texture sprshZombieKanan, sprshZombieKiri;
    SpriteBatch batch;
    private float stateTime;
    private boolean isRight = false;
    private boolean isLeft = false;

    private float speed;

    public Zombie(float x, float y, float width, float height, String imagePath, float speed) {
        super(x, y, width, height, imagePath);
        this.speed = speed;
    }

    @Override
    public void create() {
        sprshZombieKanan = new Texture(Gdx.files.internal("assets/images/spritesheet_zombie.png"));
        TextureRegion[][] tmpKanan = TextureRegion.split(sprshZombieKanan, sprshZombieKanan.getWidth() / FRAME_COLS,
                sprshZombieKanan.getHeight() / FRAME_ROWS);
        TextureRegion[] RightAttackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int indexKanan = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                RightAttackFrames[indexKanan++] = tmpKanan[i][j];
            }
        }
        sprshZombieKiri = new Texture(Gdx.files.internal("assets/images/spritesheet_zombie_Kiri.png"));
        TextureRegion[][] tmpKiri = TextureRegion.split(sprshZombieKiri, sprshZombieKiri.getWidth() / FRAME_COLS,
                sprshZombieKiri.getHeight() / FRAME_ROWS);
        TextureRegion[] LeftAttackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int indexKiri = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                LeftAttackFrames[indexKiri++] = tmpKiri[i][j];
            }
        }
        zombieAnimationKanan = new Animation<TextureRegion>(0.5f, RightAttackFrames);
        zombieAnimationKiri = new Animation<TextureRegion>(0.5f, LeftAttackFrames);
        batch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch batch) {
        currentFrame = isRight ? zombieAnimationKanan.getKeyFrame(stateTime, true)
                : zombieAnimationKiri.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
    }

    public void updateStateTime(float delta) {
        stateTime += delta;
        if (stateTime % 2 < 1) {
            isRight = true;
            isLeft = false;
        } else {
            isRight = false;
            isLeft = true;
        }
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