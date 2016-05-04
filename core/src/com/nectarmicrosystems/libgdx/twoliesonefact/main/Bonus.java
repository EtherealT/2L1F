package com.nectarmicrosystems.libgdx.twoliesonefact.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by oluwatobi on 1/16/2016.
 */
public class Bonus {
    private float x, y, width, height;
    private TextureRegion usedTexture;
    private TextureRegion notUsedTexture;
    private Rectangle bounds;
    private boolean isPressed = false;
    private boolean used;

    public Bonus(float x, float y, float width, float height, TextureRegion usedTexture, TextureRegion notUsedTexture){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.usedTexture = usedTexture;
        this.notUsedTexture = notUsedTexture;
        this.used = false;
        bounds = new Rectangle(x, y, width, height);
    }

    public void draw(SpriteBatch batcher) {
        if (used) {
            batcher.draw(usedTexture, x, y, width, height);
        } else {
            batcher.draw(notUsedTexture, x, y, width, height);
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        // It only counts as a touchUp if the button is in a pressed state.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        // Whenever a finger is released, we will cancel any presses.
        isPressed = false;
        return false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public TextureRegion getUsedTexture() {
        return usedTexture;
    }

    public void setUsedTexture(TextureRegion usedTexture) {
        this.usedTexture = usedTexture;
    }

    public TextureRegion getNotUsedTexture() {
        return notUsedTexture;
    }

    public void setNotUsedTexture(TextureRegion notUsedTexture) {
        this.notUsedTexture = notUsedTexture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }
}
