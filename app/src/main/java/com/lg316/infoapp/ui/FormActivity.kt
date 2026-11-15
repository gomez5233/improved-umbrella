package com.lg316.infoapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lg316.infoapp.R
import com.lg316.infoapp.data.ItemRepository
import com.lg316.infoapp.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM_ID = "extra_item_id"
    }

    private lateinit var binding: ActivityFormBinding
    private var itemId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        itemId = intent.getLongExtra(EXTRA_ITEM_ID, -1L)
        val existing = if (itemId != -1L) ItemRepository.getById(itemId) else null
        if (existing != null) {
            binding.toolbar.title = getString(R.string.edit_item_title)
            binding.editTitle.setText(existing.title)
            binding.editDescription.setText(existing.description)
        } else {
            binding.toolbar.title = getString(R.string.new_item)
        }

        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text?.toString()?.trim().orEmpty()
            val description = binding.editDescription.text?.toString()?.trim().orEmpty()
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, R.string.field_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (existing != null) {
                ItemRepository.update(itemId, title, description)
            } else {
                ItemRepository.add(title, description)
            }
            finish()
        }
    }
}
