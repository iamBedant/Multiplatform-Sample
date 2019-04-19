//
//  ViewController.swift
//  iosApp
//
//  Created by Bedanta Bikash Borah on 03/04/19.
//  Copyright © 2019 Bedanta Bikash Borah. All rights reserved.
//

import UIKit
import common


class ViewController: UIViewController , MainView{
    func showError(error: String) {
        
    }
    
    func displayData(data: DisplayData) {
        print(data.name)
    }
    
    func showLoader() {
        
    }
    
    func hideLoader() {
        
    }
    
    lazy var presenter : MainPresenter = {
        MainPresenter(view: self,
                      repository: DataRepositoryImpl(),
                      uiContext:IosUtilities().getDispetcher()
        )
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        presenter.doInitDatabaseIos()
        print(presenter.getSavedUserData())
        presenter.loadData(userName: "iamBedant")
        
    }
   
}

