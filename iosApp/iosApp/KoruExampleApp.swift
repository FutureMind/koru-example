import SwiftUI
import shared

@main
struct KoruExampleApp: App {
    
    let sharedComponent = IosComponent()
    
    var body: some Scene {
        WindowGroup {
            MainScreen(sharedComponent: sharedComponent)
        }
    }
}

private struct MainScreen : View {
    
    let iosViewModel : UserViewModel
    let sharedViewModel : CountdownViewModelIos
    
    init (sharedComponent: IosComponent) {
        iosViewModel = UserViewModel(
            loadUserUseCase: sharedComponent.provideLoadUserUseCase(),
            observeUsersUseCase: sharedComponent.provideObserveUserUseCase()
        )
        sharedViewModel = sharedComponent.provideCountdownViewModel()
    }
    
    var body: some View {
        TabView {
            CountdownView(viewModel: sharedViewModel)
                .font(.system(size: 42, design: .rounded))
                .tabItem {
                    Image(systemName: "applelogo")
                    Text("Shared ViewModel")
                }
            UserView(viewModel: iosViewModel)
                .font(.system(size: 24, design: .rounded))
                .tabItem {
                    Image(systemName: "iphone.homebutton")
                    Text("Shared UseCases (iOS ViewModel)")
                }
        }
    }
}
