package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.abyssconquest.Zombie;

public class WelcomeScreen extends AbyssScreen {
    private SpriteBatch batch;
    private TextureRegion wcscTexture;
    private float alpha = 0;
    private final float fadeInDuration = 4f;
    private Zombie zombie;

    public WelcomeScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        wcscTexture = new TextureRegion(new Texture(Gdx.files.internal("assets/images/abyss_conquest.png.png")));
        batch = new SpriteBatch();
        alpha = 0;

        // Membuat objek Zombie
        zombie = new Zombie(350, 100, 64, 64, "assets/images/Zombie/Kanan/DiamKanan.png", 100);
        zombie.create();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        alpha += delta / fadeInDuration;

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(wcscTexture, 0, 0);
        batch.setColor(1, 1, 1, 1);

        // Merender karakter Zombie
        zombie.update(delta);
        zombie.updateStateTime(delta);
        zombie.render(batch);

        batch.end();
        if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void hide() {
        Gdx.app.debug("AbyssConquest", "dispose WelcomeScreen");
        batch.dispose();
        wcscTexture.getTexture().dispose();
    }
}