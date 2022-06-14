package cz.cvut.fel.doudamar.localmultiplayergame.utils;

import java.io.Serializable;

/**
 * Class that holds mostly movement vectors.
 */
public class Vector2 implements Serializable {
    public double x;
    public double y;

    public Vector2() {
        x = 0f;
        y = 0f;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * multiplies vector by scalar
     *
     * @param m
     */
    public void multiply(double m) {
        // multiplies this vector by const
        x *= m;
        y *= m;
    }

    /**
     * adds two vectors together
     *
     * @param v
     */
    public void add(Vector2 v) {
        // adds vector to this vector
        x += v.x;
        y += v.y;
    }

    /**
     * @return returns vectors lenght
     */
    public double getLength() {
        // returns vector length
        return Math.sqrt(x * x + y * y);
    }

    /**
     * @return returns x in int
     */
    public int getRoundX() {
        return (int) Math.round(x);
    }

    /**
     * @return returns y in int
     */
    public int getRoundY() {
        return (int) Math.round(y);
    }

    /**
     * @return returns x
     */
    public double getX() {
        return x;
    }

    /**
     * @return returns y
     */
    public double getY() {
        return y;
    }

    /**
     * normalizes vector
     */
    public void normalize() {
        // normalizes vector
        double l = getLength();
        if (l == 0) {
            return;
        }
        x /= l;
        y /= l;
    }

    /**
     * @param v
     * @return returns distance from given vector
     */
    public double distanceFrom(Vector2 v) {
        // returns distance between two vectors
        return Math.sqrt((x - v.x) * (x - v.x) + (y - v.y) * (y - v.y));
    }

    public Vector2 copy(){
        return new Vector2(x, y);
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
