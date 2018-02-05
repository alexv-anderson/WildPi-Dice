/*
 * Licensed under the Creative Commons Attribution 4.0 International license, 2018, Alex Anderson
 */

package com.wildpi.games.structures.dice;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * @author Alex
 */
public class FairDieTest
{
    @Test
    public void fairCoin()
    {
        double errorMargin = 0.05;

        Boolean[] outcomes = new Boolean[]{true, false};
        Die<Boolean> coin = new FairDie<>(outcomes);

        int numTosses = 10000;
        int[] counts = countOutcomes(coin, outcomes, numTosses);

        assertThat("Bad distribution between evenly weighted sides",
                counts[0] / (double) numTosses,
                is(closeTo(0.5, errorMargin)));
        assertThat("Bad distribution between evenly weighted sides",
                counts[1] / (double) numTosses,
                is(closeTo(0.5, errorMargin)));
    }

    private <V> int[] countOutcomes(Die<V> die, V[] outcomes, int numRolls)
    {
        int[] counts = new int[outcomes.length];
        Arrays.fill(counts, 0);

        for(int rollNumber = 1; rollNumber <= numRolls; rollNumber++)
        {
            die.roll();
            for(int i = 0; i < outcomes.length; i++)
            {
                if(outcomes[i].equals(die.getValue()))
                {
                    counts[i]++;
                    break;
                }
            }
        }

        return counts;
    }
}
