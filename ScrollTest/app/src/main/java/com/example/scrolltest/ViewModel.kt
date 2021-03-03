package com.example.scrolltest

class ViewModel {



    /*

    Well i was trying to find the relation between two variables like i had to vary button position as the height changes so a direct relation between
    to things header Height and button position, so i thought to use calculus rate of change of button position with respect to Header height (a very practical application of
    calculus in our everyday life as in developing apps too), but the issue was i could only find derivative if the button position is known but it was something i had to find out
    so on second thought i saw there is a Linear relation between two things i.e Header height and button position, as one varies the second has to also vary in the same fashion
    but in a proportionate amount, then i managed to make two Linear equation one for the starting positions of both Header Height and Button X- Position and ending positions of
    the same.

    i.e

     ax+by=e    First linear equation
     cx+dy=f    Second linear equation


     *
     * a = initialKnownValueToVary
     * b = 1 (Always)
     * e = initialKnownHeight
     *
     *
     * c = finalKnownValue
     * d = 1 (Always)
     * f = finalKnownHeight
     *
     *
     * * we solve the linear system
     * ax+by=e
     * cx+dy=f
     *
     * Let's solve it with help of crammer's rule:
     *
     * First we find the determinant by using cross multiplication of the above two linear equations
     *
     * Note: Gradient and Coefficient will always different for different initial and final known values, thus we need to calculate it every time we have
     *       different values!!
     */

   private fun calculateGrAndCo(initialKnownValueToVary:Float,  initialKnownHeight:Float,    finalKnownValue:Float, finalKnownHeight:Float) : Pair<Float, Float> {
        val determinant = initialKnownValueToVary*1 - 1*finalKnownValue; //(a*d - b*c)
        if(determinant != 0.0F) {
            val x = (initialKnownHeight*1 - 1*finalKnownHeight)/determinant;
            val y = (initialKnownValueToVary*finalKnownHeight - initialKnownHeight*finalKnownValue)/determinant;
            return  Pair(x, y)
        }
        return Pair(0.0F, 0.0F);
    }


    /*
       Now we will store the above calculated gradient and coefficient in class variable, and will place it in above two equations to find the button
       X- position as the Height will vary let's say from 200dp to 80dp as we scroll the recyclerview

     */

    lateinit var graCof:Pair<Float,Float>

    fun storeGrCoValForButton(initialKnownValueToVary:Float,  initialKnownHeight:Float,    finalKnownValue:Float, finalKnownHeight:Float){
        this.graCof = calculateGrAndCo(initialKnownValueToVary,initialKnownHeight , finalKnownValue, finalKnownHeight)
    }
    /*
    we need to calculate a(button position) here , because this should vary in same proportion as e(in our case header height will vary) varies to satisfy the equation,thus
    we will find the value of a as follows
    ax+by=e
    ax = e-by
    a = e-by/x
    we will replace e with the current height of header as we scroll it will vary
     */
    fun calculateCurrentButtonPosition(currentHeightOfHeader:Float):Float{
        return  (currentHeightOfHeader - this.graCof.second * 1)/this.graCof.first
    }
}