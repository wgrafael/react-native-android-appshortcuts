import React, { Component } from 'react';
import { NativeModules } from 'react-native';

export const appShortcutsApi = NativeModules.RNAndroidAppShortcuts;

export class AppShortcutsAndroid extends Component {

  componentDidmount() {
    AndroidShortcuts.registerAppShourtcut({
      "shortLabel": this.props.shortLabel,
      "longLabel": this.props.longLabel,
      "uri": this.props.uri
    });
  }

  render() {
    // no render nothing
    return null;
  }
}

AppShortcuts.propType = {
  id: React.PropType.string,
  shortLabel: React.PropTypes.string,
  longLabel: React.PropTypes.string,
  uri: React.PropTypes.string
}