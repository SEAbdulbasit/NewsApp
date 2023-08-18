# NewsApp

This is a news app to fetch top headlines from different sources.

If fingerprint is detected then it will ask for authentication otherwise will skip. Also if
fingerprint
not authenticated or some error occurred the app will close.

The aim was to do the simplest implementation. There are Unit test, plus UI test. Overall test coverage is 100% for Non-UI layers.
There are UI tests for Listing and Details screen.

** Notes **
1. Current the API key is being used directly but can be obfuscated
2. Query can be improved
3. Images cache can be implemented
