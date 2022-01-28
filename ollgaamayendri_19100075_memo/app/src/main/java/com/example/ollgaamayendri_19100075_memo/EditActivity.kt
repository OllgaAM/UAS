package com.example.ollgaamayendri_19100075_memo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class EditActivity : AppCompatActivity() {
    private var etText: EditText? = null
    private var memo: Memo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        etText = findViewById(R.id.etText)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCencel)
        val bundle = intent.extras
        if (bundle != null) {
            memo = bundle["MEMO"] as Memo?
            if (memo != null) {
                etText.setText(memo!!.text)
            }
        }
        btnSave.setOnClickListener { onSaveClicked() }
        btnCancel.setOnClickListener { finish() }
    }

    fun onSaveClicked() {
        val databaseAcces = DatabaseAcces.getInstance(this)
        databaseAcces.open()
        if (memo == null) {
            val temp = Memo()
            temp.text = etText!!.text.toString()
            databaseAcces.save(temp)
        } else {
            memo!!.text = etText!!.text.toString()
            databaseAcces.update(memo)
        }
        databaseAcces.close()
        finish()
    }
}