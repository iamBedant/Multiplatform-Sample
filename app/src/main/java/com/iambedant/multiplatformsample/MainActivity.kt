package com.iambedant.multiplatformsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.sqldelight.android.AndroidSqliteDriver
import data.NewsArticle
import kotlinx.android.synthetic.main.activity_main.*
import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView
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

    private val presenter by lazy { MainPresenter(this, DataRepositoryImpl()) }
    private lateinit var viewModel: MainViewModel

    private val newsFragment: Fragment = NewsFragment.newInstance()
    private val bookmarkFragment: Fragment = BookmarkFragment.newInstance()
    private val fragmentManager = supportFragmentManager
    var active = newsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        presenter.loadTopHeadlines()
    }

    private fun setupUi() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter.initDatabase(AndroidSqliteDriver(Database.Schema, this, "user.db"))
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

