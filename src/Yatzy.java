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

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllDicesWithValues(dices,1,2,3,4,5,6);
    }

    public static int yatzy(int... dices)
    {
        int[] counts = getValueOccurrences(dices);
        for(int i = 0; i != 6; i++)
        {
            if(counts[i] == 5)
            {
                return 50;
            }
        }
        return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllDicesWithValues(dices,1);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllDicesWithValues(dices,2);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllDicesWithValues(dices,3);
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllTuples(dices,2, 1);
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllTuples(dices,2, 2);
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllTuples(dices,4, 1);
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        return getSumOfAllTuples(dices,3, 1);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        int smallStraightSum = 15;
        return getSumOfAllTuples(dices,1,5) == smallStraightSum ? smallStraightSum : 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        int largeStraightSum = 20;
        return getSumOfAllTuples(dices,1,5) == largeStraightSum ? largeStraightSum : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] dices = new int[] {d1, d2, d3, d4, d5};
        int[] counts = getValueOccurrences(dices);

        boolean pairFound = false;
        int pairValue = 0;
        boolean tripleFound = false;
        int tripleValue = 0;

        for (int i = 0; i < counts.length; i ++)
        {
            if (counts[i] == 2)
            {
                pairFound = true;
                pairValue = i + 1;
            }
            if (counts[i] == 3)
            {
                tripleFound = true;
                tripleValue = i+1;
            }
        }


        if (pairFound && tripleFound)
            return pairValue * 2 + tripleValue * 3;
        else
            return 0;
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

    private static int getSumOfAllTuples(int[] dices, int tupleSize, int expectedTupleCount)
    {
        int sum = 0;
        int tuplesCounted = 0;
        int[] counts = getValueOccurrences(dices);

        // we do a backward loop to catch the highest values first
        // we must do this because when looking for one pair, we must return the highest if there are two
        for(int i = counts.length -1 ; i>=0; i--)
        {
            if(counts[i] >= tupleSize)
            {
                int diceValue = i+1;
                if(tuplesCounted < expectedTupleCount)
                {
                    sum += diceValue * tupleSize;
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
