//
//  ViewController.swift
//  iosApp
//
//  Created by Bedanta Bikash Borah on 03/04/19.
//  Copyright © 2019 Bedanta Bikash Borah. All rights reserved.
//

import UIKit
import common


class ViewController: UIViewController , MainView {
    
    func displayBookmarkedHeadLines(headlines: [NewsArticle]) {
    }
    
    
    func displayHeadLines(headlines: [NewsArticle]) {

    }
    
    func showError(error: String) {
    }
    
    func showLoader() {
        
    }
    
    func hideLoader() {
        
    }
    
    lazy var presenter : MainPresenter = { IOSInjectorKt.injectMainPresenter() }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        NewsComponent().addModule(mainModule: MainModule(mainView: self))
        presenter.doInitDatabase(driver: IOSDriverProviderKt.getNativeSqlDriver())
        presenter.loadTopHeadlines()
        
//        presenter.storeArticle(newsArticle: headline)
//        presenter.getStoredArticles { (bookmarkedArticles) -> KotlinUnit in
//            let name = bookmarkedArticles[0]
//            return KotlinUnit()
//        }
    }
}

