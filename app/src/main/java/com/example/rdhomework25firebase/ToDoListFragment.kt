package com.example.rdhomework25firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class ToDoListFragment: Fragment() {
    private var list : ListView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.to_do_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: FloatingActionButton = view.findViewById(R.id.to_do_fragment_fab)
        list = view.findViewById(R.id.to_do_list)
        fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddToDoFragment())
                .addToBackStack("to_do_list_fragment")
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        val database = FirebaseDatabase.getInstance()
        val target = database.reference.child(account?.id?:"unknown_account")
            .child("to_do_list")

        target.get().addOnCompleteListener {
            val toDoList = mutableListOf<String>()
            if(it.isSuccessful){
                it.result.children.forEach{data->
                    val toDo = data.getValue(String::class.java)?:"Error"
                    toDoList.add(toDo)
                }
                val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1,toDoList)
                list?.adapter = adapter
            }
        }
    }
}