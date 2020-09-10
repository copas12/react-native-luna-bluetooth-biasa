
# react-native-luna-bluetooth-biasa

## Getting started

`$ npm install react-native-luna-bluetooth-biasa --save`

### Mostly automatic installation

`$ react-native link react-native-luna-bluetooth-biasa`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-luna-bluetooth-biasa` and add `RNLunaBluetoothBiasa.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNLunaBluetoothBiasa.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.subekti.RNLunaBluetoothBiasaPackage;` to the imports at the top of the file
  - Add `new RNLunaBluetoothBiasaPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-luna-bluetooth-biasa'
  	project(':react-native-luna-bluetooth-biasa').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-luna-bluetooth-biasa/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-luna-bluetooth-biasa')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNLunaBluetoothBiasa.sln` in `node_modules/react-native-luna-bluetooth-biasa/windows/RNLunaBluetoothBiasa.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Luna.Bluetooth.Biasa.RNLunaBluetoothBiasa;` to the usings at the top of the file
  - Add `new RNLunaBluetoothBiasaPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNLunaBluetoothBiasa from 'react-native-luna-bluetooth-biasa';

// TODO: What to do with the module?
RNLunaBluetoothBiasa;
```
  