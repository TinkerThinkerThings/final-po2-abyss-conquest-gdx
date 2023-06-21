package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.gdx.abyssconquest.AbyssConquest;

public class GameScreen extends AbyssScreen {
  private TiledMap tiledMap;
  private TmxMapLoader mapLoader;

  public GameScreen(Game game) {
    super(game);
    AbyssConquest ac = (AbyssConquest) game;
    ac.getScm().stop();
  }

  @Override
  public void show() {
    mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load();
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void dispose() {
    tiledMap.dispose();
  }
}