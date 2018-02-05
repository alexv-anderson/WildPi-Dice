/*
 * Licensed under the Creative Commons Attribution 4.0 International license, 2018, Alex Anderson
 */

package com.wildpi.games.structures.dice;

/**
 * Helper class which can construct various types of dice.
 *
 * @author Alex
 */
public class DieBuilder
{
    /**
     * Uses parallel arrays of weights and their corresponding values to initialize the die.
     * This allows the die's sides to be weighted which allows one or more values be rolled with a higher probability.
     *
     * @param weights An array of weights describing the relative probability that the die will yield its corresponding value
     * @param values  An array of all the values which the die can yield (one value for each side).
     * @param <V> The type of values which the die can yield
     * @return A die which has values which may or may not be rolled with a higher probability
     */
    public static <V> Die<V> weightedDie(double[] weights, V[] values)
    {
        return new WeightedDie<>(weights, values);
    }

    /**
     * Uses the array of values to create a single die where one side of the die has one of the given values.
     * Each of the values given in the array will have an equal probability of being rolled.
     *
     * @param values An array of all the values which the die can yield (one value for each side).
     * @param <V> The type of values which the die can yield
     * @return A "fair" die where each value on the die has an equal probability of being rolled.
     */
    public static <V> Die<V> fairDie(V[] values)
    {
        return new FairDie<>(values);
    }

    /**
     * Creates a single fair die with six sides and values of 1, 2, 3, 4, 5, and 6.
     *
     * Note: This is identical to calling {@link DieBuilder#fairDie(Object[])} with a value array of {1, 2, 3, 4, 5, 6}.
     *
     * @return A fair six sided die.
     */
    public static Die<Integer> fairSixSidedDie()
    {
        return DieBuilder.fairDie(new Integer[]{1, 2, 3, 4, 5, 6});
    }
}
