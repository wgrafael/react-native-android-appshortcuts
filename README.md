# React Native Android Appshortcuts


Add dynamic Android 7 Shortcuts in your react native project.

# Installing
**TODO**

# Example 

```js
class aleator extends Component {

  render() {
    return (
      <View>
        <AppShortcutsAndroid id="google" shortLabel="Search in Google" longLabel="Search anything in Google" uri="http://google.com" />
        
        <AppShortcutsAndroid id="bing" shortLabel="Search in Bing" longLabel="Search anything in Bing" uri="http://bing.com" />
     
        <Text>Hello World :P</Text>
      </View>
    );
  }
}

[Image Demo here!]
```

# Todo
[ ] Improve code and make it testable
[ ] Support to Icon
[ ] Apply guideliness by android ([Shortcut Best Practices](https://developer.android.com/guide/topics/ui/shortcuts.html#best-practices))
[ ] Add support to disable and enable shortcuts
[ ] Add support to update shortcuts
