package dev.stoneworks.a2dolist.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dev.stoneworks.a2dolist.R
import dev.stoneworks.a2dolist.databinding.ActivityNewTaskBinding
import dev.stoneworks.a2dolist.datasource.TaskDataSource
import dev.stoneworks.a2dolist.extensions.format
import dev.stoneworks.a2dolist.extensions.text
import dev.stoneworks.a2dolist.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

        private lateinit var binding: ActivityNewTaskBinding

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                binding = ActivityNewTaskBinding.inflate(layoutInflater)
                setContentView(binding.root)

                if (intent.hasExtra(TASK_ID)) {
                        val taskId = intent.getIntExtra(TASK_ID, 0)
                        TaskDataSource.findById(taskId)?.let {
                                binding.tilTitle.text = it.title
                                binding.tilDate.text = it.date
                                binding.tilTime.text = it.title
                        }
                }

                insertListeners()

        }

        private fun insertListeners() {
                binding.tilDate.editText?.setOnClickListener{
                        val datePicker = MaterialDatePicker.Builder
                                                .datePicker()
                                                .setTitleText(getString(R.string.new_task_pick_date))
                                                .build()
                        datePicker.addOnPositiveButtonClickListener {
                                val timeZone = TimeZone.getDefault()
                                val offset = timeZone.getOffset(Date().time) * -1
                                binding.tilDate.text = Date(it + offset).format()
                        }
                        datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")

                }

                binding.tilTime.editText?.setOnClickListener{
                        val timePicker = MaterialTimePicker.Builder()
                                .setTimeFormat(TimeFormat.CLOCK_24H)
                                .setTitleText(getString(R.string.new_task_pick_time)).build()
                        timePicker.addOnPositiveButtonClickListener{
                                val minute = if(timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                                val hour = if(timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                                binding.tilTime.text = "$hour:$minute"
                        }
                        timePicker.show(supportFragmentManager, "TIME_PICKER_TAG")
                }

                binding.btnCancel.setOnClickListener{
                        finish()
                }

                binding.btnCreate.setOnClickListener {
                        val task = Task(
                                title = binding.tilTitle.text,
                                date = binding.tilDate.text,
                                hour = binding.tilTime.text,
                                id = intent.getIntExtra(TASK_ID, 0)
                        )
                        TaskDataSource.insertTask(task)

                        setResult(Activity.RESULT_OK)
                        finish()
                }
        }

        companion object {
                const val TASK_ID = "task_id"
        }

}