package aa.churkin.solvetheexample

import java.io.Serializable
import kotlin.random.Random

public class Example : Serializable {
    class Example(){}
    public var firstNumber = 0
    public var secondNumber = 0
    public var operator = "+"
    public var answer = 0.0

    public fun generateNewExample(){
        var listOper = listOf("+","-","/","*")
        operator = listOper[Random.nextInt(0,listOper.size)]
        firstNumber = Random.nextInt(10,99)
        secondNumber = Random.nextInt(10,99)
        answer = when (operator){
            "+"->{(firstNumber+secondNumber).toDouble()}
            "-"->{(firstNumber-secondNumber).toDouble()}
            "/"->{(firstNumber*1.0/secondNumber).toDouble()}
            "*"->{(firstNumber*secondNumber).toDouble()}
            else->0.0
        }
    }
    public fun CheckAnswer(userAnswer:Boolean):Boolean{
        var rightAnswer = when (operator){
            "+"->{(firstNumber+secondNumber).toDouble()}
            "-"->{(firstNumber-secondNumber).toDouble()}
            "/"->{(firstNumber * 1.0/secondNumber).toDouble()}
            "*"->{(firstNumber*secondNumber).toDouble()}
            else->0.0
        }
        return ((rightAnswer == answer) == userAnswer )
    }
}