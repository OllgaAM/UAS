package com.example.ollgaamayendri_19100075_memo

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var databaseAcces: DatabaseAcces? = null
    private var memos: kotlin.collections.List<Memo?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseAcces = DatabaseAcces.getInstance(this)
        listView = findViewById(R.id.listView)
        val btnBuat = findViewById<Button>(R.id.btnBuat)
        btnBuat.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        databaseAcces!!.open()
        memos = databaseAcces!!.allMemos
        databaseAcces!!.close()
        val adapter: MemoAdapter = MemoAdapter(this, memos)
        listView!!.adapter = adapter
    }

    private inner class MemoAdapter internal constructor(
        context: Context,
        objects: kotlin.collections.List<Memo?>?
    ) :
        ArrayAdapter<Memo?>(context, 0, objects!!) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.layout_list_item, parent, false)
            }
            val btnEdit = convertView!!.findViewById<Button>(R.id.btnEdit)
            val btnDelete = convertView.findViewById<Button>(R.id.btnDelete)
            val txtDate = convertView.findViewById<TextView>(R.id.txtDate)
            val txtMemo = convertView.findViewById<TextView>(R.id.txtMemo)
            val memo = memos!![position]
            txtDate.text = memo!!.date
            txtMemo.text = memo.shortText
            btnEdit.setOnClickListener {
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("MEMO", memo)
                startActivity(intent)
            }
            btnDelete.setOnClickListener {
                databaseAcces!!.open()
                databaseAcces!!.delete(memo)
                databaseAcces!!.close()
                val adapter = listView!!.adapter as ArrayAdapter<Memo>
                adapter.remove(memo)
                adapter.notifyDataSetChanged()
            }
            return convertView
        }
    }
}