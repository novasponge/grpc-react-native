package com.grpcexample;
 
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
 
import io.grpc.example.inbox.InboxGrpc;
import io.grpc.example.inbox.GetMessages;
import io.grpc.example.inbox.GetMessage;
import io.grpc.example.inbox.CreateMessage;
import io.grpc.example.inbox.UpdateMessage;
import io.grpc.example.inbox.DeleteMessage;
 
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

        InboxGrpc.InboxBlockingStub stub = InboxGrpc.newBlockingStub(getChannel());
        Notification reply = stub.CreateMessage(request);
        WritableMap response = new WritableNativeMap();
        response.putString("notification", reply.getNotification());
        return response;
      }
    }.execute();
  }
}