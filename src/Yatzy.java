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
        int[] counts = new int[6];
        for (int dice : dices)
        {
            counts[dice-1]++;
        }

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
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6-at-1] >= 2)
                return (6-at)*2;
        return 0;
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1)
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[_1-1]++;
        tallies[_2-1]++;
        tallies[d3-1]++;
        tallies[d4-1]++;
        tallies[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i+1) * 4;
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t;
        t = new int[6];
        t[d1-1]++;
        t[d2-1]++;
        t[d3-1]++;
        t[d4-1]++;
        t[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i+1) * 3;
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    private static int getSumOfAllDicesWithValues(int[] dices, int... values)
    {
        int sum = 0;
        for (int i = 0; i < dices.length; i++)
        {
            for(int value : values) {
                if (dices[i] == value)
                {
                    sum += value;
                }
            }
        }
        return sum;
    }
}
