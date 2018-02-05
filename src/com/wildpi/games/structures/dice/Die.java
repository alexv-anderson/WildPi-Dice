/*
 * Licensed under the Creative Commons Attribution 4.0 International license, 2018, Alex Anderson
 */

package com.wildpi.games.structures.dice;

/**
 * Marks objects which can represent a die which can be rolled to yield a value
 *
 * @param <V> The type of values which the die can yield
 *
 * @author Alex
 */
public interface Die<V>
{
    /**
     * The value which the die is currently showing after being rolled.
     * @return The value which the die is currently showing
     */
    public V getValue();

    /**
     * Rolls the die to update its value.
     */
    public void roll();
}
