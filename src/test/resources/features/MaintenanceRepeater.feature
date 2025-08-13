Feature: Repeater Maintenance

#Background:
  #Given User Launches the Mobile Application
  #When User Enters the Mobile Number
  #And User Clicks on Get OTP
  #And User Enters the OTP
  #And User clicks on Verify OTP Button
  #Then User should be navigated to the Home Page

Scenario: Admin performs Repeater maintenance
  When Admin selects the Repeater Maintenance module
  And Admin selects Repeater for Maintenance
  And Admin selects Subdivision, Feeder, and DTR for Repeator Maintenance
  And Admin clicks on the Repeater ID for Maintenance
  And Admin fills the Repeater maintenance details
