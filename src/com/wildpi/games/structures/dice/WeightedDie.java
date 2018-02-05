/*
 * Licensed under the Creative Commons Attribution 4.0 International license, 2018, Alex Anderson
 */

package com.wildpi.games.structures.dice;

import java.util.Arrays;
import java.util.Random;

/**
 * Defines a die whose sides can be weighted to make one or more values be yielded with a higher probability.
 *
 * @param <V> The type of values which the die can yield
 *
 * @author Alex
 */
class WeightedDie<V> implements Die<V>
{
    /**
     * Initializes the die using parallel arrays of weights and their corresponding values
     *
     * @param weights An array of weights describing the relative probability that the die will yield its corresponding value
     * @param values  An array of all the values which the die can yield (one value for each side).
     */
    public WeightedDie(double[] weights, V[] values)
    {
        if(weights.length != values.length)
            throw new IllegalArgumentException("Must have the same number of weights and values for die");

        //region Initialize and Sort Arrays
        this.values = Arrays.copyOf(values, values.length);
        this.weights = Arrays.copyOf(weights, weights.length);
        Arrays.sort(this.weights);

        boolean[] used = new boolean[this.weights.length];
        Arrays.fill(used, false);
        for(int sortedIndex = 0; sortedIndex < this.weights.length; sortedIndex++)
        {
            for(int originalIndex = 0; originalIndex < this.weights.length; originalIndex++)
            {
                if(this.weights[sortedIndex] == weights[originalIndex] && !used[originalIndex])
                {
                    this.values[sortedIndex] = values[originalIndex];
                    used[originalIndex] = true;
                    break;
                }
            }
        }
        //endregion

        for(double weight : weights)
            totalWeight += weight;
    }

    @Override
    public final V getValue()
    {
        return values[index];
    }

    @Override
    public final void roll()
    {
        boolean found = false;

        double marker = random.nextDouble() * totalWeight;
        double weight = 0.0;
        for(int i = 0; i < weights.length; i++)
        {
            weight += weights[i];
            if(marker < weight)
            {
                index = i;
                found = true;
                break;
            }
        }

        if(!found)
            throw new IllegalStateException("Marker value of " + marker + " is not in the weight range [0.0, " + totalWeight + "]");
    }

    private int index;
    private double totalWeight = 0.0;
    private double[] weights;
    private V[] values;

    private static Random random = new Random();
}
