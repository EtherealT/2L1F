package com.nectarmicrosystems.libgdx.twoliesonefact.tweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.nectarmicrosystems.libgdx.twoliesonefact.uiElements.SimpleButton;

/**
 * Created by oluwatobi on 12/15/2015.
 */
public class SimpleButtonAccessor implements TweenAccessor<SimpleButton> {
    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;

    @Override
    public int getValues(SimpleButton target, int tweenType, float[] returnValues) {
        switch (tweenType) {

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
    public void setValues(SimpleButton target, int tweenType, float[] newValues) {
        switch (tweenType) {

            case POSITION_X:
                target.setX(newValues[0]);
                break;

            case POSITION_Y:
                target.setY(newValues[0]);
                break;

        }
    }
}
