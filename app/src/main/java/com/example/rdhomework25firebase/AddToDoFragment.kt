package com.example.rdhomework25firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class AddToDoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_to_do_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputToDo: EditText = view.findViewById(R.id.input_to_do)
        val inputDescription: EditText  = view.findViewById(R.id.input_to_do_desc)
        val doneBtn: Button = view.findViewById(R.id.done)

        val database = FirebaseDatabase.getInstance()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        doneBtn.setOnClickListener {
            val info = "${inputToDo.text},${inputDescription.text}"
            val target = database.reference.child(account?.id?:"unknown_account")
                .child("to_do_list")
                .child(UUID.randomUUID().toString())
            target.setValue(info)
                .addOnCompleteListener {
                    parentFragmentManager.popBackStack()
                }
        }
    }
}