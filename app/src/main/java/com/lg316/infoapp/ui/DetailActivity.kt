package com.lg316.infoapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lg316.infoapp.R
import com.lg316.infoapp.data.ItemRepository
import com.lg316.infoapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM_ID = "extra_item_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private var itemId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        itemId = intent.getLongExtra(EXTRA_ITEM_ID, -1L)

        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra(FormActivity.EXTRA_ITEM_ID, itemId)
            startActivity(intent)
        }

        binding.buttonDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage(R.string.confirm_delete)
                .setPositiveButton(R.string.yes) { _, _ ->
                    ItemRepository.delete(itemId)
                    Toast.makeText(this, R.string.delete_item, Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton(R.string.no, null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        val item = ItemRepository.getById(itemId)
        if (item == null) {
            finish()
            return
        }
        binding.textTitle.text = item.title
        binding.textDescription.text = item.description
    }
}
