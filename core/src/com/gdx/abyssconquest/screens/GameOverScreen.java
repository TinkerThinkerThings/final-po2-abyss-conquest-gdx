package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends AbyssScreen {
    private SpriteBatch spriteBatch;
    private Texture gameOverTexture;

    public GameOverScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        gameOverTexture = new Texture("GameOver.png");
    }

    @Override
    public void render(float delta) {
        // Bersihkan layar dengan warna hitam
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Mulai merender
        spriteBatch.begin();

        // Gambar tekstur game over pada tengah layar
        float x = (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2;
        float y = (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2;
        spriteBatch.draw(gameOverTexture, x, y);

        // Selesaikan merender
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        gameOverTexture.dispose();
    }
}