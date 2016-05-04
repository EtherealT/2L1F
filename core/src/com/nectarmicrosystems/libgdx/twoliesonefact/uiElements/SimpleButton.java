package com.nectarmicrosystems.libgdx.twoliesonefact.uiElements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by oluwatobi on 12/26/2015.
 */
public class SimpleButton {
    private float x, y, width, height;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;
    private Rectangle bounds;

    private boolean isPressed = false;
    private boolean isClickable = true;

    public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);
    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }


    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height);
        } else {
            batcher.draw(buttonUp, x, y, width, height);
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

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updateBounds(float x, float y, float width, float height){
        bounds.set(x, y, width, height);
    }
}
