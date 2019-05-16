public class Yatzy
{

    protected int[] dices;

    public Yatzy(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        dices = new int[] {dice1, dice2, dice3, dice4, dice5};
    }

    public int fours()
    {
        return getSumOfAllDicesWithValues(dices,4);
    }

    public int fives()
    {
        return getSumOfAllDicesWithValues(dices,5);
    }

    public int sixes()
    {
        return getSumOfAllDicesWithValues(dices,6);
    }

    public static int chance(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllDicesWithValues(dices,1,2,3,4,5,6);
    }

    public static int yatzy(int... dices)
    {
        int sum = getSumOfAllTuples(dices, 5, 1);
        if(sum != 0)
        {
            return 50;
        }
        return 0;
    }

    public static int ones(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllDicesWithValues(dices,1);
    }

    public static int twos(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllDicesWithValues(dices,2);
    }

    public static int threes(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllDicesWithValues(dices,3);
    }

    public static int score_pair(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllTuples(dices,2, 1);
    }

    public static int two_pair(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllTuples(dices,2, 2);
    }

    public static int four_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllTuples(dices,4, 1);
    }

    public static int three_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        return getSumOfAllTuples(dices,3, 1);
    }

    public static int smallStraight(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        int smallStraightSum = 15;
        return getSumOfAllTuples(dices,1,5) == smallStraightSum ? smallStraightSum : 0;
    }

    public static int largeStraight(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        int largeStraightSum = 20;
        return getSumOfAllTuples(dices,1,5) == largeStraightSum ? largeStraightSum : 0;
    }

    public static int fullHouse(int dice1, int dice2, int dice3, int dice4, int dice5)
    {
        int[] dices = new int[] {dice1, dice2, dice3, dice4, dice5};
        int[] counts = getValueOccurrences(dices);

        for (int i = 0; i < counts.length; i ++)
        {
            // if we found any dice value with an occurrence other than 0, 2 or 3, this can't be a full house
            if (counts[i] != 0 && counts[i] != 2 && counts[i] != 3)
            {
                return 0;
            }
        }
        return getSumOfAllDicesWithValues(dices, 1,2,3,4,5,6);
    }

    private static int getSumOfAllDicesWithValues(int[] dices, int... values)
    {
        int sum = 0;
        for (int i = 0; i < dices.length; i++)
        {
            for(int value : values)
            {
                if (dices[i] == value)
                {
                    sum += value;
                }
            }
        }
        return sum;
    }

    private static int[] getValueOccurrences(int[] dices)
    {
        int[] counts = new int[6];
        for (int dice : dices)
        {
            counts[dice-1]++;
        }
        return counts;
    }

    private static int getSumOfAllTuples(int[] dices, int expectedTupleSize, int expectedTupleCount)
    {
        int sum = 0;
        int tuplesCounted = 0;
        int[] counts = getValueOccurrences(dices);

        // we do a backward loop to catch the highest values first
        // we must do this because when looking for one pair, we must return the highest if there are two
        for(int i = counts.length -1 ; i>=0; i--)
        {
            if(counts[i] >= expectedTupleSize)
            {
                int diceValue = i+1;
                if(tuplesCounted < expectedTupleCount)
                {
                    sum += diceValue * expectedTupleSize;
                    tuplesCounted++;
                }
            }
        }

        // this check is needed in the case we look for two pairs but find only one
        if(tuplesCounted == expectedTupleCount)
        {
            return sum;
        }
        return 0;
    }
}
