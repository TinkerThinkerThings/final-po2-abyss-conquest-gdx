package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.abyssconquest.AbyssConquest;
import com.gdx.abyssconquest.KarakterUtama;
import com.gdx.abyssconquest.Map;

public class GameScreen extends AbyssScreen {
  private SpriteBatch batch;
  private Map map;
  private OrthographicCamera camera;
  private KarakterUtama player;

  public GameScreen(Game game) {
    super(game);
    AbyssConquest ac = (AbyssConquest) game;
    ac.getScm().stop();
  }

  @Override
  public void show() {
    batch = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 600);
    map = new Map("assets/images/Map/MapAbyssConquest.tmx", camera);
    player = new KarakterUtama(100, 100, 64, 64, "assets/images/abyss_kanan/abyss_diam.png.png", 200, 200);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    float cameraX = Math.max(player.getPosition().x, 400);
    camera.position.set(cameraX, 300, 0);
    camera.update();

    map.render();
    batch.begin();
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    map.dispose();
  }
}