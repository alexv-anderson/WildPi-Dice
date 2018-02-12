# WildPi-Dice
This library provides both fair and weighted die for use in games.

## Table of Contents
1. [Building the JAR](#building-the-jar)
2. [Creating and Rolling a Die](#creating-and-rolling-a-die)
3. [Working with Weighted Die](#working-with-weighted-die)
4. [Working with "Fair" Die](#working-with-fair-die)
5. [Using Generics for Non-Standard Die](#using-generics-for-non-standard-die)

### Building the JAR
To build the JAR from source:
1. Clone the repository
2. Navigate the terminal to the project's base directory
3. Execute `ant jar`
4. The JAR should be located in `{Project Directory}/jar`

### Creating and Rolling a Die
The construction of a die is handled by the utility methods in the `DieBuilder` class. Additionally, the `Die` interface allows its user to perform two actions:
1. Rolling the die
2. Getting the current value of the die


Below is a simple example of creating a die and displaying its value.
```java
Die<Integer> die = DieBuilder.fairSixSidedDie();
die.roll();
System.out.println(die.getValue());
```


### Working with Weighted Die
When working with weighted die some of the die's values will be rolled more often then others. The increase (or decrease) in the probability of a value being rolled is referred to the value's "weight".
The probability that a particular value will be rolled is based on the ratio of the value's weight to the die's total weight. This relationship can be expressed mathematical by:
```
P(v) = w(v) / w(V)
```
Where
```
P(v) -> The probability of the die yielding v on a particular roll
w(v) -> The weight assigned to v
w(V) -> The sum of the wights assigned to all of the die's values
```


_Note: A side effect of using this method to assign probabilities to the die's values is that the weight of the values do not need to sum to 1; however, the weights must still be positive._


Weighted die can be created using the `DieBuilder` as shown below:
```java
//Create a die where 5 occurs with a higher probability
double[] weights = new double[] {0.2, 0.2, 0.2, 0.2, 0.3, 0.2};
Integer[] values = new Integer[] {1, 2, 3, 4, 5, 6};

Die<Integer> weightedDie = DieBuilder.weightedDie(weights, values);
```


### Working with "Fair" Die
A "fair" die is one where each of the die's values has an equal probability of being rolled. _(From a certain point of view, a "fair" die is simply a weighted die whose values have the same weight.)_

"Fair" die can be created using the `DieBuilder` as shown below:
```java
Integer[] values = new Integer[] {1, 2, 3, 4, 5, 6};

Die<Integer> fairDie = DieBuilder.fairDie(values);
```

_Note: Be careful to not unintentionally create a weighted die by allowing duplicate values in the array given to `DieBuilder`._


### Using Generics for Non-Standard Die
Non-standard die can be created by leveraging the `Die` interface's generic parameter. For instance, a colored die can be created when an `enum` is used.

```java
enum Color { RED, BLUE, GREEN }

public class Main {
  public static void main(String[] args) {
    Color[] colors = new Color[] { BLUE, BLUE, BLUE, GREEN, RED, RED };

    Die<Color> coloredDie = DieBuilder.fairDie(colors);

    int numRollsBeforeGreen = 0;
    boolean done = false
    do {
      coloredDie.roll();
      switch(coloredDie.getValue()) {
        case Color.GREEN:
          done = true;
        default:
          numRollsBeforeGreen++;
      }
    } while(!done);

    System.out.println("Number of rolls before green: " + numRollsBeforeGreen);
  }
}
```
