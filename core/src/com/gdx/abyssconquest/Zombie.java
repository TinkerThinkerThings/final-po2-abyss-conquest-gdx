package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie {
    // Konstanta Rows and Columns of The Sprite Sheet
    private static final int FRAME_COLS = 2;
    private static final int FRAME_ROWS = 2;
    private float x; // Posisi zombie terhadap x
    private float y; // Posisi zombie terhadap y
    private float width; // Size lebar pada karakter
    private float height; // Size tinggi pada karakter

    private TextureRegion idleFrame;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> zombieAnimationKanan; // Animasi kanan zombie
    private Animation<TextureRegion> zombieAnimationKiri; // Animasi kiri zombie
    private float stateTime = 0;

    public Zombie(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Load animasi spritesheet
        Texture spritesheet = new Texture(Gdx.files.internal("assets/images/spritesheet_zombie_Kiri.png"));

        // Potongan - potongan animasi
        TextureRegion[][] regions = TextureRegion.split(spritesheet, spritesheet.getWidth() / FRAME_COLS,
                spritesheet.getHeight() / FRAME_ROWS);

        // Mengubah Array 2D menjadi Array 1D
        TextureRegion[] leftAttackZombie = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                leftAttackZombie[index++] = regions[i][j];
            }
        }
        // Mengatur kondisi frame pertama
        idleFrame = leftAttackZombie[0];

        // Membuat animasi dari frame-frame yang ada
        zombieAnimationKiri = new Animation<>(0.5f, leftAttackZombie);
        stateTime = 0f;
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void render(SpriteBatch batch) {
        currentFrame = zombieAnimationKiri.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y);
    }

    // SETTER GETTER
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}