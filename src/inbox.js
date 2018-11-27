// @flow
import { NativeModules } from "react-native";

export type Notification = { 
  id? : number,
  content : string,
  ownerID : number,
};

export type NotificationParam = { id : number };

export const Inbox = {
  createNotification: (request: Notification): Promise<Notification> => {
    return NativeModules.Inbox.CreateMessage(request);
  }
};