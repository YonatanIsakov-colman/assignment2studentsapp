package com.idz.assignment2studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.assignment2studentsapp.Model.Model
import com.idz.assignment2studentsapp.Model.Student

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val studentPosition:Int = intent.getIntExtra("studentPosition",0)
        val student = Model.shared.students[studentPosition]

        val nameTextView = findViewById<TextView>(R.id.student_details_name_text_view)
        val idTextView = findViewById<TextView>(R.id.student_details_id_text_view)
        val phoneTextView = findViewById<TextView>(R.id.student_details_phone_text_view)
        val addressTextView = findViewById<TextView>(R.id.student_details_address_text_view)
        val checkBox = findViewById<CheckBox>(R.id.student_details_check_check_box)
        val editButton = findViewById<Button>(R.id.student_details_edit_button)

        nameTextView.text = "Name: " + student.name
        idTextView.text = "ID: " + student.id
        phoneTextView.text ="Phone: " + student.phone
        addressTextView.text ="Address: " + student.address
        checkBox.isChecked = student.isChecked
        if(checkBox.isChecked){
            checkBox.text = "Checked"
        }
        editButton.setOnClickListener{
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentPosition",studentPosition)
            startActivity(intent)
        }
    }

}