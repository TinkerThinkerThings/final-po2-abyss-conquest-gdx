package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WelcomeScreen extends AbyssScreen {
  SpriteBatch batch;
  TextureRegion wcscTexture;
  float time = 0;

  public WelcomeScreen(Game game) {
    super(game);
  }

  @Override
  public void show() {
    wcscTexture = new TextureRegion(new Texture(Gdx.files.internal("assets/images/abyss_conquest.png.png")));
    batch = new SpriteBatch();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();
    batch.draw(wcscTexture, 0, 0);
    batch.end();

    time += delta;
    if (time > 1) {
      if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
        // game.setScreen(new GameScreen(game));
      }
    }
  }

  @Override
  public void hide() {
    Gdx.app.debug("AbyssConquest", "dispose WelcomeScreen");
    batch.dispose();
    wcscTexture.getTexture().dispose();
  }
}