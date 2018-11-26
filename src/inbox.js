// @flow
import { NativeModules } from "react-native";

export type Notification = { 
  content : string,
  id? : number,
  ownerID : number,
};

export type NotificationParam = { id : number };

export const Inbox = {
  createNotification: (request: Notification): Promise<Notification> => {
    return NativeModules.Inbox.CreateMessage(request);
  }
};