package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    //Initializing buttons
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    //Initializing inputs
    private lateinit var hws: EditText
    private lateinit var mid1: EditText
    private lateinit var mid2: EditText
    private lateinit var ppt: EditText
    private lateinit var gp: EditText
    private lateinit var fp: EditText

    //Initializing outputs
    private lateinit var hwData: TextView
    private lateinit var grade: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.button)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)


        hws = findViewById<EditText>(R.id.hw)
        mid1 = findViewById<EditText>(R.id.Midterm1)
        mid2 = findViewById<EditText>(R.id.Midterm2)
        ppt = findViewById<EditText>(R.id.Participation)
        gp = findViewById<EditText>(R.id.GroupPres)
        fp = findViewById<EditText>(R.id.FinalProject)

        hwData = findViewById<TextView>(R.id.textView)
        grade = findViewById<TextView>(R.id.textView8)

        val numbers = mutableListOf<Int>()
        button2.setOnClickListener{
            if(numbers.size<=5){
                val a =hws.text.toString().toInt()
                numbers.add(a)
                hws.text.clear()
                var text =""
                var i=1
                for(a in numbers){
                    text += "Homework"+i+'='+ a.toString()+", "
                    i++
                }
                hwData.setText(text)
            }
        }

        button1.setOnClickListener{
            numbers.clear()
            hwData.text="Homeworks"
        }

        /*
         Participation and attendance (10%)
         Homeworks (20%) Group Presentation (10%)
         Midterm Exams (10% + 20%)
         Final Project (30%
         */
        button3.setOnClickListener{
            //as specified in the homework, all values are 100 BY DEFAULT
            var midT1 = mid1.text.toString().toDoubleOrNull() ?: 100.0
            var midT2 = mid2.text.toString().toDoubleOrNull() ?: 100.0
            var partic = ppt.text.toString().toDoubleOrNull() ?: 100.0
            var grPres = gp.text.toString().toDoubleOrNull() ?: 100.0
            var finProj = fp.text.toString().toDoubleOrNull() ?: 100.0
            var sum = 0
            var hwMean = 100

            if(numbers.size != 0){
                for(a in numbers){
                    sum+=a
                }
                hwMean = sum/numbers.size
            }


            //ensure that inputs are in range. They are already signed=> only check for >100
            if(midT1>100) midT1=100.0
            if(midT2>100) midT1=100.0
            if(partic>100) midT1=100.0
            if(grPres>100) midT1=100.0
            if(finProj>100) midT1=100.0
            if(hwMean>100) midT1=100.0

            //round the grade to nearest hundreth
            val finalGrade = 0.1*partic + 0.2*hwMean + 0.1*grPres + 0.1*midT1 + 0.2*midT2 + 0.3*finProj
            val roundedGrade = round(finalGrade*100)/100
            grade.text = roundedGrade.toString()
        }

    }
}