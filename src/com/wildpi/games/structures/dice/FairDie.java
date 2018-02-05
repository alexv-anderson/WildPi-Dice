/*
 * Licensed under the Creative Commons Attribution 4.0 International license, 2018, Alex Anderson
 */

package com.wildpi.games.structures.dice;

import java.util.Arrays;

/**
 * Defines a die which is "fair"; therefore, each value on the die has an equal probability of being rolled.
 *
 * Note: This is a special case of a {@link WeightedDie}
 *
 * @param <V> The type of values which the die can yield
 *
 * @author Alex
 */
class FairDie<V> extends WeightedDie<V>
{
    /**
     * Initializes the die
     *
     * @param values An array of all the values which the die can yield (one value for each side).
     */
    public FairDie(V[] values)
    {
        super(Arrays.stream(values).mapToDouble(v -> 1.0 / values.length).toArray(), values);
    }
}
