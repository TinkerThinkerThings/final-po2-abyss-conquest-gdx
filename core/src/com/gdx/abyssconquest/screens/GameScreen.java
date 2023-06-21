package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.abyssconquest.AbyssConquest;

public class GameScreen extends AbyssScreen {
  private SpriteBatch batch;
  private Texture img;
  private Music gsm;

  public GameScreen(Game game) {
    super(game);
    AbyssConquest ac = (AbyssConquest) game;
    ac.getScm().stop();
  }

  @Override
  public void show() {
    batch = new SpriteBatch();
    img = new Texture("assets/images/abyss_kanan/abyss_diam.png.png");
    gsm = Gdx.audio.newMusic(Gdx.files.internal("assets/music_and_sounds/gs_music.mp3"));
    gsm.play();
    gsm.setVolume(0.7f);
    gsm.setLooping(true);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(img, 0, 0);
    batch.end();

    if (Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
      game.setScreen(new GameOverScreen(game));
    }
  }

  @Override
  public void dispose() {
    batch.dispose();
  }
}