package com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by oluwatobi on 12/13/2015.
 */
public class SpriteAccessor implements TweenAccessor<Sprite>{
    public static final int ALPHA = 0;
    public static final int POSITION_X = 1;
    public static final int POSITION_Y= 2;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;

            case POSITION_X:
                returnValues[0] = target.getX();
                return 1;

            case POSITION_Y:
                returnValues[0] = target.getY();
                return 1;

            default:
                return 0;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case ALPHA:
                target.setColor(1, 1, 1, newValues[0]);
                break;

            case POSITION_X:
                target.setX(newValues[0]);
                break;

            case POSITION_Y:
                target.setY(newValues[0]);
                break;

        }
    }
}
