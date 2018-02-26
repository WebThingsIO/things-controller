# Things Controller

Software for a Web of Things client which runs on a controller device (such as a smart speaker or smart display) used to monitor and control web things, either direcly or via a Web of Things gateway or cloud service.

* [UI Mockups](https://docs.google.com/presentation/d/1X5D1ffIOuhH0QTUbik8G-hR8eHhFGsnSBV3Nu7r_ZGA/edit?usp=sharing)

## Getting Started

This application is designed to be run as the main system app on [Android Things](https://developer.android.com/things).

* Flash Android Things onto a developer board (e.g. [flash onto a Raspberry Pi 3](https://developer.android.com/things/hardware/raspberrypi.html) with the [official Raspberry Pi touchscreen](https://www.raspberrypi.org/products/raspberry-pi-touch-display/) connected), boot it up and connect it to your local network via Ethernet or WiFi
* Clone this GitHub project onto a desktop computer and import the project into [Android Studio](https://developer.android.com/studio/index.html)
* Connect your desktop computer to the development board using `adb connect {ip address}` where `{ip address}` is the IP address displayed on the screen of your development board
* Click the "run" button to deploy the app to your development board over adb
