package com.gdx.abyssconquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class KarakterUtama {
    private static final int FRAME_COLS = 5; // Dilihat dari spritesheet yang akan digunakan
    private static final int FRAME_ROWS = 1; // Dilihat dari spritesheet yang akan digunakan
    private float x; // Posisi karakter terhadap x
    private float y; // Posisi karakter terhadap y
    private float width; // Size lebar pada karakter
    private float height; // Size tinggi pada karakter

    private TextureRegion idleFrame;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation, animation2; // Animasi yang akan digunakan pada karakter
    private float stateTime = 0; // Waktu yang telah berlalu dalam animasi

    private boolean isMovingLeft = false;
    private boolean isJumping = false;
    private boolean canJump = true;

    private float gravity = -10f; // Gravitasi yang mempengaruhi karakter
    private float jumpSpeed = 300f; // Kecepatan awal lompatan karakter
    private float yVelocity = 0f; // Kecepatan vertikal karakter

    public KarakterUtama(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Load animasi spritesheet
        Texture spritesheet = new Texture(Gdx.files.internal("assets/images/animasi_karakter/lari_kanan.png"));
        Texture spritesheet2 = new Texture(Gdx.files.internal("assets/images/animasi_karakter/lari_kiri.png"));

        // Potongan-potongan animasi
        TextureRegion[][] regions = TextureRegion.split(spritesheet, spritesheet.getWidth() / FRAME_COLS,
                spritesheet.getHeight() / FRAME_ROWS);

        TextureRegion[][] regions2 = TextureRegion.split(spritesheet2, spritesheet2.getWidth() / FRAME_COLS,
                spritesheet2.getHeight() / FRAME_ROWS);

        // Mengubah Array 2D menjadi Array 1D
        TextureRegion[] runFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                runFrames[index++] = regions[i][j];
            }
        }

        TextureRegion[] runFrames2 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                runFrames2[index2++] = regions2[i][j];
            }
        }

        // Mengatur kondisi frame pertama
        idleFrame = runFrames[0];

        // Membuat animasi dari frame-frame yang ada
        animation2 = new Animation<>(0.2f, runFrames2);
        animation = new Animation<>(0.2f, runFrames);
        stateTime = 0f;
    }

    public void update() {
        // Memperbarui posisi karakter utama berdasarkan input
        inputHandling();

        // Memperbarui waktu animasi
        stateTime += Gdx.graphics.getDeltaTime();

        yVelocity += gravity; // Menerapkan gravitasi pada kecepatan vertikal
        y += yVelocity * Gdx.graphics.getDeltaTime(); // Mengubah posisi karakter berdasarkan kecepatan vertikal
        if (yVelocity != 0) {
            canJump = false;
        }
        if (y <= 125) {
            y = 125; // Menghentikan karakter di lantai (ketinggian 0)
            yVelocity = 0; // Menghentikan pergerakan vertikal karakter setelah mencapai lantai
            canJump = true;
        }
    }

    public void render(SpriteBatch spriteBatch) {
        boolean isMoving = inputHandling();
        // Mendapatkan frame animasi yang sesuai dengan waktu animasi real time
        if (isMoving) {
            if (isMovingLeft) {
                // Mendapatkan frame animasi yang sesuai
                currentFrame = animation2.getKeyFrame(stateTime, true);
            } else {
                currentFrame = animation.getKeyFrame(stateTime, true);
            }
        } else {
            currentFrame = idleFrame;
        }
        // Menggambar karakter utama pada posisi yang tepat
        spriteBatch.draw(currentFrame, x, y);
    }

    private boolean inputHandling() {
        boolean isMoving = false;
        // Menghandle input untuk menggerakkan karakter utama
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 1.25f;
            isMoving = true;
            isMovingLeft = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 1.25f;
            isMoving = true;
            isMovingLeft = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && canJump) {
            // Melompat hanya jika karakter tidak sedang dalam proses lompatan sebelumnya
            yVelocity = jumpSpeed; // Memberikan kecepatan awal lompatan karakter
            canJump = false;
            isMoving = true;
        }
        isMovingLeft = isMoving && isMovingLeft;

        return isMoving;
    }

    public boolean detectCollision(float otherX, float otherY, int otherWidth, int otherHeight) {
        // Deteksi tabrakan antara karakter utama dengan objek lain berdasarkan posisi
        // dan ukuran
        if (x < otherX + otherWidth &&
                x + width > otherX &&
                y < otherY + otherHeight &&
                y + height > otherY) {
            // Terjadi Collision
            return true;
        }
        // Tidak terjadi Collision
        return false;
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