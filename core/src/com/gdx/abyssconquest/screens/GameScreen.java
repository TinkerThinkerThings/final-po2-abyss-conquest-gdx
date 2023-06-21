package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.abyssconquest.AbyssConquest;

public class GameScreen extends AbyssScreen {
  private SpriteBatch batch;
  private Texture img;

  public GameScreen(Game game) {
    super(game);
    AbyssConquest ac = (AbyssConquest) game;
    ac.getScm().stop();
  }

  @Override
  public void show() {
    batch = new SpriteBatch();
    img = new Texture("assets/images/badlogic.jpg");
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    batch.draw(img, 0, 0);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    img.dispose();
  }
}