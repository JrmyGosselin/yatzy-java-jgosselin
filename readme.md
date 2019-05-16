Kata walkthrough

The given goal is to cleanup/refactor the classes without changing their contracts.

This readme file contains a walkthrough of what I've done to the project.

1 - Harmonizing the programming style

The most obvious (and easy to fix) issue with the Yatzy class and its tests are inconsistencies in coding style.

So I started with this.

To maintain coherence, when I had to choose a style, I picked the style that was the most used within the existing
codebase. In some cases, I had to make personal choices, which I will justify.

   a. Braces
       The most used formatting regarding braces is to have carriage return before opening.

   b. Function order in the Yatzy class
       I personally don't like mixing static methods with non static methods. So I've moved the non-static methods and
       attributes at the top of the java file, and regrouped the rest at the bottom. I think it's more readable that
       way

   c. Annotations
       In most cases, a carriage return follows an annotation, so we will be applying this for every annotation

   d. Function naming
       There are inconsistencies there as well. Some functions respect the most commonly used Java standards - such as
       smallStraight. But others don't : three_of_a_kind for example. I was explicitly told not to change the contract
       so I'm not touching the function names, even though I really want to.

   e. Misc.
       There are other inconsistencies regarding variable naming, if and for blocks, but we will fix it later as we go
       over the functions.

2 - Going over the tests

Before I started fiddling with the code, I needed to take a closer look at the tests to check for mistakes :

   YatzyTest.chance_scores_sum_of_all_dice : this test seems to be correct, however I had to harmonize how the
   assertions were declared

   YatzyTest.yatzy_scores_50 : same as above

   YatzyTest.test_1s : small inconsistency, replacing the first assertion with an assertEquals

   YatzyTest.test_threes : the test looks fine but the name is not consistent with the rest. I'm taking the risk
   of changing the methods for the test class, because it's not technically part of the class contract. I will be using
   the same naming convention as YatzyTest.smallStraight, since it's the most used in the java file (and also because
   it's the most used by java programmers). While I was at it, I changed all the names to be more explicit.

   From now on, I'm using the new names

   YatzyTest.fourOfAKindScoresSumOfQuadruple : there's an assertion that has nothing to do here, because it tests
   another function. I'm moving the assertion to YatzyTest.threeOfAKindScoresSumOfTriple

Turns out there were no mistakes that I could find in the tests. However, I found the assertion strength to be changing
depending on the test case. So, I added a "zero-case" assertion for every test that had none, to allow me some peace of
mind in the refactoring that follows.

3 - Going over the code

Most of the work here was about harmonizing the programming style, changing some very mysteriously named variables and
parameters, and reducing code duplication.

You can check the commit history to find a walkthrough of what I've done. I have been careful to make small commits,
with explicit messages.
