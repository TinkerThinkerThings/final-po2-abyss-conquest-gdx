package com.gdx.abyssconquest;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class MapRenderer {
  private TiledMapRenderer tiledMapRenderer;

  public MapRenderer(TiledMap tiledMap, Camera camera) {
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    tiledMapRenderer.setView((OrthographicCamera) camera);
  }

  public void render() {
    tiledMapRenderer.render();
  }

  public void dispose() {
    ((Disposable) tiledMapRenderer).dispose();
  }
}