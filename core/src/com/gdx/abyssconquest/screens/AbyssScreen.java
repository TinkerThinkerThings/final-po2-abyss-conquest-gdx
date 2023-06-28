package com.gdx.abyssconquest.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

public abstract class AbyssScreen implements Screen {
  Game game;

  public AbyssScreen(Game game) {
    this.game = game;
  }

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void show() {
  }

  public void update() {

  }

  @Override
  public void hide() {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
  }
}