
package com.subekti;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.net.*;
import java.io.*;
import javax.annotation.Nullable;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.react.bridge.*;
import com.subekti.sdk.Command;
import com.subekti.sdk.PrintPicture;
import com.subekti.sdk.PrinterCommand;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.concurrent.*;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class RNLunaBluetoothBiasaModule extends ReactContextBaseJavaModule {
  public static final int WIDTH_58 = 384;
  public static final int WIDTH_80 = 576;
  private int deviceWidth = WIDTH_58;
  private final ReactApplicationContext reactContext;
  ExecutorService executorService = Executors.newFixedThreadPool(20);


  public RNLunaBluetoothBiasaModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNLunaBluetoothBiasa";
  }

  @ReactMethod
  public void Print(String address, ReadableArray commands, Promise promise) {
    final String printerMac = address;
    final ReadableArray pCommands = commands;
        try {
          BluetoothAdapter blueTooth = BluetoothAdapter.getDefaultAdapter();
          blueTooth.cancelDiscovery();
          UUID SERIAL_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
          if (!blueTooth.isEnabled()) {
            blueTooth.enable();
          }
          BluetoothDevice blueDevice = blueTooth.getRemoteDevice(printerMac);
          
          BluetoothSocket bSocket = blueDevice.createInsecureRfcommSocketToServiceRecord(SERIAL_UUID);
          if (!bSocket.isConnected()) {
            bSocket.connect();
          }
            
          DataOutputStream out = new DataOutputStream(bSocket.getOutputStream());

          int i = 0;
          int length = pCommands.size();

          while (i < length) {
            ReadableMap command = pCommands.getMap(i);
            if (command.hasKey("printText")) {
              // Print TEXT
              String encoding = "GBK";
              int codepage = 0;
              int widthTimes = 0;
              int heigthTimes = 0;
              int fonttype = 0;
              String toPrint = command.getString("printText");

              byte[] bytes = PrinterCommand.POS_Print_Text(toPrint, encoding, codepage, widthTimes, heigthTimes, fonttype);
              out.write(PrinterCommand.POS_S_Align(1));
              out.write(bytes);
              out.write(PrinterCommand.POS_Set_PrtAndFeedPaper(30));
              out.write(Command.ESC_Init);
            } else if (command.hasKey("printPic")) {
              // Print PIC
              int width = 0;
              int leftPadding = 0;
              String base64Image = command.getString("printPic");
              if (command.hasKey("width")) {
                width = command.getInt("width");
              }
              if (command.hasKey("left")) {
                leftPadding = command.getInt("left");
              }
              // cannot larger then devicesWith;
              if (width > deviceWidth || width == 0) {
                width = deviceWidth;
              }

              byte[] bytes = Base64.decode(base64Image, Base64.DEFAULT);
              Bitmap mBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
              int nMode = 0;
              if (mBitmap != null) {
                byte[] data = PrintPicture.POS_PrintBMP(mBitmap, width, nMode, leftPadding);
                out.write(Command.ESC_Init);
                out.write(Command.LF);
                out.write(PrinterCommand.POS_S_Align(1));
                out.write(data);
                out.write(PrinterCommand.POS_Set_PrtAndFeedPaper(30));
                out.write(Command.ESC_Init);
              }
            } else if (command.hasKey("cutPaper")) {
              // CUT
              int cutPaper = command.getInt("cutPaper");
              out.write(PrinterCommand.POS_Set_Cut(cutPaper));
              out.write(Command.ESC_Init);
            } else if (command.hasKey("openCashDrawer")) {
              // CASH DRAWER
              boolean openCashDrawer = command.getBoolean("openCashDrawer");
              if (openCashDrawer == true) {
                out.write(PrinterCommand.POS_Set_Cashbox(0, 25, 250));
              }
            } else if (command.hasKey("feed")) {
              int feed = command.getInt("feed");
              out.write(PrinterCommand.POS_Set_PrtAndFeedPaper(feed));
            }
            i++;
          }
          Thread.sleep(200);
          promise.resolve("success");
        } catch (Exception e) {
          promise.reject(e.toString());
        }
  }
  
}
