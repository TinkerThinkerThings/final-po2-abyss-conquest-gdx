package com.gdx.abyssconquest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Character {
  protected Texture character;
  protected Rectangle boundsColDetect;

  public Character(float x, float y, float width, float height, String imagePath) {
    character = new Texture(imagePath);
    boundsColDetect = new Rectangle(x, y, width, height);
  }

  public void create() {

  }

  public void update(float delta) {
  }

  public void render(SpriteBatch batch) {
    batch.draw(character, boundsColDetect.x, boundsColDetect.y, boundsColDetect.width, boundsColDetect.height);
  }

  public Rectangle getBoundsColDetection() {
    return boundsColDetect;
  }

  public void dispose() {
    character.dispose();
  }
}