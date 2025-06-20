# Stock.io - Investment Mobile App

Android investment application built with Jetpack Compose for portfolio management and market analysis.

## Features

- **Portfolio Dashboard**: Real-time portfolio value and balance tracking
- **Market Analysis**: Browse and purchase various investment assets
- **Asset Management**: Buy/sell stocks, crypto, gold, and mutual funds
- **Privacy Controls**: Toggle balance visibility
- **Modern UI**: Material Design 3 with smooth animations

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design**: Material Design 3
- **Architecture**: MVVM Pattern
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)


## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.9.0+
- Gradle 8.0+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/stockio-mobile-app.git
cd stockio-mobile-app
```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button

## 📁 Project Structure

```
app/src/main/java/com/example/stockio/
├── MainActivity.kt              # Main entry point
├── model/
│   └── InvestmentModels.kt     # Data models
├── ui/theme/                   # Theme configuration
├── components/
│   ├── cards/                  # Reusable cards
│   ├── dialogs/                # Dialog components
│   ├── navigation/             # Navigation components
│   ├── screens/                # Screen components
│   └── shared/                 # Shared components
```

## 🎨 Key Components

### Investment Assets
- Stocks (BBCA, BBRI)
- Cryptocurrencies (Bitcoin)
- Gold (24K)
- Mutual Funds

### Navigation
- **Home**: Portfolio overview and quick actions
- **Market**: Browse and purchase assets
- **Portfolio**: Manage existing investments
- **Profile**: User account settings

## 🔧 Build & Run

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

