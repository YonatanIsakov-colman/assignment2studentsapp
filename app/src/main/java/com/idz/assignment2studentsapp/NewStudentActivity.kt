package com.idz.assignment2studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.assignment2studentsapp.Model.Model
import com.idz.assignment2studentsapp.Model.Student

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.edit_student_save_button)
        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        val nameTextField: EditText = findViewById(R.id.edit_student_name_text_field)
        val idTextField: EditText = findViewById(R.id.edit_student_id_text_field)
        val phoneTextField: EditText = findViewById(R.id.edit_student_phone_text_field)
        val addressTextField: EditText = findViewById(R.id.edit_student_address_text_field)
        val checkBox = findViewById<CheckBox>(R.id.edit_student_check_box)
        val savedTextField: TextView = findViewById(R.id.edit_student_success_saved_text_view)
        val isCheckedTextView: TextView = findViewById(R.id.edit_textView5)


        if (checkBox.isChecked) {
            isCheckedTextView.visibility = View.VISIBLE
        } else{
            isCheckedTextView.visibility = View.INVISIBLE
        }

        saveButton.setOnClickListener {
            val name = nameTextField.text.toString()
            val id = idTextField.text.toString()
            val phone = phoneTextField.text.toString()
            val address = addressTextField.text.toString()
            val isChecked = checkBox.isChecked



            val newStudent = Student(name, id, phone, address, isChecked)

            Model.shared.addStudent(newStudent)

            savedTextField.text = "$name is saved...!!!"
        }

        cancelButton.setOnClickListener {
            //finish()
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }
    }
}