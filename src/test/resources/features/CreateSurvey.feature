Feature: Create Survey Module

Background:
  Given User Launches the Mobile Application
  When User Enters the Mobile Number
  And User Clicks on Get OTP
  And User Enters the OTP
  And User clicks on Verify OTP Button
  Then User should be navigated to the Home Page

Scenario: Admin performs a consumer meter survey
  When User clicks on the Survey module
  And User selects Subdivision,Feeder,and DTR
  And User searches for the Meter Number
  And If the Meter Number exists, user clicks on Details
  And If the Meter Number does not exist, user clicks on Yes
  And After the survey is completed, the user performs a resurvey
