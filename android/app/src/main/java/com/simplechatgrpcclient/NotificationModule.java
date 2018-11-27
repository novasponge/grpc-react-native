package com.simplechatgrpcclient;
 
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
 
import io.grpc.example.inbox.inboxGrpc;
import io.grpc.example.inbox.Notification;
import io.grpc.example.inbox.NotificationParams;

public class NotificationModule extends ReactContextBaseJavaModule {
  public NotificationModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
   
  @Override
  public String getName() {
    return "Inbox";
  }
   
  @ReactMethod
  public void CreateMessage(final ReadableMap requestMap, final Promise responsePromise) {
    new ResponseTask(responsePromise) {
      @Override
      protected WritableMap getResponse() {
        Notification request = Notification.newBuilder()
          .setContent(requestMap.getString("content"))
          .build();

        inboxGrpc.inboxBlockingStub stub = inboxGrpc.newBlockingStub(getChannel());
        Notification reply = stub.createMessage(request);
        WritableMap response = new WritableNativeMap();
        response.putString("content", reply.getContent());
        return response;
      }
    }.execute();
  }
}