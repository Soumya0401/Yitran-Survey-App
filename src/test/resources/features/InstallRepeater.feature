Feature: Repeater Installation

Background:
  Given User Launches the Mobile Application
  When User Enters the Mobile Number
  And User Clicks on Get OTP
  And User Enters the OTP
  And User clicks on Verify OTP Button
  #Then User should be navigated to the Home Page

Scenario: Admin installs the Repeater device
  When Admin selects on the Installation module
  And Admin selects Repeater
  And Admin selects Subdivision, Feeder, and DTR
  And Admin fills and submits all required Repeater installation details
  #And Admin submits the Repeater installation form
