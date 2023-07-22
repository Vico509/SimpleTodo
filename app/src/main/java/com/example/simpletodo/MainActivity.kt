package com.example.simpletodo

import org.apache.commons.io.FileUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongCliked(position: Int) {
                //1.remove item from the list
                listOfTasks.removeAt(position)
                //2.Notify the adapter that our data set change
                adapter.notifyDataSetChanged()
            }

        }

        // 1. Let's detect when user click an the add button
//        findViewById<Button>(R.id.button).setOnClickListener {
//            // code in here is going to be executed when the user clicks on a button
//            //Log.i(tag:"Caren", msg:"User clicked on button")
//        }
        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for a walk")

        loadItems()
        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks,onLongClickListener)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the button and input field, so that the user can enter a task
        val inputTextField = findViewById<EditText>(R.id.addTaskFile)
        //Get a reference to the button
        // and then set an onclick Listener
        findViewById<Button>(R.id.button).setOnClickListener {
            // 1. the text the user has inputted into @id/andTaskField
            val userInputtedTask = findViewById<EditText>(R.id.addTaskFile).text.toString()
            // 2. Add the string to our list of tasks: listOfTasks
            listOfTasks.add(userInputtedTask)

            // Notify the adapter
            adapter.notifyItemInserted(listOfTasks.size -1)


            // 3. Reset text field
            inputTextField.setText("")
            saveItems()

        }
    }

    //save the data that the user are inputted
    // save data By writting and reading from a file

    // Get the file we neeed
    fun getDataFile() : File {
        return File(filesDir,"data.txt")
    }

    // create a method to get the file we need

    // load the items by reading every line in the datafile
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch (ioException : IOException){
            ioException.printStackTrace()
        }

    }
    // save items by writting then into your datafile
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(),listOfTasks)
        }catch (ioException : IOException){
            ioException.printStackTrace()
        }

    }


}