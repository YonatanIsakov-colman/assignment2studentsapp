package com.idz.assignment2studentsapp

import android.annotation.SuppressLint
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

class EditStudentActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentPosition:Int = intent.getIntExtra("studentPosition",0)
        val student = Model.shared.students[studentPosition]

        val saveButton: Button = findViewById(R.id.edit_student_save_button)
        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        val deleteButton: Button = findViewById(R.id.edit_student_delete_button)
        val nameTextField: EditText = findViewById(R.id.edit_student_name_text_field)
        val idTextField: EditText = findViewById(R.id.edit_student_id_text_field)
        val phoneTextField: EditText = findViewById(R.id.edit_student_phone_text_field)
        val addressTextField: EditText = findViewById(R.id.edit_student_address_text_field)
        val checkBox = findViewById<CheckBox>(R.id.edit_student_check_box)
        val savedTextField: TextView = findViewById(R.id.edit_student_success_saved_text_view)
        val isCheckedTextView: TextView = findViewById(R.id.edit_textView5)

        if (checkBox.isChecked) {
            isCheckedTextView.visibility = View.VISIBLE
        } else {
            isCheckedTextView.visibility = View.INVISIBLE
        }

        nameTextField.setText(student.name)
        idTextField.setText(student.id)
        phoneTextField.setText(student.phone)
        addressTextField.setText(student.address)
        checkBox.isChecked = student.isChecked


        deleteButton.setOnClickListener{
            Model.shared.students.removeAt(studentPosition)
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }


        saveButton.setOnClickListener {
            student.name = nameTextField.text.toString()
            student.id = idTextField.text.toString()
            student.phone = phoneTextField.text.toString()
            student.address = addressTextField.text.toString()
            student.isChecked = checkBox.isChecked

//            val newStudent = Student(name, id, phone, address, isChecked)
//
//            Model.shared.students[studentPosition]= newStudent

            savedTextField.text = "${student.name}  is saved...!!!"
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            //finish()
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }
    }
}