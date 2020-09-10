
import { NativeModules } from 'react-native';

const { RNLunaBluetoothBiasa } = NativeModules;

const Print = async (address, commands) => {
  return new Promise((resolve, reject) => {
    RNLunaBluetoothBiasa.Print(address, commands, (err, result) => {
      if (err) reject(err);
      resolve(result);
    });
  });
};

export {Print};
