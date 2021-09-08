# Calculator
This is very much a work in progress and meant as a portfolio piece.

Currently this is a very basic app, it does not follow any mathematical rules (eg. BODMAS) and many buttons are yet to be implemented.

I plan to continuously update this project, adding more functionality until completed in order to show my work progress from the very start of building an app.

Any comments or criticism to steepen my learning curve is always appreciated.

Version 0.1
  - Basic Left to Right computation of integer numbers.
  - Buttons implemented
    - Numerical buttons (0-9).
    - Add (+), subtract (-) , divide (÷), multiply (×) and equals (=).
    - All Clear (AC).

Version 0.2
  - Decimal calculations with BigDecimal values.
  - New class Equate to handle calculations.
  - Simple JUnit Testing for integer & decimal calculations.
  - Buttons implemented
    - Decimal (.) button

Version 0.3
  - Restructured classes for better navigation of code
    - 2 packages added to separate classes.
      - calculate for common functions & equating.
      - clicks for button handling operations.
    - Button operations now separated into a 4 classes hierarchy
      - NumericClicks, OperationClicks, SpecialClicks & SetClicks
  - Deleted outer constraint layer of the "activity_main.xml" file.
  - Buttons implemented
    - Negative "+/-" button

Version 0.4
  - Added classes
    - ClicksBase - Stores general variables associated with the Clicks objects
    - Holder - Stores variables for the current calculations that can be
      plugged and unplugged from the Clicks object.
  - Buttons implemented
    - Parentheses Button "()" - auto opens and closes depending on
      current input from the user.
  - Although the application has yet to implement BODMAS rules, these can
    now be achieved using the parentheses button until BODMAS is properly
    implemented.

Thank you for reading
