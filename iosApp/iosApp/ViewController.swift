//
//  ViewController.swift
//  iosApp
//
//  Created by Bedanta Bikash Borah on 03/04/19.
//  Copyright © 2019 Bedanta Bikash Borah. All rights reserved.
//

import UIKit
import common

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = CommonKt.createApplicationScreenMessage()
        view.addSubview(label)
    }
}

