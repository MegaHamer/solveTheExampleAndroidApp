package aa.churkin.solvetheexample

import aa.churkin.solvetheexample.databinding.ActivityMainBinding
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Timer
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //btns
        binding.buttonStart.setOnClickListener {start()}
        binding.buttonLeft.setOnClickListener {check(false)}
        binding.buttonRight.setOnClickListener {check(true)}
        //btns actives
        binding.buttonLeft.isEnabled = false
        binding.buttonRight.isEnabled = false
        //text
        TotalSolvesExamples = savedInstanceState?.getInt("Total") ?: TotalSolvesExamples
        RightSolvesExamples = savedInstanceState?.getInt("Right") ?: RightSolvesExamples
        WrongSolvesExmples = TotalSolvesExamples-RightSolvesExamples
        //background
        color =  savedInstanceState?.getInt("color")?:color
        binding.main.background = getDrawable(color)
        //example
        var exe = savedInstanceState?.getSerializable("ex") ?: ex
        ex = exe as Example
        //time


        UpdateText()
    }
    var color = R.color.white

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Total",TotalSolvesExamples)
        outState.putInt("Right",RightSolvesExamples)
        outState.putSerializable("ex",ex)

        outState.putInt("color",color)

        //time
        outState.putInt("min",minimum)
        outState.putInt("max",maximum)
    }
    private var TotalSolvesExamples = 0
    private var RightSolvesExamples = 0
    private var WrongSolvesExmples = 0
    private var ex = Example()
    private var minimum = 0
    private var maximum = 0
    private var average = 0.00
    private fun start(){
        //btns active
        binding.buttonLeft.isEnabled = true
        binding.buttonRight.isEnabled = true
        binding.buttonStart.isEnabled = false
        //color bg white
        color = R.color.white
        binding.main.background = getDrawable(color)
        //start timer


        ex.generateNewExample()
        UpdateText()
    }
    private fun check(userAnswer:Boolean){
        //btns active
        binding.buttonLeft.isEnabled = false
        binding.buttonRight.isEnabled = false
        binding.buttonStart.isEnabled = true
        //totalSolves
        TotalSolvesExamples++
        //if correct
        if(ex.CheckAnswer(userAnswer)){
            color = R.color.RightAnswerColor
            RightSolvesExamples++
        }
        else{
            color = R.color.WrongAnswerColor
            WrongSolvesExmples = TotalSolvesExamples-RightSolvesExamples
        }
        binding.main.background = getDrawable(color)

        //end timer

        //min max

        //average

        UpdateText()
    }
    private fun UpdateText(){
        binding.textFirstNumber.text = ex.firstNumber.toString()
        binding.textSecondNumber.text = ex.secondNumber.toString()
        binding.textPlus.text = ex.operator
        binding.textCountTotalSolvesExamples.text = TotalSolvesExamples.toString()
        binding.textCountRightSolves.text = RightSolvesExamples.toString()
        binding.textCountWrongSolves.text = WrongSolvesExmples.toString()
        if (TotalSolvesExamples == 0 || (RightSolvesExamples/1.0/TotalSolvesExamples*100.0) == 0.0) binding.textPercent.text = "0.00%" else{
            val df = DecimalFormat("#.##")
            //df.roundingMode = RoundingMode
            val roundoff = df.format(RightSolvesExamples/1.0/TotalSolvesExamples*100.0)
            binding.textPercent.text = "${roundoff}%"
        }
        val df = DecimalFormat("#.##")
        val roundoff = df.format(ex.answer)
        binding.textThirdNumber.text = "${roundoff}"

        //binding.textCountMinimum.text =
        //binding.textMaximum.text=
        //binding.textCountAverage =
    }
}


