Original App Design Project - README Template
===

# Fake Caller App

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
A Fake Caller App creates fake calls that might be helpful in dangerous situations during alone night-time walking home. 

### App Evaluation
- **Category:** Utility app
- **Mobile:** Works like the Android phone app
- **Story:** Help protect women/girls during alone night-time walking home
- **Market:** Women who don't feel safe walking at night
- **Habit:** Possibly daily use
- **Scope:** Might be a bit challenging because of use of different APIs and mimicking the phone app interface

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can view a list of contacts.
* User can make a fake call to one contact and view "Calling" screen.
* User can get fake responses during the call.
* User can switch to an emergency activity that makes real call to emergency number.
* User can receive fake phone coming.

**Optional Nice-to-have Stories**

* User can add a list of contacts of their choices.
* User can make multiple, customized fake calls to different contacts.
* User can set time for each call.
* User can even fake Facetime.

### 2. Screen Archetypes

* Contact List (Stream)
   * User can view a list of contacts.
* Outgoing Call Activity (Detail)
   * User can make a fake call to one contact and view "Calling" screen.
   * User can get fake responses during the call.
   * User can switch to an emergency activity that makes real call to emergency number.
* Incoming Call Activity (Detail)
   * User can receive fake phone coming.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Emergency Call
* Contact List

**Flow Navigation** (Screen to Screen)

* Contact List
   * Incoming Call
   * Outgoing Call
* Incoming Call
   * Contact List
* Outgoing Calls
   * Contact List 

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
