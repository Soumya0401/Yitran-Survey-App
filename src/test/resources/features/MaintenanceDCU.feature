Feature: DCU Maintenance

#Background:
  #Given User Launches the Mobile Application
  #When User Enters the Mobile Number
  ##And User Enters the OTP
  #And User clicks on Verify OTP Button
  #Then User should be navigated to the Home Page

Scenario: Admin performs DCU maintenance
  When Admin selects the DCU Maintenance module
  And Admin selects DCU for Maintenance
  And Admin chooses Subdivision, Feeder, and DTR for Maintenance
  And Admin clicks on the DCU ID for Maintenance
  And Admin fills the DCU maintenance details
