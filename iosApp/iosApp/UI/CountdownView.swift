import Foundation
import SwiftUI
import shared
import Combine

struct CountdownView: View {
    
    let publisher : AnyPublisher<String, Never>
    @State private var countdown : String = ""
    
    init (viewModel: CountdownViewModelIos) {
        publisher = createPublisher(wrapper: viewModel.countdown)
            .map { countdown in
                String(countdown!)
            }
            .replaceError(with: "Error occurred during countdown")
            .eraseToAnyPublisher()
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(countdown)
                .onReceive(publisher) { countdown = $0 }
        }.padding(5.0)
    }
}
