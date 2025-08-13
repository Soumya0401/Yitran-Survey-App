Feature: JNJ Meter Maintenance

#Background:
  #Given User Launches the Mobile Application
  #When User Enters the Mobile Number
  #And User Clicks on Get OTP
  #And User Enters the OTP
  #And User clicks on Verify OTP Button
  #Then User should be navigated to the Home Page

Scenario: Admin performs JNJ Meter maintenance
  When Admin opens the Maintenance module for JNJ
  And Admin selects JNJ Meter for Maintenance
  And Admin selects Subdivision, Feeder, and DTR for Maintenance
  And Admin clicks on the User ID for Maintenance
  And Admin fills the JNJ Meter maintenance details
