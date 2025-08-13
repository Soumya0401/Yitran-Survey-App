Feature: DCU Installation Module

#Background:
  #Given User Launches the Mobile Application
  #When User Enters the Mobile Number
  #And User Clicks on Get OTP
  #And User Enters the OTP
  #And User clicks on Verify OTP Button
  #Then User should be navigated to the Home Page

Scenario: Admin selects Power On status and fills respective DCU installation form
  When Admin clicks on the Installation module
  And Admin selects DCU
  And Admin selects a valid Subdivision, Feeder, and DTR
  And Admin randomly selects Power On status
  #Then Form is filled based on Power On status
