Feature: NIC Installation

Background:
  Given User Launches the Mobile Application
  When User Enters the Mobile Number
  And User Clicks on Get OTP
  And User Enters the OTP
  And User clicks on Verify OTP Button
  Then User should be navigated to the Home Page

Scenario: Admin installs the NIC device
  When Admin selects the Installation module
  And Admin selects NIC
  And Admin selects Subdivision, Feeder, and DTR for NIC device
  And Admin enters the Meter Number
  And If the Meter Number exists, admin clicks on Details
  And If the Meter Number is not found in NIC, admin clicks on Yes
  #Then Admin clicks on Add the Consumer button
