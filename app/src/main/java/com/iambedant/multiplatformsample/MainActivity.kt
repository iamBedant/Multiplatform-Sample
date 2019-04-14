package com.iambedant.multiplatformsample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.android.synthetic.main.activity_main.*
import org.kotlin.mpp.mobile.data.DisplayData
import org.kotlin.mpp.mobile.model.DataRepositoryImpl
import org.kotlin.mpp.mobile.presentation.MainPresenter
import org.kotlin.mpp.mobile.presentation.MainView
import org.kotlin.mpp.mobile.storage.saveUserNameToDb
import org.kotlin.mpp.mobile.storage.userDatabase
import storage.Database

class MainActivity : AppCompatActivity(),MainView {


    override fun showLoader() {
        pb.show()
        tvBio.hide()
        tvName.hide()
        tvGists.hide()
        tvRepos.hide()
        ivAvatar.hide()
    }

    override fun hideLoader() {
        pb.hide()
        tvBio.show()
        tvName.show()
        tvGists.show()
        tvRepos.show()
        ivAvatar.show()
    }

    override fun displayData(data: DisplayData) {
        with(data) {
            tvName.text = name
            com.bumptech.glide.Glide.with(this@MainActivity).load(avatarUrl).into(ivAvatar)
            tvRepos.text = publicRepos
            tvGists.text = publicGists
            tvBio.text = bio
            saveUserNameToDb(name)
        }
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
        initializeDatabse()
        fabGo.setOnClickListener {
            presenter.loadData(etUserName.text.toString())
        }
    }

    private fun initializeDatabse() {
        if (userDatabase == null) {
            userDatabase = Database(AndroidSqliteDriver(Database.Schema, this, "user.db"))
        }
    }
}
