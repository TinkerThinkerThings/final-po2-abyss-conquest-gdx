package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class KarakterUtama extends Character {
    // Konstanta
    private static final int FRAME_COLS = 5, FRAME_ROWS = 1;
    private static final float MOVE_SPEED = 75f;

    Animation<TextureRegion> mcAnimationRight;
    Animation<TextureRegion> mcAnimationLeft;
    Texture spshMainCharacterRight, spshMainCharacterLeft;
    TextureRegion currentFrame;
    SpriteBatch batch;
    private float stateTime;

    private boolean isMovingLeft;
    private boolean isMovingRight;

    // Attribut Karakter
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
    public void create() {
        super.create();
        spshMainCharacterRight = new Texture(Gdx.files.internal("assets/images/animasi_karakter/lari_kanan.png"));
        // Perhitungan Frame untuk Kanan
        TextureRegion[][] tmpRight = TextureRegion.split(spshMainCharacterRight,
                spshMainCharacterRight.getWidth() / FRAME_COLS,
                spshMainCharacterRight.getHeight() / FRAME_ROWS);
        TextureRegion[] runFramesRight = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int indexRight = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                runFramesRight[indexRight++] = tmpRight[i][j];
            }
        }
        mcAnimationRight = new Animation<TextureRegion>(0.5f, runFramesRight);

        spshMainCharacterLeft = new Texture(Gdx.files.internal("assets/images/animasi_karakter/lari_kiri.png"));
        // Perhitungan Frame untuk Kiri
        TextureRegion[][] tmpLeft = TextureRegion.split(spshMainCharacterLeft,
                spshMainCharacterLeft.getWidth() / FRAME_COLS,
                spshMainCharacterLeft.getHeight() / FRAME_ROWS);
        TextureRegion[] runFramesLeft = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int indexLeft = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                runFramesLeft[indexLeft++] = tmpLeft[i][j];
            }
        }
        mcAnimationLeft = new Animation<TextureRegion>(0.5f, runFramesLeft);
        batch = new SpriteBatch();
        stateTime = 0f;
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
        handleInput();
        updateStateTime(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isMovingRight) {
            currentFrame = mcAnimationRight.getKeyFrame(stateTime, true);
        } else {
            currentFrame = mcAnimationLeft.getKeyFrame(stateTime, true);
        }
        batch.draw(currentFrame, boundsColDetect.x, boundsColDetect.y);
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = jumpPower;
        }
    }

    public void handleInput() {
        isMovingLeft = Gdx.input.isKeyPressed(Input.Keys.A);
        isMovingRight = Gdx.input.isKeyPressed(Input.Keys.D);

        if (isMovingLeft) {
            boundsColDetect.x -= MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }
        if (isMovingRight) {
            boundsColDetect.x += MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            jump();
        }
    }

    public void updateStateTime(float delta) {
        stateTime += delta;
    }

    // Setter and Getter
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

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}