package com.example.scrolltest

class ViewModel {

    

    lateinit var graCof:Pair<Float,Float>

    fun storeGrCoValForButton(initialKnownValueToVary:Float,  initialKnownHeight:Float,    finalKnownValue:Float, finalKnownHeight:Float){
        this.graCof = calculateGrAndCo(initialKnownValueToVary,initialKnownHeight , finalKnownValue, finalKnownHeight)
    }
    
    fun calculateCurrentButtonPosition(currentHeightOfHeader:Float):Float{
        return  (currentHeightOfHeader - this.graCof.second * 1)/this.graCof.first
    }
}