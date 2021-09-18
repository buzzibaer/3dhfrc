# tdhfrc
3D holografic fan remote controll for virtual pinball

## What is this for? The Problem!
I bought a kindly cheap 3D holografic fan from china.

It has a Handy-App for remote controlling it via Wifi.

There is also a Windows App for ending videos for it and controlling it.

But i wanted to control it directly from my virtual pinball cabinet.

The idea ist that if i am going to load up a specific pinball table the fan has to select the corresponding video for the table.

So if you going to load up the table "DarkPrinzess" a topper video is shown on the fan for ambiente purposes.

## The Fan
I bought this device here: [AliExpress Link] (https://de.aliexpress.com/item/4000579865125.html?spm=a2g0s.9042311.0.0.659e4c4dMc6T5K	)

But it seems kindly generic, so maybe it works also for other models

Original Software for App and Desktop came from here >> https://huangbanjin.gitee.io/bergerh/

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



