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

    // Variabel Animasi
    private Texture[] runFrames;
    private Animation<Texture> runningAnimation;
    private float animatime;

    public KarakterUtama(float x, float y, float width, float height, String imagePath, float speed, int health) {
        super(x, y, width, height, imagePath);
        this.speed = speed;
        this.health = health;
    }

    public void loadRunningAnimation(String[] framePaths, float frameDuration) {
        runFrames = new Texture[framePaths.length];
        for (int i = 0; i < framePaths.length; i++) {
            runFrames[i] = new Texture(framePaths[i]);
        }
        runningAnimation = new Animation<Texture>(frameDuration, runFrames);
        animatime = 0;
    }

    @Override
    public void update(float delta) {
        float movement = speed * delta;
        boundsColDetect.x += movement;
        animatime += delta;
    }

    @Override
    public void render(SpriteBatch batch) {
        Texture currentFrame = runningAnimation.getKeyFrame(animatime, true);
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

    public Vector2 getPosition() {
        return new Vector2(boundsColDetect.x, boundsColDetect.y);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.draw(character, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
    }
}