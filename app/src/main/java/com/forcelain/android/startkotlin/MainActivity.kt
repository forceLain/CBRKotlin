package com.forcelain.android.startkotlin

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.forcelain.android.startkotlin.models.ValCurs
import com.forcelain.android.startkotlin.models.Valute
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*


class MainActivity : AppCompatActivity() {

    var currencyList: List<Valute> = listOf()
    var adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        var myApp = application as App
        var service: CBR = myApp.service as CBR;
        service.daily
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ValCurs>() {
                    override fun onNext(t: ValCurs?) {
                        currencyList = t?.valute as List<Valute>
                        adapter.valuteList = currencyList
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        var menuItem = menu?.findItem(R.id.action_search)
        var searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null && query.length > 0) {
                    var newList = currencyList.filter {
                        it.name.contains(query, true) || it.charCode.contains(query, true)
                    }
                    adapter.valuteList = newList
                } else {
                    adapter.valuteList = currencyList
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    inner class MyAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

        var valuteList: List<Valute> = listOf()
            set(value) {
                field = value.sortedWith(currComparator)
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder{
            var view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
            return CurrencyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return valuteList.size;
        }

        override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
            val currency = valuteList[position]
            val flagRes = currency.findFlagRes()
            if (flagRes > 0) {
                holder.image.setImageResource(flagRes)
            } else {
                holder.image.setImageDrawable(null)
            }
            val nominal = currency.nominal
            val name = currency.name
            val value = currency.value
            holder.name.text = "$nominal $name"

            holder.value.text = "$value \u20BD"

            holder.itemView.setOnClickListener {
                var dialog = ConvertFragment.create(currency.nominal, currency.value, flagRes, currency.charCode);
                dialog.show(supportFragmentManager, "converter-dialog")
            }
        }

        private val currComparator: Comparator<Valute> = Comparator { lhs, rhs ->
            if (lhs.charCode.equals("USD", true) || lhs.charCode.equals("EUR", true)) {
                return@Comparator -1;
            }
            if (rhs.charCode.equals("USD", true) || rhs.charCode.equals("EUR", true)) {
                return@Comparator 1;
            }
            return@Comparator lhs.name.compareTo(rhs.name);
        }

    }
}



class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val value: TextView = itemView.findViewById(R.id.value) as TextView
    val name: TextView = itemView.findViewById(R.id.name) as TextView
    val image: ImageView = itemView.findViewById(R.id.image) as ImageView
}