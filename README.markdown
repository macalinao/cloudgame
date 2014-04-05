bukkit-bootstrap
================

A bootstrap to create new Bukkit plugins quicker.

## Setup
First, ensure you have a *nix OS, Git, and Gradle. Then proceed.

1. Edit `build.gradle` with your plugin's information.
2. Run `gradle scaffold`. This will set up your plugin directories and files and rename the `git remote` so you can push to your own Git repository.
3. Edit your `gradle.properties` and set the `testPluginDir` and `remotePluginDir` variables. Make sure you use `$HOME` instead of `~` for your home directory.
4. Delete this README and put in your own. *(optional)* It is recommended to use the `.md` extension if you are using Markdown, so edits don't conflict with this file.
5. Delete/change the license. *(also optional)*

## Usage
**bukkit-bootstrap** provides the following Gradle tasks:

* `scaffold` - Sets things up.
* `testCopy` - Copies the JAR to the test directory at whatever `testPluginDir` is set to.
* `remoteCopy` - Copies the JAR via SCP to a remote directory of your choice.

## License
This license can also be found in the attached `LICENSE.txt`.

```
The MIT License (MIT)

Copyright (c) 2014 Ian Macalinao

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

