package com.lg316.infoapp.data

object ItemRepository {
    private val items = mutableListOf<Item>()
    private var nextId = 1L

    init {
        add("Bienvenido", "Este es un elemento de ejemplo. Puedes editarlo o eliminarlo.")
        add("Segundo elemento", "Toca un elemento para ver su detalle y gestionarlo.")
    }

    fun getAll(): List<Item> = items.toList()

    fun getById(id: Long): Item? = items.find { it.id == id }

    fun add(title: String, description: String): Item {
        val item = Item(nextId++, title, description)
        items.add(0, item)
        return item
    }

    fun update(id: Long, title: String, description: String): Boolean {
        val item = getById(id) ?: return false
        item.title = title
        item.description = description
        return true
    }

    fun delete(id: Long): Boolean = items.removeIf { it.id == id }
}
