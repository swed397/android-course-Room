package com.android.course.room.present.add

import android.view.View
import android.widget.AdapterView

class DropDownGenresOnItemSelectedListener(
    private val data: List<String>,
    private val onSelect: (genre: String) -> Unit
) : AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onSelect.invoke(data[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}