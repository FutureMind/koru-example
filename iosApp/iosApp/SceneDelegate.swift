import UIKit
import SwiftUI

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        
        let sharedComponent = (UIApplication.shared.delegate as! AppDelegate).sharedComponent
        let viewModel = UserViewModel(
            loadUserUseCase: sharedComponent.provideLoadUserUseCase(),
            observeUsersUseCase: sharedComponent.provideObserveUserUseCase()
        )
        let contentView = UserView(viewModel: viewModel)
        
        if let windowScene = scene as? UIWindowScene {
            let window = UIWindow(windowScene: windowScene)
            window.rootViewController = UIHostingController(rootView: contentView)
            self.window = window
            window.makeKeyAndVisible()
        }
    }

}

