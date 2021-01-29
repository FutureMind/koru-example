import Foundation
import SwiftUI

struct UserView: View {
    
    @ObservedObject var viewModel: UserViewModel
    
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Search by surname", text: $viewModel.inputUsername)
            Button(action: viewModel.generateDates) {
                Text("...or generate blind dates")
            }
            Text("Output").font(.title).padding(.top, 10)
            Text(viewModel.messageOutput)
            Spacer()
        }.padding(5.0)
    }
}
