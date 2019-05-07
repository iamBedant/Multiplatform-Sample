package com.iambedant.multiplatformsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.sqldelight.android.AndroidSqliteDriver
import data.NewsArticle
import kotlinx.android.synthetic.main.activity_main.*
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView
import org.kotlin.mpp.mobile.providers.NewsComponent
import org.kotlin.mpp.mobile.providers.MainModule
import org.kotlin.mpp.mobile.providers.inject
import storage.Database


class MainActivity : AppCompatActivity(), MainView {

    override fun displayHeadLines(headlines: List<NewsArticle>) {
        viewModel.updateNewsDataSource(headlines)
    }

    override fun showLoader() {
        progressBar.show()
        container.hide()
    }

    override fun hideLoader() {
        progressBar.hide()
        container.show()
    }

    override fun showError(error: String) {
        error.let {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun displayBookmarkedHeadLines(headlines: List<NewsArticle>) {
        viewModel.updateBookmarkedArticles(headlines)
    }

    private val mainPresenter by lazy { inject<MainPresenter>() }
    private lateinit var viewModel: MainViewModel
    private val newsFragment: Fragment = NewsFragment.newInstance()
    private val bookmarkFragment: Fragment = BookmarkFragment.newInstance()
    private val fragmentManager = supportFragmentManager
    private var active = newsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NewsComponent.addModule(MainModule(this))
        setupUi()
        setupObservers()
        mainPresenter.loadData()
    }

    private fun setupObservers() {
        viewModel.bookmarkedArticle.observe(this, Observer {article->
            article?.let {
                mainPresenter.storeArticle(it)
                Toast.makeText(this, "Storing ${it.title}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupUi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mainPresenter.initDatabase(AndroidSqliteDriver(Database.Schema, this, "articles.db"))
        fragmentManager.beginTransaction().add(R.id.container, bookmarkFragment, "bookmarkFragment").hide(bookmarkFragment).commit()
        fragmentManager.beginTransaction().add(R.id.container, newsFragment, "newsFragment").commit()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_news -> {
                    fragmentManager.beginTransaction().hide(active).show(newsFragment).commit()
                    active = newsFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_bookmark -> {
                    fragmentManager.beginTransaction().hide(active).show(bookmarkFragment).commit()
                    active = bookmarkFragment
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}

