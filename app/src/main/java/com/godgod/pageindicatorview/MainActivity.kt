package com.godgod.pageindicatorview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.godgod.pageindicator.PageIndicatorView
import com.godgod.pageindicator.connectTo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager2 = findViewById<ViewPager2>(R.id.vp)

        viewPager2.apply {
            adapter = SampleAdapter()
        }.run {
            (adapter as SampleAdapter).apply {
                setAll(listOf(Color.BLACK, Color.BLUE, Color.DKGRAY, Color.RED))
            }.run {
                notifyDataSetChanged()
            }
        }

        viewPager2.connectTo(findViewById<PageIndicatorView>(R.id.cv_page_indicator).apply {
            setIndicatorCount(4)
        })
    }
}


class SampleAdapter() : RecyclerView.Adapter<SampleViewHolder>() {

    private val items: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_sample, parent, false)
        return SampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setAll(list: List<Int>) {
        this.items.clear()
        this.items.addAll(list)
    }
}

class SampleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(color: Int) {
        view.findViewById<ViewGroup>(R.id.root).setBackgroundColor(color)
    }
}