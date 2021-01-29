import UIKit
import shared
import Combine

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    private var cancellables = Set<AnyCancellable>()
    let sharedComponent = IosComponent()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        IosComponentKt.doInitIosDependencies()
        return true
    }
    
}
