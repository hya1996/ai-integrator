import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        AppInitHelperKt.doInitKoinAndApp()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}