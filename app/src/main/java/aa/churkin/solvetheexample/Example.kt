package aa.churkin.solvetheexample

import kotlin.random.Random

class Example {
    class Example(){}
    public var firstNumber = 0
    public var secondNumber = 0
    public var operator = "+"

    public fun generateNewExample(){
        var listOper = listOf("+","-","/","*")
        operator = listOper[Random.nextInt(0,listOper.size)]
        firstNumber = Random.nextInt(10,99)
        secondNumber = Random.nextInt(10,99)
        if (operator == "/")
            while ((firstNumber % secondNumber != 0) ||
                (firstNumber == secondNumber) ||
                (firstNumber == 0) ||
                (secondNumber == 0)
            ){
            firstNumber = Random.nextInt(10,99)
            secondNumber = Random.nextInt(10,99)
        }
    }
    public fun CheckAnswer(userAnswer:Int):Boolean{
        var rightAnswer = when (operator){
            "+"->{firstNumber+secondNumber}
            "-"->{firstNumber-secondNumber}
            "/"->{firstNumber/secondNumber}
            "*"->{firstNumber*secondNumber}
            else->0
        }
        return (rightAnswer == userAnswer)
    }
}