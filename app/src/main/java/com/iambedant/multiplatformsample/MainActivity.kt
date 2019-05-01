package com.iambedant.multiplatformsample

import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.sqldelight.android.AndroidSqliteDriver
import data.NewsArticle
import kotlinx.android.synthetic.main.activity_main.*
import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView
import storage.Database

class MainActivity : AppCompatActivity(),MainView {
    override fun displayHeadLines(headlines: List<NewsArticle>) {
        rvNews.adapter = NewsAdapter(headlines) { clickItem -> Toast.makeText(this,clickItem.title,Toast.LENGTH_SHORT).show() }
    }

    override fun showLoader() {
        progressBar.show()
        rvNews.hide()
    }

    override fun hideLoader() {
        progressBar.hide()
        rvNews.show()
    }

    override fun showError(error: String) {
        error.let {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    private val presenter by lazy { MainPresenter(this, DataRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNews.layoutManager = GridLayoutManager(this, 1)
        rvNews.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        presenter.initDatabase(AndroidSqliteDriver(Database.Schema, this, "user.db"))
        presenter.loadTopHeadlines()
    }


}
