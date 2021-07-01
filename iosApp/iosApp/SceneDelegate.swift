import UIKit
import SwiftUI
import shared

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
        
        runAbstractExamples(sharedComponent: sharedComponent)
    }

    private func runAbstractExamples(sharedComponent: IosComponent) {
        
        let mutableExample = sharedComponent.provideMutableExample()
        mutableExample.runUnlessItsAlreadyRunning()
        mutableExample.runUnlessItsAlreadyRunning()
        mutableExample.runUnlessItsAlreadyRunning()
        
//      this would result in kotlin.native.IncorrectDereferenceException: illegal attempt to access non-shared com.example.multiplatform.koru.shared.abstractexamples.MutableClassExampleIos@30164e8 from other thread
//        DispatchQueue.global(qos: .userInitiated).async {
//            mutableExample.runUnlessItsAlreadyRunning()
//        }
        
        let frozenExample = sharedComponent.provideFrozenExample()
        DispatchQueue.global(qos: .userInitiated).async {
            frozenExample.whatever()
        }
        
    }
    
}

