import Foundation
import shared
import Combine


class UserViewModel : ObservableObject {
    
    private var disposables = Set<AnyCancellable>()
    private var datesCancellable : AnyCancellable? = nil
    private var observeUsersUseCase : ObserveUsersUseCaseIos
    
    @Published var messageOutput: String = ""
    @Published var inputUsername: String = ""
    
    init(
        loadUserUseCase: LoadUserUseCaseIos,
        observeUsersUseCase: ObserveUsersUseCaseIos
    ) {
        
        self.observeUsersUseCase = observeUsersUseCase
        
        $inputUsername
            .dropFirst(1)
            .debounce(for: 0.5, scheduler: DispatchQueue.main)
            .removeDuplicates()
            .flatMap { username in
                createPublisher(wrapper: loadUserUseCase.loadUser(username: username))
                    .map { user in
                        if let name = user?.name {
                            return "Hello from the Kotlin side \(name)"
                        } else {
                            return "Nope :("
                        }
                    }
                    .catch { error -> AnyPublisher<String, Never> in
                        let message = (error as? SharedError)?.errorDescription ?? "other"
                        return Just("Error: \(message)").eraseToAnyPublisher()
                    }
            }
            .subscribe(on: DispatchQueue.main)
            .sink(
                receiveValue: { [weak self] userString in
                    self?.messageOutput = userString
                }
            )
            .store(in: &disposables)
        
    }
    
    func generateDates() {
        datesCancellable?.cancel()
        datesCancellable = createPublisher(wrapper: observeUsersUseCase.observeUsers(usersCount: 5))
            .zip(createPublisher(wrapper: observeUsersUseCase.observeUsers(usersCount: 5)))
            .scan("", { previous, matchedUsers in
                let user1 = matchedUsers.0!.name
                let user2 = matchedUsers.1!.name
                return "\(previous)\(user1) ❤️ \(user2)\n"
            })
            .sink(
                receiveCompletion: { _ in },
                receiveValue: { [weak self] matchedUsers in
                    self?.messageOutput = matchedUsers
                }
            )
    }
    
}
