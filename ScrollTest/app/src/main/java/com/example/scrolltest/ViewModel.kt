package com.example.scrolltest

class ViewModel {

    

   private fun calculateGrAndCo(initialKnownValueToVary:Float,  initialKnownHeight:Float,    finalKnownValue:Float, finalKnownHeight:Float) : Pair<Float, Float> {
        val determinant = initialKnownValueToVary*1 - 1*finalKnownValue; 
        if(determinant != 0.0F) {
            val x = (initialKnownHeight*1 - 1*finalKnownHeight)/determinant;
            val y = (initialKnownValueToVary*finalKnownHeight - initialKnownHeight*finalKnownValue)/determinant;
            return  Pair(x, y)
        }
        return Pair(0.0F, 0.0F);
    }


   
    lateinit var graCof:Pair<Float,Float>

    fun storeGrCoValForButton(initialKnownValueToVary:Float,  initialKnownHeight:Float,    finalKnownValue:Float, finalKnownHeight:Float){
        this.graCof = calculateGrAndCo(initialKnownValueToVary,initialKnownHeight , finalKnownValue, finalKnownHeight)
    }
  
    fun calculateCurrentButtonPosition(currentHeightOfHeader:Float):Float{
        return  (currentHeightOfHeader - this.graCof.second * 1)/this.graCof.first
    }
}