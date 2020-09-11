
import { NativeModules } from 'react-native';
const { RNLunaBluetoothBiasa } = NativeModules;

const Print = async (address, commands) => {
  return await RNLunaBluetoothBiasa.Print(address, commands);
};

export {Print};
