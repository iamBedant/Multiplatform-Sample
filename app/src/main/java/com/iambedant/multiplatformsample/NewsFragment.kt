package com.iambedant.multiplatformsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

    }

    private val adapter =
        NewsAdapter(listOf()) { clickItem ->
            viewModel.bookmarkedArticle.value = clickItem
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvNews.layoutManager = GridLayoutManager(activity, 1)
        rvNews.adapter = adapter
        viewModel.headlines.observe(this, Observer {
            adapter.addItems(it)
        })
    }
}
