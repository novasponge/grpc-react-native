// @flow
import React, { Component } from 'react';
import {
  Alert,
  Button,
  StyleSheet,
  Text,
  TextInput,
  View
} from 'react-native';
import { Inbox } from './inbox'

type state = {
  content: string
}
export default class App extends Component<any, state> {
  state: { content: string } = { content: '' };
   
  render() {
    return (
      <View style={styles.container}>
        <View style={styles.textInputWrap}>
          <Text>New notification</Text>
          <TextInput
            style={styles.textInput}
            onChangeText={(content) => this.setState({content})}
            value={this.state.content}
          />
        </View>
        <Button title="call Inbox to create notification" onPress={() => this.onButtonPress()}/>
      </View>
    );
  }
   
  async onButtonPress() {
    try {
      const response = await Inbox.createNotification({ 
        content: this.state.content,
        ownerID: 1
      })
      Alert.alert('response', response.content);
    } catch (e) {
      console.log('error', e.content);
      Alert.alert('error', e.content);
    }
  }
}
 const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    padding: 20,
  },
  textInputWrap: {
    flexDirection: 'row'
  },
  textInput: {
    flex: 1,
    height: 40,
    borderColor: 'gray',
  }
});