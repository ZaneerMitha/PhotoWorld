# 📷 PhotoWorld Overview

## ℹ️ What is it?

PhotoWorld is a photo editor that gives users the ability to upload, edit, and save photos using the file system on their computer through a Graphical User Interface, as well as optional command line control. The application displays the chosen photo in its current state, buttons for each editing option, as well as histograms that present the photo's Red, Green, and Blue (RGB) levels. Examples of possible editing options include flipping, blurring, greyscaling, filtering, and sharpening.

I created this application as a means of exploring the possibilities of Java and Graphical User Interfaces. I wanted to learn more about their capabilities, while creating an application that I could use myself. I feel that current editing applications are overcomplicated and difficult to use, and I aimed to make one that has the basic editing functionality that I use the most. After using PhotoWorld, I prefer it over Photoshop!

## 💻 Technologies

This application is developed using Java and the Swing library to integrate data storage and manipulation with the user interface. It follows a Model, View, Controller system and presents itself in a Graphical User Interface (GUI).

TL;DR:

- Java
- Swing Library
- Model, View, Controller (MVC) system
- Graphical User Interface (GUI)

## 📸 PhotoWorld Photos
<img width="1112" alt="Screenshot 2024-09-02 at 5 16 25 AM" src="https://github.com/user-attachments/assets/ab269b6c-fa1a-40c0-bc06-6e5e78322cef">
<img width="1112" alt="Screenshot 2024-09-02 at 5 17 14 AM" src="https://github.com/user-attachments/assets/cc1b1b26-3da6-42ae-93f4-34608bed6e1f">
<img width="1112" alt="Screenshot 2024-09-02 at 5 17 49 AM" src="https://github.com/user-attachments/assets/75d9e304-2a33-4106-84ad-1d75392b81fd">
<img width="1112" alt="Screenshot 2024-09-02 at 5 18 09 AM" src="https://github.com/user-attachments/assets/4e550a52-061d-42c5-89d1-771ed8c24e2f">
<img width="1112" alt="Screenshot 2024-09-02 at 5 18 43 AM" src="https://github.com/user-attachments/assets/0d7409e1-667f-4a4d-ae50-8e1c7477055c">
<img width="1112" alt="Screenshot 2024-09-02 at 5 19 32 AM" src="https://github.com/user-attachments/assets/c88cb2fd-6cb1-4692-be3d-a7856535a962">
<img width="1112" alt="Screenshot 2024-09-02 at 5 20 03 AM" src="https://github.com/user-attachments/assets/1edca479-64a2-4517-b67c-2fbb0b57f6c2">
<img width="1112" alt="Screenshot 2024-09-02 at 5 20 35 AM" src="https://github.com/user-attachments/assets/15b1189e-9280-4bb0-8960-9f026fff2dd2">

# Running PhotoWorld on your local machine

## Scripts
The program will accept these as command line arguments:
- load filepath filename (i.e. load images/selfie.ppm selfie)
- save filepath filename (i.e. save images/selfie.ppm selfie)
- brighten factor currentName newName (i.e. brighten 10 selfie brightSelfie)
- vertical-flip currentName newName (i.e. vertical-flip selfie selfie-vertical)
- horizontal-flip currentName newName (i.e. horizontal-flip selfie selfie-horizontal)
- value-component currentName newName (i.e. value-component selfie selfie-greyscale)
- intensity-component currentName newName (i.e. intensity-component selfie selfie-greyscale)
- luma-component currentName newName (i.e. luma-component selfie selfie-greyscale)
- red-component currentName newName (i.e. red-component selfie selfie-greyscale)
- green-component currentName newName (i.e. green-component selfie selfie-greyscale)
- blue-component currentName newName (i.e. blue-component selfie selfie-greyscale)
- blur currentName newName (i.e. blur selfie selfie-blur)
- sharpen currentName newName (i.e. sharpen selfie selfie-sharpen)
- sepia currentName newName (i.e.sepia selfie selfie-sepia)
- greyscale currentName newName (i.e. greyscale selfie selfie-greyscale)


In order to run this script using our program the user must input one of the above commands in the
format shown, and based on what the command is the program act accordingly.
