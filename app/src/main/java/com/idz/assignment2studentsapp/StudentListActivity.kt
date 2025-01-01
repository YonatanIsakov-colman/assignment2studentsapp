package com.idz.assignment2studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.assignment2studentsapp.Adapter.StudentsRecyclerAdapter
import com.idz.assignment2studentsapp.Model.Model
import com.idz.assignment2studentsapp.Model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentListActivity : AppCompatActivity() {

    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val moveToNewStudentButton: Button = findViewById(R.id.students_list_button)

        moveToNewStudentButton.setOnClickListener{
            val intent = Intent(this,NewStudentActivity::class.java)
            startActivity(intent)
        }

        students = Model.shared.students

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@StudentListActivity, StudentDetailsActivity::class.java)
                intent.putExtra("studentPosition", position)
                startActivity(intent)
            }

            override fun onItemClick(student: Student?) {

            }
        }
        recyclerView.adapter = adapter
    }




}