# TDHFRC - three d holografic fan remote control
3D holografic fan remote controll for virtual pinball

## What is this for? The Problem!
I bought a kindly cheap 3D holografic fan from china.

It has a Handy-App for remote controlling it via Wifi.

There is also a Windows App for ending videos for it and controlling it.

But i wanted to control it directly from my virtual pinball cabinet.

The idea ist that if i am going to load up a specific pinball table the fan has to select the corresponding video for the table.

So if you going to load up the table "DarkPrinzess" a topper video is shown on the fan for ambiente purposes.

## The Fan
I bought this device here: [AliExpress Link] (https://de.aliexpress.com/item/4000579865125.html?spm=a2g0s.9042311.0.0.659e4c4dMc6T5K)

![explain pic](https://github.com/buzzibaer/3dhfrc/blob/main/docmedia/install5.png)

But it seems kindly generic, so maybe it works also for other models

Original Software for App and Desktop came from here >> https://huangbanjin.gitee.io/bergerh/

### Configuring the fan to static ip
this is needed if you want to have still internet for your cab

the fan is pushing a standard gateway to your wlan adapter via dhcp and this is a problem if you do have ethernet or a second wlan adapter up and running for internet acess.
we need to get rid of the gateway from the fan

![explain pic](https://github.com/buzzibaer/3dhfrc/blob/main/docmedia/install3.png)

Just disable DHCP on the WLAN Chip and set it to manual

The Fan has the IP = 192.168.4.1

Your WLAN Dongle / Net should have the IP = 192.168.4.2

Your Subnet = 255.255.255.0

Your Subnetmask = <EMPTY> Delete everything here

Change your setup accordingly and your internet will run like charm :)

### USB Wlan Dongle - Why using a separete WLAN Dongle

The China Fan is quite a cheap product an the software is shit.
Since the FAN is propagating a OPEN Wlan you have to connect to, you dont want to have this in yoour private network environment.
I bought a cheap usb wlan dongle for my cab windows pc and attaced this dongle exclusivly to the FAN.

Configuration of FAN and Dongle is described below.


## What does it do?
This small cluecode application is getting a pinball table as commandline input and is than selection a corresponding pic / vid on the 3d holografic fan.

If you want to see it in action look here = https://youtu.be/gSEaMVhVHcs or here https://youtu.be/rK_Xfbv4QXQ

## How does it work?
You can add this to your PinUpPopper Launch configuration for VPX and pass the table name as parameter to this commandline.
It will then select a specific vid / pic on your fan for this table


# Installation & precondition
You need 2 files from here
* tdhfrc.jar
* tabletovideomapping.ini
and you should have a java runtime installed

usually you will have one on your windows pc. This app has an quite old Java 1.8 compiler flag. Should run everywhere.

## tdhfrc.jar - the application
Download the app and put it in an folder.

In my case it is in C:\tdhfrc\tdhfrc.jar

## tabletovideomapping.ini - the config file
Download the tabletovideomapping.ini and put it in the same directory as the application.

In my case it is in C:\tdhfrc\tablevideomapping.ini

## How to configure PinUpPopper

Startup your PinupPoper Setup.

You should go here

![explain pic](https://github.com/buzzibaer/3dhfrc/blob/main/docmedia/install.png)

Add the following into your VPX lauch script:

START /min "" /i /d "C:\tdhfrc\" java -jar tdhfrc.jar "[GAMENAME]"

## How to configure the mapping between table and video for fan
just go inside the tabletovideomapping.ini and add the mapping

It looks like this:

DarkPrincess=4

Tron Legacy (Stern 2011)1.3f=8

The talbe name is passed from PinUPPopper to the application from the variable [GAMENAME]

You got this from your GameManager as showen here:
![explain pic](https://github.com/buzzibaer/3dhfrc/blob/main/docmedia/install2.png)

## Toubleshooting & Logging
You can go up higher in the loglevel to see what is going on

Have a look into the tabletovideomapping.ini there is also config for logging

Log output folder is where you placed this application

## My PC will not connect automaticly to the FAN! Help!
Yes, this is happening due to the FAN will propagate a public WLAN.
Windows 10 will not connect automaticly to public wlan, even if autoconncet is enabled.
See:
![explain pic](https://github.com/buzzibaer/3dhfrc/blob/main/docmedia/install4.png)
Source = https://appuals.com/windows-10-will-not-connect-to-wifi-automatically/




