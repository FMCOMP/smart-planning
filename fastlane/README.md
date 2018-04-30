fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android stage
```
fastlane android stage
```
Deploy a new testing version to HockeyApp
### android beta
```
fastlane android beta
```
Deploy a new Google Play Store
### android release
```
fastlane android release
```
Release Application to App Store
### android build
```
fastlane android build
```
Build Application
### android archive
```
fastlane android archive
```
Upload signed application release
### android sign
```
fastlane android sign
```
Sign Application
### android upload
```
fastlane android upload
```
Upload Application Source COde
### android playstore
```
fastlane android playstore
```
Deploy App to Google Play
### android bump_version_code
```
fastlane android bump_version_code
```


----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
