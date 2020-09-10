using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Luna.Bluetooth.Biasa.RNLunaBluetoothBiasa
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNLunaBluetoothBiasaModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNLunaBluetoothBiasaModule"/>.
        /// </summary>
        internal RNLunaBluetoothBiasaModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNLunaBluetoothBiasa";
            }
        }
    }
}
