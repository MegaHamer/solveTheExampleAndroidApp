package aa.churkin.solvetheexample

import aa.churkin.solvetheexample.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {start()}
        binding.buttonStart.isEnabled = true
        binding.buttonCheck.setOnClickListener {check()}
        binding.buttonCheck.isEnabled = false

        TotalSolvesExamples = savedInstanceState?.getInt("Total") ?: TotalSolvesExamples
        RightSolvesExamples = savedInstanceState?.getInt("Right") ?: RightSolvesExamples
        WrongSolvesExmples = TotalSolvesExamples-RightSolvesExamples

        binding.inputUserAnswer.setText(savedInstanceState?.getString("user") ?: "")

        var exe = savedInstanceState?.getSerializable("ex") ?: ex
        ex = exe as Example

        UpdateText()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Total",TotalSolvesExamples)
        outState.putInt("Right",RightSolvesExamples)
        outState.putSerializable("ex",ex)
        outState.putString("user",binding.inputUserAnswer.getText().toString())
    }
    private var TotalSolvesExamples = 0
    private var RightSolvesExamples = 0
    private var WrongSolvesExmples = 0
    private var ex = Example()

    private fun start(){
        //enable on checkbutton
        binding.buttonCheck.isEnabled = true
        //enable off startbutton
        binding.buttonStart.isEnabled = false
        //color bg white
        binding.main.background = getDrawable(R.color.white)

        ex.generateNewExample()
        UpdateText()
    }
    private fun check(){
        //enable on startbutton
        binding.buttonStart.isEnabled = true
        //enable off checkbutton
        binding.buttonCheck.isEnabled = false
        //get user answer and compare with solve
        var userAnswer = binding.inputUserAnswer.getText().toString()
        if (userAnswer=="") userAnswer ="0";
        //totalSolves
        TotalSolvesExamples++
        //if correct
        if(ex.CheckAnswer(userAnswer.toInt())){
            binding.main.background = getDrawable(R.color.RightAnswerColor)
            RightSolvesExamples++
        }
        else{
            binding.main.background = getDrawable(R.color.WrongAnswerColor)
            WrongSolvesExmples = TotalSolvesExamples-RightSolvesExamples
        }
        binding.inputUserAnswer.setText("")

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
    }
}


